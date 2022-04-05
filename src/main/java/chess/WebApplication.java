package chess;

import chess.controller.WebController;
import chess.model.board.Board;
import chess.model.dto.WebBoardDto;
import chess.model.piece.Piece;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebApplication {
    public static String STATUS = "dev";

    public static void main(String[] args) {
        WebController webController = new WebController();

        if (STATUS.equals("dev")) {
            String projectDirectory = System.getProperty("user.dir");
            String staticDirectory = "/src/main/resources/static";
            externalStaticFileLocation(projectDirectory + staticDirectory);
        } else {
            staticFileLocation("/static");
        }

        port(8081);
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            WebBoardDto board = webController.start();
            Gson gson = new Gson();
            return gson.toJson(board.getWebBoard());
        });
    }

    private static Map<String, Piece> toMap(Board board) {
        return board.getBoard().entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey().getPosition(), Map.Entry::getValue));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
