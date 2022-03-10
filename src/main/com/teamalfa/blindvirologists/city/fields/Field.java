package main.com.teamalfa.blindvirologists.city.fields;

import main.com.teamalfa.blindvirologists.Virologist;

import java.util.ArrayList;
import java.util.Collection;

public class Field {
    private ArrayList<Virologist> virologists;
    private  ArrayList<Field> neighbours;

    public Field(){
        virologists = new ArrayList<>();
        neighbours = new ArrayList<>();
    }

    public void accept(Virologist virologist) {
        virologists.add(virologist);
    }

    public void remove(Virologist virologist) {
        virologists.remove(virologist);
    }

    public ArrayList<Field> getNeighbours(){
        return neighbours;
    }

    public void setNeighbours(ArrayList<Field> neighbours) {
        this.neighbours = neighbours;
    }

    public ArrayList<Virologist> getVirologists() {
        return virologists;
    }
}
