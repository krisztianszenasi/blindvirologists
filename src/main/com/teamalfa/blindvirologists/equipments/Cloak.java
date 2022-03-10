package main.com.teamalfa.blindvirologists.equipments;

import java.util.Random;

public class Cloak extends Equipment{
    private int protectionRate = 100;


    @Override
    public boolean affectInfection() {
        return new Random().nextInt(101) < protectionRate;
    }
}
