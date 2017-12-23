package com.clavardage

import com.clavardage.tasks.AnnouncementManager
import com.clavardage.tasks.Server
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*
import static org.junit.Assert.assertEquals as assertEquals;

public class test {

   @Before
   public void setUp() {

   }

   @After
   public void tearDown() {
   }

    // Good IP address | Good Port
   @Test
   public void should_return_the_received_message() throws Exception {
       Server.start();
       User u = new User("test", "127.0.0.1", Network.MESSAGE_PORT);
       Session s = new Session(u);
       s.start();
       s.send(new MessageText("Plait il"));
       s.stop();
       assertEquals("Plait il", u.getHistory().getMessages().get(0).getContent());
   }

    // Bad port number
   @Test(expected=ConnectException.class)
   public void should_throw_exception_when_portNbr_different_than_6667() throws Exception {
       Server.start();
       User u = new User("test", "127.0.0.1", 7025);
       Session s = new Session(u);
       s.start();
   }

    // Public IP address
   @Test
   public void should_throw_exception_when_public_address_is_used() throws Exception {
       Server.start();
       User u = new User("test", "20.130.0.30", Network.MESSAGE_PORT);
       Session s = new Session(u);
       s.start();
   }

    // Bad IP format
    @Test(expected=AssertionError.class)
    public void testNoneRoutable() throws Exception {
        Server.start();
        User u = new User("test", "0.0.0", Network.MESSAGE_PORT);
        Session s = new Session(u);
        s.start();
    }








}