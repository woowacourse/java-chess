package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.BoardDto;
import chess.dto.MoveDto;
import chess.dto.TurnDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {
    private final ChessGame chessGame;

    public ChessWebController() {
        this.chessGame = new ChessGame();
    }

    public void run() {
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            chessGame.start();
            BoardDto boardDto = BoardDto.from(chessGame.getBoard());
            return gson.toJson(boardDto.getBoard());
        });

        post("/move", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            chessGame.move(moveDto);
            BoardDto boardDto = BoardDto.from(chessGame.getBoard());
            return gson.toJson(boardDto.getBoard());
        });

        get("/turn", (req, res) -> {
            TurnDto boardDto = TurnDto.from(chessGame.getTurn());
            return gson.toJson(boardDto.getColor());
        });

        post("/status", (req, res) -> {
            MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            chessGame.move(moveDto);
            BoardDto boardDto = BoardDto.from(chessGame.getBoard());
            return gson.toJson(boardDto.getBoard());
        });
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
