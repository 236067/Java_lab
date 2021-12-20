package src;

import java.io.InputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.URL;

class APP {
    public static void main(String[] args) throws Exception {
        // WebService服务的地址
        URL url = new URL("https://127.0.2.0.1/GUI");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //是否具有输入参数
        conn.setDoInput(true);
        //是否输出输入参数
        conn.setDoOutput(true);
        //发POST请求
        conn.setRequestMethod("POST");
        //设置请求头（注意一定是xml格式）
        conn.setRequestProperty("content-type", "text/xml;charset=utf-8");
        // 构造请求体，符合SOAP规范（最重要的）
        String requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://com.wpx\" "
                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
                + "<soapenv:Body>"
                + "<q0:myMethod>"
                + "<name>wpx</name>"
                + "</q0:myMethod>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
        //获得一个输出流
        OutputStream out = conn.getOutputStream();
        out.write(requestBody.getBytes());
        //获得服务端响应状态码
        int code = conn.getResponseCode();
        StringBuffer sb = new StringBuffer();
        if (code == 200) {
            //获得一个输入流，读取服务端响应的数据
            InputStream is = conn.getInputStream();
            byte[] b = new byte[1024];
            int len = 0;

            while ((len = is.read(b)) != -1) {
                String s = new String(b, 0, len, "utf-8");
                sb.append(s);
            }
            is.close();
        }
        out.close();
        System.out.println("服务端响应数据为：" + sb.toString());

    }
    //html构建
    public  String Htmlbudling(){
        return"<body>\n" +
                "    <form action=\"http://127.0.2.0.1/GUI\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                "        User：<input type=\"text\" name=\"username\" /> <br>\n" +
                "        <input type=\"submit\" value=\"READ\" />\n" +
                "    </form>\n" +
                "</body>";

    }
}