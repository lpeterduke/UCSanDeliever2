package edu.ucsd.cse110.ucsandeliever;

/**
 * Created by taoyeyao on 2016/11/26.
 */

public class ThreadController {
    public void sleep(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (Exception e)
        {
        }
    }
}
