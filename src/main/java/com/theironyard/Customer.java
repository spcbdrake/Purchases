package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by benjamindrake on 11/11/15.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue
    Integer id;

    String name;
    String email;

    @OneToMany(mappedBy = "customer")
    List<Purchases> purchases;
}
