package com.digitalacademy.customer.service;

import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.repositories.CustomerRepository;
import com.digitalacademy.customer.support.CustomerSupportTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@SpringBootTest(classes = CustomerServiceTest.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService ;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @DisplayName("Test get all customer should return list")
    @Test
    void testGetAllCustomer(){
        when(customerRepository.findAll()).thenReturn(CustomerSupportTest.getCustomerList());
        List<Customer> resp = customerService.getCustomerList();

        assertEquals(1,resp.get(0).getId().intValue());
        assertEquals("Ryan",resp.get(0).getFirstName());
        assertEquals("Gigs",resp.get(0).getLastName());
        assertEquals("gig@test.com", resp.get(0).getEmail());
        assertEquals("0999999999",resp.get(0).getPhoneNo());
        assertEquals(22,resp.get(0).getAge().intValue());

        assertEquals(2,resp.get(1).getId().intValue());
        assertEquals("noon",resp.get(1).getFirstName());
        assertEquals("bow",resp.get(1).getLastName());
        assertEquals("jane@test.com", resp.get(1).getEmail());
        assertEquals("0111111111",resp.get(1).getPhoneNo());
        assertEquals(23,resp.get(1).getAge().intValue());


    }

    @DisplayName("Test get all customer by id should return list")
    @Test
    void testGetAllCustomerById(){
        Long reqParam = 1L;
        when(customerRepository.findAllById(reqParam))
                .thenReturn(CustomerSupportTest.getCustomerList().get(0));
        Customer resp = customerService.getCustomer(reqParam);

        assertEquals(1,resp.getId().intValue());
        assertEquals("Ryan",resp.getFirstName());
        assertEquals("Gigs",resp.getLastName());
        assertEquals("gig@test.com", resp.getEmail());
        assertEquals("0999999999",resp.getPhoneNo());
        assertEquals(22,resp.getAge().intValue());
    }

    @DisplayName("Test get all customer by name should return")
    @Test
    void testGetAllCustomerBynName(){
        String name = "Ryan";
        when(customerRepository.findByFirstName(name))
                .thenReturn(CustomerSupportTest.getCustomerNameRyanList());
        List<Customer> resp = customerService.getCustomerName(name);
        assertEquals(1,resp.get(0).getId().intValue());
        assertEquals("Ryan",resp.get(0).getFirstName());
        assertEquals("Gigs",resp.get(0).getLastName());
        assertEquals("gig@test.com", resp.get(0).getEmail());
        assertEquals("0999999999",resp.get(0).getPhoneNo());
        assertEquals(22,resp.get(0).getAge().intValue());;
    }


    @DisplayName("Test create customer should return new customer")
    @Test
    void testCreateCustomer(){
        Customer reqCustomer = new Customer();

        reqCustomer.setFirstName("noon");
        reqCustomer.setLastName("bow");
        reqCustomer.setEmail("jane@test.com");
        reqCustomer.setPhoneNo("0111111111");
        reqCustomer.setAge(18);

        when(customerRepository.save(reqCustomer)).thenReturn(CustomerSupportTest.getNewCustomer());
        Customer resp = customerService.createCustomer(reqCustomer);

        assertEquals(1,resp.getId().intValue());
        assertEquals("noon",resp.getFirstName());
        assertEquals("bow",resp.getLastName());
        assertEquals("jane@test.com", resp.getEmail());
        assertEquals("0111111111",resp.getPhoneNo());
        assertEquals(18,resp.getAge().intValue());
    }

    @DisplayName("Test update customer should return SUCCESS")
    @Test
    void testUpdateCustomer(){
        Long reqId = 1L;
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("noon2");
        reqCustomer.setLastName("bow");
        reqCustomer.setEmail("jane@gmail.com");
        reqCustomer.setPhoneNo("0111111111");
        reqCustomer.setAge(18);

        when(customerRepository.findAllById(reqId)).thenReturn(CustomerSupportTest.getNewCustomer());
        when(customerRepository.save(reqCustomer)).thenReturn(CustomerSupportTest.getUpdateCustomer());
        Customer resp =  customerService.updateCustomer(reqId, reqCustomer);

        assertEquals(1, resp.getId().intValue());
        assertEquals("noon2", resp.getFirstName());
        assertEquals("bow", resp.getLastName());
        assertEquals("janetest@test.com", resp.getEmail());
        assertEquals("01111111113", resp.getPhoneNo());
        assertEquals(18, resp.getAge().intValue());
    }
    @DisplayName("Test update customer should return FAIL")
    @Test
    void testUpdateCustomerFail(){
        Long reqId = 4L;
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("NoonNo");
        reqCustomer.setLastName("BowNo");
        reqCustomer.setEmail("janeNo@test.com");
        reqCustomer.setPhoneNo("0999999999");
        reqCustomer.setAge(17);

        when(customerRepository.findAllById(reqId)).thenReturn(null);
        Customer resp =  customerService.updateCustomer(reqId, reqCustomer);
        assertEquals(null, resp);
    }
    @DisplayName("Test delete customer should return true")
    @Test
    void testDeleteCustomer(){
        Long reqId = 1L;
        doNothing().when(customerRepository).deleteById(reqId);
        boolean resp = customerService.deleteById(reqId);
        assertTrue(resp);
    }

    @DisplayName("Test delete customer should return false")
    @Test
    void testDeleteCustomerFalse(){
        Long reqId = 1L;
        doThrow(EmptyResultDataAccessException.class).when(customerRepository).deleteById(reqId);

        boolean resp = customerService.deleteById(reqId);
        assertFalse(false);
    }
}
