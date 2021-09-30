package net.thumbtack.school.library.Server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.thumbtack.school.library.dto.response.StopServerVariables;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class Utils {
    public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = "ui8WfwQd7Je/mb4weBHUiZ==".getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("NFIOP8DVFWgBhySJK4");
        byte[] hash = f.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(hash);
    }

    public static String concatString(String jsonString, String token) {
        if (token != null) {
            Gson gson = new Gson();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(jsonString);
            if (jsonString != null && !jsonString.equals("")) {
                stringBuilder.deleteCharAt(jsonString.length() - 1);
                stringBuilder.append(",");
                stringBuilder.append(gson.toJson(token));
                stringBuilder.deleteCharAt(jsonString.length());
            } else {
                stringBuilder.append(gson.toJson(token));
            }
            return stringBuilder.toString();
        }
        return jsonString;
    }

    public static LocalDate calculateDate(String dateRange) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(Long.parseLong(dateRange));
        return localDateTime.toLocalDate();
    }

    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dateFormat);

    }

    public static boolean checkDate(LocalDate finalDate) {
        if (finalDate == null) {
            return true;
        } else {
            LocalDateTime localDateTime = LocalDateTime.now();
            return finalDate.compareTo(ChronoLocalDate.from(localDateTime)) <= 0;
        }
    }

    public static void stopServer(String savedDataFileName, StopServerVariables stopServerVariables) {
        Gson gson = new Gson();
        if (savedDataFileName != null) {
            File file = new File(savedDataFileName);
            try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {

                JsonElement jelem = gson.toJsonTree(stopServerVariables);
                JsonObject jsonObject = jelem.getAsJsonObject();
                gson.toJson(jsonObject, br);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
