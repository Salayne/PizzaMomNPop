package com.example.momnpop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NewUserModel {
    private final Map<String, String> loginInfoMap = new HashMap<>();

    NewUserModel(){}
    public void saveLoginInformation(String phoneNumber, String password) throws IOException {

        loginInfoMap.put(phoneNumber, password);



        File file = new File("login_Info.txt");

        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Map.Entry<String, String> entry : loginInfoMap.entrySet()) {
            bw.write("Phone Number: " + entry.getKey() + " Password: " + entry.getValue());
            bw.newLine();
        }


        bw.close();
        fw.close();

    }

}
