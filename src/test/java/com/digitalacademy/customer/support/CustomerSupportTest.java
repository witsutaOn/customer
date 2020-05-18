package com.digitalacademy.customer.support;

import com.digitalacademy.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerSupportTest {

    public static Customer getNewCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("noon");
        customer.setLastName("bow");
        customer.setEmail("jane@test.com");
        customer.setPhoneNo("0111111111");
        customer.setAge(18);
        return customer;
    }

    public static Customer getUpdateCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("noon2");
        customer.setLastName("bow");
        customer.setEmail("janetest@test.com");
        customer.setPhoneNo("01111111113");
        customer.setAge(18);
        return customer;
    }

    public static Customer createNewCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("madrid");
        customer.setLastName("bayern");
        customer.setEmail("test@test.com");
        customer.setPhoneNo("0111111111");
        customer.setAge(18);
        return customer;
    }

    public static Customer responseCreateNewCustomer() {
        Customer customer = new Customer();
        customer.setId(8L);
        customer.setFirstName("madrid");
        customer.setLastName("bayern");
        customer.setEmail("test@test.com");
        customer.setPhoneNo("0111dd111111");
        customer.setAge(18);
        return customer;
    }

    public static List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Ryan");
        customer.setLastName("Gigs");
        customer.setEmail("gig@test.com");
        customer.setPhoneNo("0999999999");
        customer.setAge(22);
        customerList.add(customer);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("noon");
        customer2.setLastName("bow");
        customer2.setEmail("jane@test.com");
        customer2.setPhoneNo("0111111111");
        customer2.setAge(23);
        customerList.add(customer2);
        return customerList;
    }

    public static List<Customer> getCustomerNameRyanList() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Ryan");
        customer.setLastName("Gigs");
        customer.setEmail("gig@test.com");
        customer.setPhoneNo("0999999999");
        customer.setAge(22);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Ryan");
        customer.setLastName("beckham");
        customer.setEmail("david@test.com");
        customer.setPhoneNo("0111111111");
        customer.setAge(23);
        customerList.add(customer);

        return customerList;
    }
}