package com.company.Model;

import java.sql.Connection;

/**
 * Created by Bajtas on 2015-05-10.
 */
public class CustomerService {
    private Connection ActualConnection;
    public CustomerService(Connection con) {
        ActualConnection = con;

    }
}
