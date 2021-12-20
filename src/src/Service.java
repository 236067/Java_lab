package src;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.logging.ConsoleHandler;


@WebService(targetNamespace = "https://127.0.2.0.1/GUI")
public class Service {
    @WebMethod

    public static void main(String[] argv) {
        Object implementor = new Service();
        //发布地址
        String address = "https://127.0.2.0.1/GUI";
        //发布方法

        Endpoint.publish(address, new GUI());
        System.out.println("success");
    }
}
