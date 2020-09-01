package com.sberbank.generator;

import com.sberbank.testapp.entity.Abonent;
import com.sberbank.testapp.entity.Tariff;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataFileOperations {

    public static String generate(File path) {
        try {
            if (!path.exists()) path.mkdirs();
            File file = new File(path, "sbertest.txt");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (int i = 0; i < 100; i++) {
                bw.write(String.format("%d|%s|%s|%s|%tF|%d|%d\n",
                                       i,
                                       "Surename" + i,
                                       "Firstname" + i,
                                       getSecondNameIfPresent(i),
                                       new Date(),
                                       new Random().ints(1, 4)
                                                   .findFirst()
                                                   .getAsInt(),
                                       12345 + i));
            }
            bw.flush();
            bw.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Abonent> parseFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            List<Abonent> abonents = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                abonents.add(mapToAbonent(line.split("\\|")));
            }

            return abonents;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static String getSecondNameIfPresent(int i) {
        if (i%2 == 0) {
            return "";
        } else {
            return "Secondname" + i;
        }
    }

    public static Abonent mapToAbonent(String[] line) {
        Abonent abonent = new Abonent();
        abonent.setAbonentNumber(Long.parseLong(line[0].trim()));
        abonent.setAbonentSurname(line[1].trim());
        abonent.setAbonentFirstname(line[2].trim());
        abonent.setAbonentSecondname(line[3].trim());
        abonent.setAbonentBirth(java.sql.Date.valueOf(LocalDate.parse(line[4].trim())));
        abonent.setAbonentTariffNumber(Integer.parseInt(line[5].trim()));
        abonent.setAbonentMinutes(Integer.parseInt(line[6].trim()));
        return abonent;
    }

    public static Abonent mapToAbonentWithoutId(String[] line) {
        Abonent abonent = new Abonent();
        abonent.setAbonentSurname(line[0].trim());
        abonent.setAbonentFirstname(line[1].trim());
        abonent.setAbonentSecondname(line[2].trim());
        abonent.setAbonentBirth(java.sql.Date.valueOf(LocalDate.parse(line[3].trim())));
        abonent.setAbonentTariffNumber(Integer.parseInt(line[4].trim()));
        abonent.setAbonentMinutes(Integer.parseInt(line[5].trim()));
        return abonent;
    }

    public static Tariff mapToTariffWithoutId(String line) {
        Tariff tariff = new Tariff();
        tariff.setTariffDescription(line);
        return tariff;
    }
}
