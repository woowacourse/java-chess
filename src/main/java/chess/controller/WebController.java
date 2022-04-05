package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.board.InitialBoardGenerator;
import chess.dto.BoardDto;
import chess.dto.PieceDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    public void run() {
        staticFiles.location("/static");
        port(8080);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            Board board = new Board(new InitialBoardGenerator());
            BoardDto boardDto = new BoardDto(board);

            List<String> emojis = boardDto.getPieces().stream()
                    .map(PieceDto::getEmoji)
                    .collect(Collectors.toList());

            model.put("pieces", emojis);

            return render(model, "/ingame.html");
        });


    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
