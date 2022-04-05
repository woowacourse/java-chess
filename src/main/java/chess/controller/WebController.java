package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.GameResult;
import chess.domain.board.InitialBoardGenerator;
import chess.domain.piece.Color;
import chess.domain.position.Square;
import chess.dto.BoardDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    public void run() {
        staticFiles.location("/static");
        port(8080);

        get("/", (request, response) -> render(new HashMap<>(), "/ready.html"));

        Map<String, Object> model = new HashMap<>();
        ChessGame chessGame = new ChessGame(new InitialBoardGenerator());
        get("/ingame", (request, response) -> {
            chessGame.startGame();
            BoardDto boardDto = new BoardDto(chessGame.getBoard());
            List<String> emojis = boardDto.getEmojis();

            model.put("pieces", emojis);
            model.put("message", "누가 이기나 보자구~!");

            return render(model, "/ingame.html");
        });

        post("/ingame", (request, response) -> {
            String source = request.queryParams("source");
            String target = request.queryParams("target");

            try {
                chessGame.move(new Square(source), new Square(target));
                BoardDto boardDto = new BoardDto(chessGame.getBoard());
                List<String> emojis = boardDto.getEmojis();
                model.put("pieces", emojis);
                model.put("message", "누가 이기나 보자구~!");
            } catch (IllegalArgumentException e) {
                model.put("message", e.getMessage());
            }
            if (chessGame.isKingDie()) {
                model.put("message", "킹 잡았다!! 게임 끝~!~!");
                return render(model, "/finished.html");
            }
            return render(model, "/ingame.html");
        });

        get("/status", (request, response) -> {
            GameResult gameResult = new GameResult(chessGame.getBoard());
            model.put("whiteScore", gameResult.calculateScore(Color.WHITE));
            model.put("blackScore", gameResult.calculateScore(Color.BLACK));
            model.put("message", "수고하셨습니다 ^0^");
            return render(model, "/status.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
