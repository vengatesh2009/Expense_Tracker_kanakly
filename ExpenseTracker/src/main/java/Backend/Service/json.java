package Backend.Service;

import java.util.ArrayList;

import Backend.model.HistoryData;
import Backend.model.profile;

public class json {

    public static String HistoryToJson(ArrayList<HistoryData> historyData) {

        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < historyData.size(); i++) {

            HistoryData history = historyData.get(i);

            json.append("{");
            json.append("\"amount\":\"").append(history.getAmount()).append("\",");
            json.append("\"category\":\"").append(history.getCategory()).append("\",");
            json.append("\"createdDate\":\"").append(history.getCreatedDate()).append("\",");
            json.append("\"date\":\"").append(history.getDate()).append("\",");
            json.append("\"description\":\"").append(history.getDescription()).append("\",");
            json.append("\"title\":\"").append(history.getTitle()).append("\"");
            json.append("}");

            if (i < historyData.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");

        return json.toString();
    }

    public static String profileToJson(profile profile){
        StringBuilder json = new StringBuilder("[{");
        json.append("\"name\":\"").append(profile.getName()).append("\",");
        json.append("\"email\":\"").append(profile.getEmail()).append("\",");
        json.append("\"createdDate\":\"").append(profile.getCreatedDate()).append("\",");
        json.append("\"phoneNumber\":\"").append(profile.getPhoneNumber()).append("\",");
       json.append("\"numberOfExpense\":").append(profile.getNumberOfExpense()).append(",");
        json.append("\"totalAmount\":").append(profile.getAmount());
        json.append("}] ");
        
        return json.toString();
    }

}
/*
  String name;
    String email;
    String createdDate;
    String phoneNumber;
    int numberOfExpense; */
