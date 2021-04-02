package chess.controller;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import chess.DTO.BoardDto;
import chess.domain.board.BoardFactory;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessController {
    public void run() {
        staticFileLocation("/templates");
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, String> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            BoardDto boardDto = new BoardDto(BoardFactory.initializeBoard());
            return render(boardDto.getBoard(), "game.html");
        });

//        get("/path", (req, res) -> {
//            BoardDto boardDto = new BoardDto();
//            return render(boardDto.getBoard(), "game.html");
//        });

        get("/move", (req, res) -> {
            Map<String, String> model = new HashMap<>();
            return render(model, "game.html");
        });
    }

    private static String render(Map<String, String> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
