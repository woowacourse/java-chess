package chess;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/public");

        Grid grid = new Grid(new NormalGridStrategy());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("grid", grid);
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            try {
                Map<String, String> reqBody = (Map) GSON.fromJson(req.body().toString(), new HashMap<>().getClass());
                String sourcePosition = reqBody.get("source");
                String targetPosition = reqBody.get("target");
                grid.move(sourcePosition, targetPosition);
                return "OK";
            } catch (Exception e) {
                return e;
            }
        });

        post("/start", (req, res) -> {
            try {
                grid.start();
                return "OK";
            } catch (Exception e) {
                return e;
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
