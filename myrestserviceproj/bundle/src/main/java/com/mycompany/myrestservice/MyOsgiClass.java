package com.mycompany.myrestservice;

/**
 * A simple OSGI Class
 */
 
public class MyOsgiClass {
    
    /**
     * @return the methodparam value added with static message back to the jsp
     */
    public String displayMessage(String methodparam){

return "Welcome to MyOsgiClass displayMessage: Your Message is"+ methodparam;
}
}