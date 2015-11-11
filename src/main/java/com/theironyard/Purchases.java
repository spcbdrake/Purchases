package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.security.Timestamp;

/**
 * Created by benjamindrake on 11/11/15.
 */
@Entity
public class Purchases {
    @Id
    @GeneratedValue
    Integer id;

    String date;
    String card;
    String cvv;
    String category;

    @ManyToOne
    Customer customer;
}
