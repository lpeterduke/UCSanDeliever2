package edu.ucsd.cse110.ucsandeliever;

import java.util.Random;

/**
 * Created by Peterli on 10/23/16.
 */

public class Order {


    private String restaurants;

    private String item;

    private String time;
    private String destination;
    private String requestor;
    private String orderNumber;
    private String runner;
    private String requestorUid;
    private String runnerUid;

    private Random random = new Random();

    public Order(){
        int first = random.nextInt(1000);
        int second = random.nextInt(1000);
        int third = random.nextInt(1000);

        String string = Integer.toString(first) + Integer.toString(second) + Integer.toString(third);
        orderNumber = string;
        //liset


    }

    public String getOrderNumber( ) {
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRestaurants( ) {
        return this.restaurants;
    }

    public void setRestaurants(String restaurants) {
        this.restaurants = restaurants;
    }

    public String getItem( ) {
        return this.item;
    }

    public void setItem(String item2) {
        this.item = item2;
    }

    public String getTime( ) {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDestination( ) {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRequestor( ) {
        return this.requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getRunner( ) {
        return this.runner;
    }

    public void setRunner(String runner) {
        this.runner = runner;
    }

    public String getRequestorUid( ) {
        return this.requestorUid;
    }

    public void setRequestorUid(String requestorUid) {
        this.requestorUid = requestorUid;
    }

    public String getRunnerUid( ) {
        return this.runnerUid;
    }

    public void setRunnerUid(String runnerUid) {
        this.runnerUid = runnerUid;
    }


}
