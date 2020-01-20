package com.example.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void A_testSimpleMail() {
        this.mailService.sendSimpleMail("JailBreak02@163.com", "test simple mail", " hello this is simple mail");
    }

    @Test
    public void B_testHtmlMail() {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";

        this.mailService.sendHtmlMail("JailBreak02@163.com", "test html mail", content);
    }

    @Test
    public void C_sendAttachmentsMail() {
        String relativelyPath = System.getProperty("user.dir");
        String filePath = relativelyPath + "\\Log\\spring.log";
        this.mailService.sendAttachmentsMail("JailBreak02@163.com", "主题: 带附件的邮件", "有附件, 请查收！", filePath);
    }

    @Test
    public void D_sendInlineResourceMail() {
        String relativelyPath = System.getProperty("user.dir");
        String rscId = "199209217040";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = relativelyPath + "\\Picture\\martin-luther-king-jr-day.png";

        this.mailService.sendInlineResourceMail("JailBreak02@163.com", "主题: 这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void E_sendTemplateMail() {
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = this.templateEngine.process("emailTemplate", context);

        this.mailService.sendHtmlMail("JailBreak02@163.com", "主题: 这是邮件模板", emailContent);
    }

}
