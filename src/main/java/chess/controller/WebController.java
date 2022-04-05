package chess.controller;

import static spark.Spark.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.ChessGame;
import chess.domain.board.InitialBoardGenerator;
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
            model.put("error", "누가 이기나 보자구~!");

            return render(model, "/ingame.html");
        });

        post("/ingame", (request, response) -> {
            String input = request.queryParams("squares");
            List<Square> squares = Arrays.stream(input.split(", "))
                    .map(Square::new)
                    .collect(Collectors.toList());
            try {
                chessGame.move(squares.get(0), squares.get(1));
                BoardDto boardDto = new BoardDto(chessGame.getBoard());
                List<String> emojis = boardDto.getEmojis();
                model.put("pieces", emojis);
                model.put("error", "누가 이기나 보자구~!");
            } catch (IllegalArgumentException e) {
                model.put("error", e.getMessage());
            }
            return render(model, "/ingame.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
