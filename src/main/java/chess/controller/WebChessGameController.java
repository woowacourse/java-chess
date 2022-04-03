package chess.controller;

import chess.domain.ChessWebGame;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.dto.MoveDto;
import chess.dto.ScoreDto;
import chess.service.ChessService;
import chess.view.ChessMap;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessGameController {

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        final Gson gson = new Gson();
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        final ChessWebGame chessWebGame = new ChessWebGame(whitePlayer, blackPlayer);
        final ChessService chessService = new ChessService();

        get("/", (req, res) ->
                render(new HashMap<>(), "index.html")
        );

        get("/status", (req, res) -> {
            final ScoreDto scoreDto = chessWebGame.getScoreStatus();
            return gson.toJson(scoreDto);
        });

        get("/end", (req, res) -> {
            return null;
        });

        post("/move", (req, res) -> {
            final MoveDto moveDto = gson.fromJson(req.body(), MoveDto.class);
            final ChessMap chessMap = chessService.move(chessWebGame, moveDto);
            return gson.toJson(chessMap);
        });

        exception(Exception.class, (exception, req, res) -> {
            res.body(exception.getMessage());
        });
    }
}
