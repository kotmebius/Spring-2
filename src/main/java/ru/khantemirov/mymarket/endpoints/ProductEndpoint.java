package ru.khantemirov.mymarket.endpoints;


import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.khantemirov.mymarket.services.ProductService;
import ru.khantemirov.mymarket.soap.products.GetAllProductsRequest;
import ru.khantemirov.mymarket.soap.products.GetAllProductsResponse;
import ru.khantemirov.mymarket.soap.products.GetProductByIdRequest;
import ru.khantemirov.mymarket.soap.products.GetProductByIdResponse;

@RequiredArgsConstructor
@Endpoint
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.khantemirov.ru/mymarket/ws/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductByIdResponse(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productService.getByIdXML(request.getId()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/ws/market
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.khantemirov.ru/mymarket/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:GetAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */



    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProductsXML().forEach(response.getProductsSOAP()::add);
        return response;
    }
}
