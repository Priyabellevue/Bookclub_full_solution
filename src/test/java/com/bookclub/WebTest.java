/*
Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
rights reserved.
*/
package com.bookclub;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookclub.Web.AdminController;
import com.bookclub.Web.HomeController;
import com.bookclub.Web.WishlistController;
import com.bookclub.Web.WishlistRestController;

@SpringBootTest
public class WebTest {

    @Autowired
    private HomeController controller;
    
    @Autowired
    private WishlistController controller2;

    @Autowired
    private AdminController controller3;

    @Autowired
    private WishlistRestController controller4;

    @Test
    public void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
        assertThat(controller2).isNotNull();
        assertThat(controller3).isNotNull();
        assertThat(controller4).isNotNull();
    }
    
}