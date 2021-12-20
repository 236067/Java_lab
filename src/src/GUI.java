package src;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
//对接 websercice的地址
@WebService(targetNamespace = "https://127.0.2.0.1/GUI")
public class GUI extends DES {

    public static void main(String[] args) {
        SwingFile swingFile = new SwingFile();
    }

    public static void ui(){
        SwingFile swingFile = new SwingFile();
    }
    static class SwingFile {
        private String fileContent;
        private JTextArea textArea;

        public SwingFile() {

            JPanel panel = new JPanel();                // 创建面板容器，使用默认的布局管理器
            final JTextField textFieldTop = new JTextField(8);
            final JTextField textFieldLow = new JTextField(8);
            final JTextField textField = new JTextField(8);
            final JTextField textField2 = new JTextField(8);
            textFieldTop.setFont(new

                    Font(null, Font.PLAIN, 20));
            textFieldLow.setFont(new

                    Font(null, Font.PLAIN, 20));
            textField.setFont(new

                    Font(null, Font.PLAIN, 20));
            panel.add(textFieldTop);

            // 创建一个按钮，点击后获取文本框中的文本
            JButton btnTop = new JButton("ReadFile");
            btnTop.setFont(new

                    Font(null, Font.PLAIN, 20));
            panel.add(btnTop);

            panel.add(textFieldLow);
            JButton btnLow = new JButton("Writer");
            btnLow.setFont(new

                    Font(null, Font.PLAIN, 20));
            panel.add(btnLow);

            panel.add(textField);
            JButton Fbtn = new JButton("Encryption And Writer");
            Fbtn.setFont(new

                    Font(null, Font.PLAIN, 20));
            panel.add(Fbtn);

            panel.add(textField2);
            JButton Sbtn = new JButton("Writer And Encryption");
            Sbtn.setFont(new

                    Font(null, Font.PLAIN, 20));
            panel.add(Sbtn);
            textArea = new

                    JTextArea(50, 20);
            panel.add(textArea);

            // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
            // 1. 创建一个顶层容器（窗口）
            JFrame jf = new JFrame("The test window");          // 创建窗口
            jf.setContentPane(panel);// 4. 把 面板容器 作为窗口的内容面板 设置到 窗口
            jf.setSize(300, 500);                       // 设置窗口大小
            jf.setLocationRelativeTo(null);             // 把窗口位置设置到屏幕中心
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 当点击窗口的关闭按钮时退出程序（没有这一句，程序不会退出）
            jf.setVisible(true);

            //监听按钮
            //读取
            btnTop.addActionListener(new

                                             ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e) {
                                                     try {
                                                         FileNameExtensionFilter filter = new FileNameExtensionFilter("plain text", "xml", "json");

                                                         fileRead(textFieldTop.getText());
                                                     } catch (Exception e1) {
                                                         // TODO Auto-generated catch block
                                                         e1.printStackTrace();
                                                     }
                                                 }
                                             });
            //先加密后写入
            Fbtn.addActionListener(new

                                           ActionListener() {
                                               @Override
                                               public void actionPerformed(ActionEvent e) {
                                                   try {
                                                       fileWrite(DesTool.encrypt(textFieldLow.getText()));
                                                   } catch (Exception e1) {
                                                       // TODO Auto-generated catch block
                                                       e1.printStackTrace();
                                                   }
                                               }
                                           });
            //先写如后加密
            Sbtn.addActionListener(new

                                           ActionListener() {
                                               @Override
                                               public void actionPerformed(ActionEvent e) {
                                                   try {
                                                       fileWrite(textFieldLow.getText());//写入
                                                       DesTool.encrypt(textFieldLow.getText());//加密
                                                   } catch (Exception e1) {
                                                       // TODO Auto-generated catch block
                                                       e1.printStackTrace();
                                                   }
                                               }
                                           });
            //只写入
            btnLow.addActionListener(new

                                             ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e) {
                                                     try {
                                                         fileWrite(textFieldLow.getText());
                                                     } catch (Exception e1) {
                                                         // TODO Auto-generated catch block
                                                         e1.printStackTrace();
                                                     }
                                                 }
                                             });
        }

        public void fileRead(String fileName) throws Exception {
            File f = new File(fileName);
            FileInputStream fip = new FileInputStream(f);
            // 构建FileInputStream对象

            InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
            // 构建InputStreamReader对象,编码与写入相同

            StringBuffer sb = new StringBuffer();
            while (reader.ready()) {
                sb.append((char) reader.read());
                // 转成char加到StringBuffer对象中
            }
            String content = sb.toString();
            countWords(content);
            textArea.setText(fileContent);
            reader.close();
            // 关闭读取流

            fip.close();
            // 关闭输入流,释放系统资源
        }

        public void fileWrite(String fileName) throws Exception {
            File f = new File(fileName);
            FileOutputStream fop = new FileOutputStream(f);
            // 构建FileOutputStream对象,文件不存在会自动新建

            OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
            // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk

            writer.append(fileContent);

            writer.close();
            // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉

            fop.close();
            // 关闭输出流,释放系统资源

            textArea.setText("success");
        }

        public void countWords(String str) {

            Map<String, Integer> map = new HashMap<String, Integer>();
            Pattern p = Pattern.compile("[()0-9]*( ){0,}([+-/*]( ){0,}[()0-9]{0,})*");//正则表达式
            Matcher m = p.matcher(str);
            while (m.find()) {
                String mstr = m.group();
                if (map.containsKey(mstr)) {
                    map.put(mstr, map.get(mstr) + 1);
                } else {
                    map.put(mstr, 1);
                }
            }
            Set<Entry<String, Integer>> entrySet = map.entrySet();
            Iterator<Entry<String, Integer>> it = entrySet.iterator();
            StringBuffer sBuffer = new StringBuffer();
            sBuffer.append("Arithmetic operation" + "\r\n");
            while (it.hasNext()) {
                Entry<String, Integer> next = it.next();
                System.out.println(next.getKey() + " " + next.getValue());
                sBuffer.append(next.getKey() + "	 " + next.getValue() + "\r\n");
            }
            fileContent = sBuffer.toString();
        }
    }
}


