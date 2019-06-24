package chess;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/templates");
        Gson gson = new Gson();
//        get("/api/gamenum", HistoryService::getProceedingGameNumber,gson::toJson);
    }
}
