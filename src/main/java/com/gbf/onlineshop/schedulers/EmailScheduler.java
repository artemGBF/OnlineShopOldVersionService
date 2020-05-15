package com.gbf.onlineshop.schedulers;

import com.gbf.onlineshop.model.Order;
import com.gbf.onlineshop.repository.OrderRepository;
import com.gbf.onlineshop.service.MessageService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class EmailScheduler {
    @Autowired
    private MessageService messageService;
    @Autowired
    private OrderRepository orderRepository;

    //@Scheduled(fixedDelay = 30000)
    private void sendAdvertisement() {
        try {
            messageService.sendToMany();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    @Scheduled(cron = "0 30 13 ? * Sun")
    public void writeWeaklyReport() {
        write();
        try {
            messageService.sendToOne("WeeklyReport.xls");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void write(){
        List<Order> allByDate = orderRepository.findAllByDate(LocalDateTime.now().minusDays(7), LocalDateTime.now());
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("WeeklyReport");
        String[] tags = Order.getTags();
        Row first = sheet.createRow(0);
        for (int i = 0; i < tags.length; i++) {
            Cell cell = first.createCell(i);
            cell.setCellValue(tags[i]);
        }
        int rowCount = 0;
        for (Order order : allByDate) {
            Row row = sheet.createRow(++rowCount);
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(order.getNumber());
            cell = row.createCell(columnCount++);
            cell.setCellValue(order.getDate().toString());
            cell = row.createCell(columnCount++);
            cell.setCellValue(order.getDelivery().getType());
            cell = row.createCell(columnCount++);
            cell.setCellValue(order.getClient().getLogin());
            cell = row.createCell(columnCount++);
            cell.setCellValue(order.getStatus().getName());
            cell = row.createCell(columnCount);
            cell.setCellValue(order.getPaymentType().isType());
        }
        try (FileOutputStream outputStream = new FileOutputStream("WeeklyReport.xls")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
