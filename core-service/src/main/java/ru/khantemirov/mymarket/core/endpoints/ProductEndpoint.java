package ru.khantemirov.mymarket.core.endpoints;


import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.khantemirov.mymarket.core.services.ProductService;
import ru.khantemirov.mymarket.core.soap.products.GetAllProductsRequest;
import ru.khantemirov.mymarket.core.soap.products.GetAllProductsResponse;
import ru.khantemirov.mymarket.core.soap.products.GetProductByIdRequest;
import ru.khantemirov.mymarket.core.soap.products.GetProductByIdResponse;
import ru.khantemirov.mymarket.core.soap.products.*;


@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.khantemirov.ru/mymarket/ws/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductByIdResponse(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProductSOAP(productService.getByIdXML(request.getId()));
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



//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
//    @ResponsePayload
//    public JAXBElement<GetAllProductsResponse> getAllProducts(@RequestPayload JAXBElement<GetAllProductsRequest> request) {
//        GetAllProductsResponse response = new GetAllProductsResponse();
//        productService.getAllProductsXML().forEach(response.getProductsSOAP()::add);
//        QName qname = new QName("GetAllProductsResponse");
//        JAXBElement<GetAllProductsResponse> jaxbElement = new JAXBElement<>(qname,
//                GetAllProductsResponse.class, response);
//        return jaxbElement;
//    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProductsXML().forEach(response.getProductsSOAP()::add);
        return response;
    }
}
