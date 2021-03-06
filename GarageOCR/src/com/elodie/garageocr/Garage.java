package com.elodie.garageocr;

import voitures.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Garage{
    private final List<Vehicule> voitures = new ArrayList<>();

    public String toString(){
        String str = "";
        str += "***************************\n";
        str += "*  Garage OpenClassrooms  *\n";
        str += "***************************";
        return str;
    }
    public Garage() {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File( "garage.txt" ) ) ) );
            try {
                for (Vehicule v : voitures) {
                    System.out.println(((Vehicule) ois.readObject()).toString());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        } catch (FileNotFoundException e) {
            if (Files.notExists(Paths.get("garage.txt"))) System.out.println("Aucune voiture sauvegardée");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addVoiture(Vehicule v){
        voitures.clear();
        voitures.add(v);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File( "garage.txt" ) ) ) );
            for (Vehicule voiture : voitures) {
                System.out.println( voiture.toString() );
                oos.writeObject( voitures );
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
