package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;

/**
 * Created by benjamindrake on 11/11/15.
 */
@Controller
public class PurchasesController {
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    PurchasesRepository purchasesRepo;

    @PostConstruct
    public void init() {
       if (customerRepo.count() == 0) {
           String fileContent = readFile("customers.csv");
           String[] lines = fileContent.split("\n");
           for (String line : lines) {
               if (line == lines[0])
                   continue;
               Customer customer = new Customer();
               String[] columns = line.split(",");
               customer.name = columns[0];
               customer.email = columns[1];
               customerRepo.save(customer);
           }
       }
       if (purchasesRepo.count() == 0){
           String fileContent = readFile("purchases.csv");
           String[] lines = fileContent.split("\n");
           for (String line : lines) {
               if (line == lines[0])
                   continue;
               Purchases purchases = new Purchases();
               String[] columns= line.split(",");
               Customer customer= customerRepo.findOne(Integer.valueOf(columns[0]));
               purchases.customer = customer;
               purchases.date = columns[1];
               purchases.card = columns[2];
               purchases.cvv = columns[3];
               purchases.category = columns[4];
               purchasesRepo.save(purchases);
           }
        }
    }

    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/")
    public String home (Model model) {
        model.addAttribute("customer", customerRepo.findAll());
        model.addAttribute("purchases", purchasesRepo.findAll());
        return "home";
    }
}
