package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Turn;
import chess.domain.result.GameResult;
import chess.exception.InvalidPositionException;
import chess.exception.MoveCommandWhenBoardNullException;
import chess.exception.PieceImpossibleMoveException;
import chess.exception.TakeTurnException;
import chess.service.ModelParser;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Board board;

    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            board = BoardFactory.createEmptyBoard();
            return render(ModelParser.parseBoard(board), "index.html");
        });

        post("/start", (req, res) -> {
            board.initialize();
            Map<String, Object> model = ModelParser.parseBoard(board);
            model.put("turn", board.getTurn());
            return render(model, "index.html");
        });

        post("/status", (req, res) -> {
            GameResult gameResult = new GameResult();
            double blackScore = gameResult.calculateScore(board, Turn.BLACK);
            double whiteScore = gameResult.calculateScore(board, Turn.WHITE);
            if (blackScore == 0 && whiteScore == 0) {
                return render(ModelParser.parseBoard(board), "index.html");
            } else {
                Map<String, Object> model = ModelParser.parseBoard(board);
                model.put("black", blackScore);
                model.put("white", whiteScore);
                model.put("turn", board.getTurn());
                return render(model, "index.html");
            }
        });

        post("/end", (req, res) -> {
            board.clear();
            return render(ModelParser.parseBoard(board), "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = ModelParser.parseBoard(board);
            model.put("turn", board.getTurn());
            try {
                board.move(req.queryParams("fromPiece"), req.queryParams("toPiece"));
                model = ModelParser.parseBoard(board);
                model.put("turn", board.getTurn());
                model.put("isFinished", String.valueOf(board.isFinished()));
                return render(model, "index.html");
            } catch (InvalidPositionException | MoveCommandWhenBoardNullException | PieceImpossibleMoveException | TakeTurnException e) {
                return render(model, "index.html");
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
