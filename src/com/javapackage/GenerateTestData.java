package com.javapackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateTestData {

    private static final String CSV_FILE_PATH =
            "C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\Clanovi.csv";

    public static void main(String[] args){

        int rowCount = 10_000;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            Random random = new Random();
            String[] genders = {"M", "F"};

            //Write the header row
            String header = String.join(", ",
                    "Ime i prezime", "E-mail", "Broj telefona", "Spol", "Clanarina", "Datum", "Rok isteka");
            bw.write(header);
            bw.newLine();

            for (int i = 1; i <= rowCount; i++) {
                String name = "User" + i + " Last" + i;
                String email = "user" + i + "@example.com";
                String phone = String.format("+385 91 %03d %04d", random.nextInt(1000), random.nextInt(10000));
                String gender = genders[random.nextInt(genders.length)];
                String membership = random.nextBoolean() ? "Standard" : "Premium";
                String startDate = String.format("%02d.%02d.2024", random.nextInt(28) + 1, random.nextInt(12) + 1);
                String expirationDate = String.format("%02d.%02d.2025", random.nextInt(28) + 1, random.nextInt(12) + 1);

                String line = String.join(", ",
                        name, email, phone, gender, membership, startDate, expirationDate);

                bw.write(line);
                bw.newLine();
            }

            System.out.println("Successfully generated " + rowCount + " rows into:");
            System.out.println(CSV_FILE_PATH);

        } catch (IOException e) {
            e.printStackTrace();
        }












    }
}