package com.springapp.mvc;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-11
 * Time: 下午10:22
 * To change this template use File | Settings | File Templates.
 */
public class Person {
    private String firstname;
    private String lastname;
    private PhoneNumber phone;
    private PhoneNumber fax;
    public  Person(String firstName,String lastName){
       this.firstname= firstName;
       this.lastname= lastName;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public void setPhone(PhoneNumber phone) {
        this.phone = phone;
    }

    public PhoneNumber getFax() {
        return fax;
    }

    public void setFax(PhoneNumber fax) {
        this.fax = fax;
    }
}
