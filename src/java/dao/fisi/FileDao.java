/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.fisi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author vhbo
 */
public class FileDao {

    // Lee cuantos visitantes tiene el sitio web
    public int getCount() {
        int cuenta = 0;
        PrintWriter writer = null;
        FileReader reader = null;
        BufferedReader bufReader = null;

        try {
            File f = new File("Contador.initial");
            if (!f.exists()) {
                // Si no existe lo crea y le pone cero
                f.createNewFile();
                writer = new PrintWriter(new FileWriter(f));
                writer.println(0);
            }
            if (writer != null) {
                writer.close();
            }

            reader = new FileReader(f);
            bufReader = new BufferedReader(reader);
            String inicio = bufReader.readLine();
            cuenta = Integer.parseInt(inicio);
        } catch (IOException | NumberFormatException ex) {
            if (writer != null) {
                writer.close();
            }
        }

        if (bufReader != null) {
            try {
                bufReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cuenta;
    }

    // Graba la cuenta de visitantes
    public void save( int cuenta ) throws Exception {
        FileWriter writer = null;
        PrintWriter printWriter = null;
        writer = new FileWriter( "Contador.initial" );
        printWriter = new PrintWriter(writer);
        printWriter.println(cuenta);
        if (printWriter != null) {
            printWriter.close();
        }
    }
}
