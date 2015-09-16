package com.example.jkt.wall2wall0;

/**
 * Created by JDK on 4/4/2015.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.example.jkt.wall2wall0.FileIO;

public class Settings {

    // Create variables that will hold the values you want to save in your game.
    // Default values:

    public static boolean soundEnabled = true;

    public static int getHighScore() {
        return highScore;
    }

    public static void setHighScore(int highScore) {
        Settings.highScore = highScore;
    }

    public static int highScore;


    // save method is used to write updated sound setting and score value(key, value) to a
    // .savedata file
    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {

            // Writes a file called .savedata to the SD Card
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(".savedata")));

            // Line by line ("\n" creates a new line), write the value of each variable to the file.
            out.write(Boolean.toString(soundEnabled));
            out.write("\n");

            // Uses a for loop to save 5 numbers to the save file. INCORRECT NOTE
            out.write(Integer.toString(highScore));
            out.write("\n");

            // This section handles errors in file management!

        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    // load method is used to read sound setting and score value from .savedata file
    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            // Reads file called Save Data
            in = new BufferedReader(new InputStreamReader(
                    files.readFile(".savedata")));

            // Loads values from the file and replaces default values.
            soundEnabled = Boolean.parseBoolean(in.readLine());

            highScore = Integer.parseInt(in.readLine());

        } catch (IOException e) {
            // Catches errors. Default values are used.
        } catch (NumberFormatException e) {
            // Catches errors. Default values are used.
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }
}

