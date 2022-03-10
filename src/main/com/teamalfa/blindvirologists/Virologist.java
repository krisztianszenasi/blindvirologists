package main.com.teamalfa.blindvirologists;

import main.com.teamalfa.blindvirologists.agents.geneticCodes.GeneticCode;
import main.com.teamalfa.blindvirologists.agents.viruses.Virus;
import main.com.teamalfa.blindvirologists.agents.viruses.VirusComparator;
import main.com.teamalfa.blindvirologists.city.fields.Field;
import main.com.teamalfa.blindvirologists.equipments.Equipment;

import java.util.ArrayList;
import java.util.Collections;

public class Virologist {
    private ArrayList<Virus> activeViruses;
    private ArrayList<GeneticCode> protectionBank;
    private ArrayList<Equipment> equipments;
    private Field current;

    public Virologist() {
        activeViruses = new ArrayList<>();
        protectionBank = new ArrayList<>();
        equipments = new ArrayList<>();
    }

    public void move(Field destination) {
        // Variable that stores possible movement modification by viruses.
        Field modified = null;

        // If a virus wants to modify the destination then it returns the desired field in its affectMovement method.
        // Viruses which don't want to modify the movement will return a NULL.
        // Since viruses near to the end of the list can override previous modifications, the activeViruses list
        // must be ordered by some kind of priority. (E.g. paralyzed vs random move)
        if(!activeViruses.isEmpty()){
            sortViruses();
            for(Virus virus : activeViruses)
                modified = virus.affectMovement(current);
        }

        // If the modified variable is not NULL then the movement is affected by a virus.
        if(modified != null)
            destination = modified;

        current.remove(this);
        destination.accept(this);
        current = destination;
    }

    public boolean infectedBy(Virus virus) {
        if(protectionBank.contains(virus.getCode())) {
            return false;
        }else {
            for(Equipment equipment : equipments)
                if(equipment.affectInfection())
                    return false;
        }
        activeViruses.add(virus);
        return true;
    }

    public void protectedBy(GeneticCode code) {
        protectionBank.add(code);
    }

    public Field getField() {
        return current;
    }

    private void sortViruses(){
        Collections.sort(activeViruses, new VirusComparator());
    }

    public void setField(Field current) {
        this.current = current;
    }

    public void removeViruses() {
        activeViruses.clear();
    }
    public void clearProtectionBank() { protectionBank.clear(); }

    public ArrayList<Virus> getActiveViruses(){
        return activeViruses;
    }

    public void equip(Equipment equipment) {
        equipments.add(equipment);
    }

    public void forgetAllCode(){
        // TODO implement
    }
}
