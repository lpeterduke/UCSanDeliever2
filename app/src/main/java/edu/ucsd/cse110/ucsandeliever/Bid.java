package edu.ucsd.cse110.ucsandeliever;
import java.util.*;

/**
 * Created by taoyeyao on 2016/11/6.
 */

public class Bid {
    private String bidNum,money,runner,time;
    private Random r=new Random();

    public Bid()
    {
        int i;
        bidNum="";
        for (i=0;i<3;i++)
            bidNum+=Integer.toString(r.nextInt(1000));
    }

    public Bid(String s1, String s2, String s3)
    {
        this();
        money=s1;
        time=s2;
        runner=s3;
    }

    public String getMoney()
    {
        return money;
    }

    public void setMoney(String s)
    {
        money=s;
    }

    public String getRunner()
    {
        return runner;
    }

    public void setRunner(String s)
    {
        runner=s;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String s)
    {
        time=s;
    }

    public String getBidNum()
    {
        return bidNum;
    }
}
