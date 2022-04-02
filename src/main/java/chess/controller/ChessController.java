package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.board.Board;
import chess.domain.board.generator.BoardGenerator;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {
    public void run(BoardGenerator boardGenerator) {
        staticFileLocation("/static");

        ChessGame chessGame = new ChessGame();

        // 초기 페이지
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        // 새로운 보드 생성
        post("/new", (request, response) -> {
            chessGame.start(boardGenerator);
            Board board = chessGame.getBoard();

            Map<String, Object> model = new HashMap<>();
            for (Entry<Position, Piece> entry : board.getValue().entrySet()) {
                model.put(entry.getKey().getName()
                        , new PieceDto(entry.getValue()));
            }
            model.put("turn", chessGame.getTurn());

            return render(model, "board.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
