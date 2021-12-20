package src;

public class Client extends GUI {
    //创建服务试图
    Service service=new Service();
    //获取服务实现类
    Service servicImp= service.getPort(GUI.class);
    //调用查询方法打印
    servicImp.ui();
}
