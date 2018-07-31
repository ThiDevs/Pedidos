package sample;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class ABC2 {
    public static void main(String args[]) throws IOException, InterruptedException {

        // create a new hasthtable
        Dictionary d = new Hashtable();

        // put some elements
        d.put("1", "Chocolate");
        d.put("2", "Cocoa");

        d.put("5", "Coffee");
        d.put("5", "Coffee2");

        // print how many times put was called
        System.out.println(d);

    }
}
