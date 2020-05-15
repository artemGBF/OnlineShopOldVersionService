package com.gbf.onlineshop.service;

import com.gbf.onlineshop.model.Admin;
import com.gbf.onlineshop.model.Client;
import com.gbf.onlineshop.repository.AdminRepository;
import com.gbf.onlineshop.repository.ClientRepository;
import com.gbf.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MessageService {
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;


    public void sendToOne(String path1) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Admin admin = adminRepository.findById(12L).get();
        helper.setTo(new String[]{admin.getMail()});
        helper.addAttachment("WeeklyReport.xls", new File(path1));
        helper.setSubject("Hi there!");
        sender.send(message);
    }

    public void sendToMany() throws MessagingException {
        /*MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
       String[] arr = (String[]) userRepository.getUsers().stream().
               filter(t->t.getClass()==Client.class).map(t->t.getMail()).toArray();
        helper.setTo(arr);
        helper.setText("advertisement");
        helper.setSubject("Hi there!");
        sender.send(message);*/
        //new String[]{"shipilovGBF@yandex.ru"/*, "2323353@gmail.com", "school15345a@yandex.ru"*/}
    }


}
