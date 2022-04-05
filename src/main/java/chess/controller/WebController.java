package chess.controller;

import chess.model.ChessGame;
import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.dto.MoveDto;
import chess.model.dto.WebBoardDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {

    private final ChessService chessService;

    public WebController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            WebBoardDto board = chessService.start();
            return gson.toJson(board.getWebBoard());
        });

        post("/move", (req, res) -> {
            MoveDto moveCommand = gson.fromJson(req.body(), MoveDto.class);
            WebBoardDto board = chessService.move(moveCommand);
            return gson.toJson(board.getWebBoard());
        });
    }


    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
