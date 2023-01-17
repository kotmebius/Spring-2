angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    $scope.fillTable = function () {
        $http.get('http://localhost:8189/market/api/v1/products')   //отправляем get-запрос
            .then(function (response) {                             //Потом, когда пришёл ответ
                $scope.products = response.data;                    //Поле data - тело ответа на запрос, scope - обмен с html(аналог модели)
                // console.log(response.data);
                //console.log(response.data[3].title);              //вывести название 4-го продукта
            });
    };

    $scope.fillCart = function () {
        $http.get('http://localhost:8189/market/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
                // console.log(response);
            });
    };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/market/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.myMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    // $http.get('http://localhost:8189/app/api/v1/cart/' + $localStorage.springWebGuestCartId + '/merge')
                    //     .then(function successCallback(response) {
                    //     });
                    //
                    // $location.path('/');
                }
            }, function errorCallback(response) {
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }
    $scope.deleteFromCart = function (id) {
        $http.delete('http://localhost:8189/market/api/v1/cart/' + id)
            .then(function (response) {
                $scope.fillCart();
            });
    }
    $scope.addProductToCart = function (id) {
        $http.get('http://localhost:8189/market/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.fillCart();
            });
    }

    $scope.incCartItem = function (id) {
        $http.post('http://localhost:8189/market/api/v1/cart/inc/' + id)
            .then(function (response) {
                $scope.fillCart();
            });
    }

    $scope.decCartItem = function (id) {
        $http.post('http://localhost:8189/market/api/v1/cart/dec/' + id)
            .then(function (response) {
                $scope.fillCart();
            });
    }
    $scope.clearCart = function () {
        $http.post('http://localhost:8189/market/api/v1/cart/flush/')
            .then(function (response) {
                $scope.fillCart();
            });
    }

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:8189/market/api/v1/products', $scope.customer)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

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
            return true;
        } else {
            return false;
        }
    };

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.myMarketUser) {
            try {
                let jwt = $localStorage.springWebUser.token;
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
        $http.post('http://localhost:8189/market/api/v1/orders/add', $scope.customer)
            .then(function (response) {
                alert(response.status);
            });
    }

    $scope.fillTable();
    $scope.fillCart();
});