package chess;

import chess.controller.TestController;
import chess.domain.ChessGame;
import chess.domain.dto.BoardInitializeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {

    public static void main(String[] args) {
        Spark.staticFileLocation("public");
        TestController testController = new TestController();
        ObjectMapper objectMapper = new ObjectMapper();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        /*get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGame chessGame = new ChessGame();
            chessGame.settingBoard();
            model.put("board", chessGame.getBoard());
            return render(model, "index.html");
        });*/

        get("/create", (req, res) -> {
            BoardInitializeDto run = testController.run();
            return objectMapper.writeValueAsString(run);
        });

        post("/move", (req, res) -> {
            BoardInitializeDto run = testController.run();
            return objectMapper.writeValueAsString(run);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
