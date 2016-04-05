package com.shihang.myframe.manager;


import java.util.ArrayList;
import java.util.List;

public class HomeTabManager {

    public enum TAB{
        HOME,
        INFO
    }

    public static List<Chooseble> chooseListener = new ArrayList();

    public interface Chooseble{
        void choose(TAB tab);
    }

    public static void choose(TAB tab){
        for (Chooseble chooseble : chooseListener){
            chooseble.choose(tab);
        }
    }

    public static void addChooseble(Chooseble chooseble){
        if(chooseble != null && !chooseListener.contains(chooseble)){
            chooseListener.add(chooseble);
        }
    }

    public static void removeChooseble(Chooseble chooseble){
        if(chooseble != null && chooseListener.contains(chooseble)){
            chooseListener.remove(chooseble);
        }
    }

}
