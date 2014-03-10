package com.ellin

import groovy.transform.ToString

/**
 * Created with IntelliJ IDEA.
 * User: jellin
 * Date: 10/7/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */
@ToString
class Customer implements Serializable{
    String id;
    String customerName;
    String customerLocation;
    String address;
    String city;
    String state;
    String country;
    String postalCode;
}