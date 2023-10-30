package test;

import java.util.ArrayList;
import java.util.List;

public class MainRun {

    public static void main(String[] args) {



        List<String> initialList = new ArrayList<>();
        initialList.add("item1");
        initialList.add("item2");
        ImmutableClass immutableObject = new ImmutableClass("immutable", initialList);

        System.out.println("Name: " + immutableObject.getName());
        System.out.println("List: " + immutableObject.getList());

        initialList.add("efkjgb");

        System.out.println("List: " + immutableObject.getList());

    }
}
