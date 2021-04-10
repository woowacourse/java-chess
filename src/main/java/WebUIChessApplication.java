import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.WebMenuController;
import dto.ResultDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    static WebMenuController menuController = new WebMenuController();

    public static void main(String[] args) {
        Gson gson = new Gson();

        staticFiles.location("/static");
        get("/", (req, res) -> render(new HashMap<>(), "index.html"));

        get("/roomNumber", (req, res) -> menuController.findGames());

        get("/startClick", (req, res) -> {
            String input = req.queryParams("roomNumber");
            if ("new".equals(input)) {
                menuController.startNewGame();
                return render(new HashMap<String, Object>() {{
                    put("roomNumber", menuController.newGameId());
                }}, "start.html");
            }
            int gameID = Integer.parseInt(input);
            menuController.startGame(gameID);
            return render(new HashMap<>(), "start.html");
        });

        get("/start", (req, res) -> render(new HashMap<>(), "game.html"));

        get("/showData", (req, res) -> gson.toJson(menuController.status()));

        post("/movedata", (req, res) -> {
            JsonObject jsonObject = gson.fromJson(req.body(), JsonObject.class);
            String source = jsonObject.get("source").getAsString();
            String target = jsonObject.get("target").getAsString();
            ResultDto resultDto2 = menuController.move(source, target);
            return gson.toJson(resultDto2);
        });

        get("/status", (req, res) -> gson.toJson(menuController.status()));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}