package com.sa.weilianGL.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.SneakyThrows;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class MailUtil {

    @SneakyThrows
    public static void sendMail(String ToMail){
        System.out.println("sendMailServlet-----start");

        Properties prop = PropertiesUtil.getloadProperties();
        String ToMailCCs = prop.getProperty("weilian.TomailCCs");

        //1.创建邮件对象
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", "smtp.exmail.qq.com"); // 指定SMTP服务器
        properties.setProperty("mail.smtp.auth", "true"); // 指定是否需要SMTP验证
        properties.setProperty("mail.smtp.port","465");
        //使用SSL，企业邮箱必需！
        //开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(properties);
        MimeMessage mimeMessage = new MimeMessage(session);

        //一个Multipart对象包含一个或多个bodypart对象，组成邮件正文
        MimeMultipart multipart = new MimeMultipart();
        /*2.设置发件人
         * 其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
         * */
        mimeMessage.setFrom(new InternetAddress("liulei001@sensorsdata.cn", "liulei", "UTF-8"));
        /*3.设置收件人
        To收件人   CC 抄送  BCC密送*/

        //ToMail //382236853@qq.com
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(ToMail, "weilian", "UTF-8"));

        String[] strToMailccs = ToMailCCs.split(",");
        for (String toCC:strToMailccs) {
            mimeMessage.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(toCC, "weilian2", "UTF-8"));
        }

        /*4.设置标题和内容*/
        mimeMessage.setSubject("微恋报表汇总", "UTF-8");
//        mimeMessage.setContent("Test Content:这是一封测试福建...", "text/html;charset=UTF-8");

        //创建附件节点  读取本地文件,并读取附件名称
        BodyPart file1 = new MimeBodyPart();
//        File usfiles = new File("/Users/4paradigm/Desktop/lltest/渠道回流4441698931863769361.csv");
        DataSource source = new FileDataSource("./files/weilian.xls");
        file1.setDataHandler(new DataHandler(source));
        // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
        // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
//        sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//        file1.setFileName("=?GBK?B?" + enc.encode(usfiles.getName().getBytes()) + "?=");

        //MimeUtility.encodeWord可以避免文件名乱码

        file1.setFileName(source.getName());
        multipart.addBodyPart(file1);

        /*5.保存邮件*/
        mimeMessage.setSentDate(new Date());
        mimeMessage.setContent(multipart);

        mimeMessage.saveChanges();
        //465
        Transport transport = session.getTransport("smtp"); //获取邮件传输对象
        transport.connect("liulei001@sensorsdata.cn", "Liu2640141");
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();

        System.out.println("sendMailServlet-----end");
    }

//    @SneakyThrows
//    public static void main(String[] args) {
//        sendMail();
//    }
}