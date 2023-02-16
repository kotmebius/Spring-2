angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
        let coreService = "http://localhost:8189/market-core/";
        let cartService = "http://localhost:8190/market-carts/";

        $scope.fillTable = function () {
            $http.get(coreService+'api/v1/products')   //отправляем get-запрос
                .then(function (response) {                             //Потом, когда пришёл ответ
                    $scope.products = response.data;                    //Поле data - тело ответа на запрос, scope - обмен с html(аналог модели)
                    // console.log(response.data);
                    // console.log(response.data[3].title);              //вывести название 4-го продукта
                });
        };

        $scope.fillCart = function () {
            $http.get(cartService+'api/v1/cart')
                .then(function (response) {
                    $scope.cart = response.data;
                    // console.log(response);
                });
        };

        $scope.fillCategories = function () {
            $http.get(coreService+'api/v1/categories')
                .then(function (response) {
                    $scope.categories = response.data;
                    //console.log(response);
                });
        };


        $scope.fillOrders = function () {
            $http.get(coreService+'api/v1/orders')
                .then(function (response) {
                    $scope.categories = response.data;
                    //console.log(response);
                });
        };


        $scope.createNewProduct = function (id) {
            $http.post(coreService+'api/v1/products/', $scope.newProduct)
                .then(function (response) {
                    $scope.newProduct = null;
                    $scope.fillTable();
                });
        }

        $scope.deleteProduct = function (id) {
            $http.delete(coreService+'api/v1/products/' + id)
                .then(function (response) {
                    $scope.fillTable();
                });
        }
        $scope.deleteFromCart = function (id) {
            $http.delete(cartService+'api/v1/cart/' + id)
                .then(function (response) {
                    $scope.fillCart();
                });
        }
        $scope.addProductToCart = function (id) {
            $http.get(cartService+'api/v1/cart/add/' + id)
                .then(function (response) {
                    $scope.fillCart();
                });
        }

        $scope.incCartItem = function (id) {
            $http.post(cartService+'api/v1/cart/inc/' + id)
                .then(function (response) {
                    $scope.fillCart();
                });
        }

        $scope.decCartItem = function (id) {
            $http.post(cartService+'api/v1/cart/dec/' + id)
                .then(function (response) {
                    $scope.fillCart();
                });
        }
        $scope.clearCart = function () {
            $http.delete(cartService+'api/v1/cart/clear/')
                .then(function (response) {
                    $scope.fillCart();
                });
        }

        $scope.tryToAuth = function () {
            $http.post(coreService+'auth', $scope.user)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.myMarketUser = {
                            username: $scope.user.username, token: response.data.token,
                            expdate: JSON.parse(atob(response.data.token.split('.')[1])).exp
                        };
                        $scope.authError = false;
                        $scope.user.username = null;
                        $scope.user.password = null;

                        // $http.get('http://localhost:8189/app/api/v1/cart/' + $localStorage.springWebGuestCartId + '/merge')
                        //     .then(function successCallback(response) {
                        //     });
                        //
                        // $location.path('/');
                    }
                }, function errorCallback(response) {
                    if (response.status = 401) {
                        $scope.authError = true;
                    }
                })
        };

        $scope.tryToLogout = function () {
            $scope.clearUser();
            $scope.user = null;
            // $location.path('/');
        };

        $scope.clearUser = function () {
            delete $localStorage.myMarketUser;
            $http.defaults.headers.common.Authorization = '';
        };

        $scope.isUserLoggedIn = function () {
            if ($localStorage.myMarketUser) {
                if ($localStorage.myMarketUser.expdate < parseInt(new Date().getTime() / 1000)) {
                    $scope.tryToLogout;
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }

        // $scope.isWrongPassword() = function () {
        // if ()
        // }

        function run($rootScope, $http, $localStorage) {
            if ($localStorage.myMarketUser) {
                try {
                    let jwt = $localStorage.myMarketUser.token;
                    let payload = JSON.parse(atob(jwt.split('.')[1]));
                    let currentTime = parseInt(new Date().getTime() / 1000);
                    if (currentTime > payload.exp) {
                        console.log("Token is expired!!!");
                        delete $localStorage.myMarketUser;
                        $http.defaults.headers.common.Authorization = '';
                    }
                } catch (e) {
                }

                if ($localStorage.myMarketUser) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.myMarketUser.token;
                }
            }
            if (!$localStorage.springWebGuestCartId) {
                $http.get('http://localhost:8189/app/api/v1/cart/generate')
                    .then(function successCallback(response) {
                        $localStorage.springWebGuestCartId = response.data.value;
                    });
            }
        }

        $scope.confirmOrder = function () {
            $http.post(coreService+'api/v1/orders/add', $scope.customer)
                .then(function (response) {
                    ;
                });
        }

        $scope.fillTable();
        $scope.fillCart();
        $scope.fillCategories();
    }
)
;