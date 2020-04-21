package chess.controller;

import chess.dao.GamesDao;
import chess.dao.MoveDao;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserController {
    private final GamesDao gamesDao = new GamesDao();
    private final Gson gson = new GsonBuilder().create();

    public void route() {
        Spark.staticFileLocation("/templates");
        post("/users", this::users);
    }

    private String users(Request request, Response response){
        try {
            int gameId = gamesDao.createGame(request.queryParams("user1"), request.queryParams("user2"));
            HashMap<String, Object> model = new HashMap<>();
            model.put("id", gameId);
            return gson.toJson(model);
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(e.getMessage());
        }
    }
}
