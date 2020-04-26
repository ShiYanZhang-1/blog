package top.shiyana.blog.util;

import com.sun.mail.util.MailSSLSocketFactory;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

/**
 * @ProjectName: hospital
 * @Package: top.shiyana.hospital.util
 * @ClassName: EmailUtils
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/20 17:42
 * @Version: 1.0
 */
@Component
public class EmailUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

   /* @Resource
    private RedisUtil redisUtil;*/

    @Value("${spring.mail.username}")
    public String from;
    @Value("${spring.mail.password}")
    public String password;// 登录密码
    @Value("${spring.mail.protocol}")
    public String protocol;// 协议
    @Value("${spring.mail.port}")
    public String port;// 端口
    @Value("${spring.mail.host}")
    public String host;// 服务器地址

    private String theme = "注册验证码";

    private String reset = "重置验证码";

    //初始化参数
    public Session initProperties() {
        Properties properties = new Properties();
        System.out.println(this.from);
        System.out.println(this.protocol);
        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        // 使用smtp身份验证
        properties.put("mail.smtp.auth", "true");
        // 使用SSL,企业邮箱必需 start
        // 开启安全协议
        MailSSLSocketFactory mailSSLSocketFactory = null;
        try {
            mailSSLSocketFactory = new MailSSLSocketFactory();
            mailSSLSocketFactory.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        properties.put("mail.smtp.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.socketFactory.port", port);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        // 使用SSL,企业邮箱必需 end
        // TODO 显示debug信息 正式环境注释掉
        session.setDebug(true);
        return session;
    }

    /**
     * 发送注册邮箱验证码
     * @param sender
     * @return
     */
    public String sendHtmlEmail(String sender) {
        boolean lean = false;
        String code = null;
        try {
            Session session = this.initProperties();
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from,"shiyan"));// 发件人,可以设置发件人的别名
            // 收件人,多人接收
            InternetAddress[] internetAddressTo = new InternetAddress().parse(sender);
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddressTo);
            // 主题
            mimeMessage.setSubject(theme);
            // 时间
            mimeMessage.setSentDate(new Date());
            // 容器类 附件
            MimeMultipart mimeMultipart = new MimeMultipart();
            // 可以包装文本,图片,附件
            MimeBodyPart bodyPart = new MimeBodyPart();
            code = CodeUtils.getMathVal();

//            System.err.println("=========================验证码"+code);
            // 设置内容 getEmailHtml是邮箱内容模板
            bodyPart.setContent(getEmailHtml(sender,code), "text/html; charset=UTF-8");
            mimeMultipart.addBodyPart(bodyPart);
            // 添加图片&附件
//            bodyPart = new MimeBodyPart();
//            bodyPart.attachFile(fileSrc);
//            mimeMultipart.addBodyPart(bodyPart);
            mimeMessage.setContent(mimeMultipart);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);

            /*redisUtil.set(sender,code, UserConstant.time);*/

            lean = true;
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
            lean = false;
            logger.error("发送注册邮件失败："+sender+" ----"+e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            lean = false;
            logger.error("发送注册邮件失败："+sender+" ----"+e.getMessage());
        }
        if(lean){

            return code;
        }
        return null;
    }


    /**
     * 发送重置邮箱验证码
     * @param sender
     * @return
     */
    public Boolean sendHtmlEmailCZ(String sender) {
        Boolean lean = false;


        try {
            Session session = initProperties();
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from,"Tesra"));// 发件人,可以设置发件人的别名
            // 收件人,多人接收
            InternetAddress[] internetAddressTo = new InternetAddress().parse(sender);
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddressTo);
            // 主题
            mimeMessage.setSubject(reset);
            // 时间
            mimeMessage.setSentDate(new Date());
            // 容器类 附件
            MimeMultipart mimeMultipart = new MimeMultipart();
            // 可以包装文本,图片,附件
            MimeBodyPart bodyPart = new MimeBodyPart();
            String code = CodeUtils.getMathVal();


            // 设置内容 getEmailReset是发送邮箱的html模板
            bodyPart.setContent(getEmailHtml(sender,code), "text/html; charset=UTF-8");
            mimeMultipart.addBodyPart(bodyPart);
            // 添加图片&附件
//            bodyPart = new MimeBodyPart();
//            bodyPart.attachFile(fileSrc);
//            mimeMultipart.addBodyPart(bodyPart);
            mimeMessage.setContent(mimeMultipart);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);


           /* redisUtil.set(sender,code, UserConstant.time);*/


            lean = true;
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
            lean = false;
            logger.error("发送重置邮件失败："+sender+" ----"+e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            lean = false;
            logger.error("发送重置邮件失败："+sender+" ----"+e.getMessage());
        }
        return lean;
    }


    /**
     * 注册邮箱模板
     * @param to 邮箱号
     * @param code 验证码
     * @return
     */
    public String getEmailHtml(String to,String code){
        String[] tos = to.split("@");
        String sub = tos[0].substring(0,2);
        String email = sub+"*******@"+tos[1];
        String html = "<div style=\"padding: 0px 0px 0px 20px;box-sizing: border-box;color: #333333;font-family: \"microsoft yahei\";font-size: 14px\">" +
                "<h3 style=\"font-weight: normal;font-size: 18px;\">验证码</h3>" +
                "<div id=\"daojishi\"></div>\n" +
                "<script type=\"text/JavaScript\">\n" +
                "    window.onload = function(){\n" +
                "        var i = 120;\n" +
                "        var timer = setInterval(function(){\n" +
                "            if(i== -1){\n" +
                "                clearInterval(timer);\n" +
                "            }else{\n" +
                "                document.getElementById(\"daojishi\").innerHTML=i;\n" +
                "                // document.body.innerHTML = i;\n" +
                "                --i;\n" +
                "            }\n" +
                "        },1000);\n" +
                "    }" +
                "</script>"+
                "<h4 style=\"color:#2672EC;font-size: 40px;margin-top: 24px;font-weight: normal;\">账号注册验证码</h4>" +
                "<div style=\"margin-top: 40px;\">您好，您正在使用<a href=\"javascript:;\" target=\"_blank\" style=\"color: #2672EC;text-decoration: none;\">"+email+"</a>注册账号。</div>" +
                "<div style=\"margin-top: 30px;\">您的注册验证码为：<em style=\"font-style: normal;font-weight: 600;\">"+code+"</em></div>" +
                "<div style=\"margin-top: 35px;\">谢谢！</div>" +
                "<div style=\"margin-top: 10px;\">@ 誓言 </div>" ;
        return html;
    }
}