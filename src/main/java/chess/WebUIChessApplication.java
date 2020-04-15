package chess;

import chess.exception.InvalidPositionException;
import chess.exception.PieceImpossibleMoveException;
import chess.exception.TakeTurnException;
import chess.service.BoardService;
import chess.service.TurnService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static BoardService boardService = new BoardService();
    private static TurnService turnService;

    static {
        try {
            turnService = new TurnService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            return render(boardService.receiveEmptyBoard(), "index.html");
        });

        post("/start", (req, res) -> {
            turnService.initializeTurn();
            return render(boardService.receiveInitializedBoard(), "index.html");
        });

        post("/end", (req, res) -> {
            return render(boardService.receiveEmptyBoard(), "index.html");
        });

        post("/load", (req, res) -> {
            Map<String, Object> model = boardService.receiveLoadedBoard(turnService);
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = boardService.receiveLoadedBoard(turnService);
            try {
                model = boardService.receiveMovedBoard(
                        req.queryParams("fromPiece"), req.queryParams("toPiece"), turnService);
                if (boardService.isFinished()) {
                    res.redirect("/finish");
                }
            } catch(InvalidPositionException | PieceImpossibleMoveException | TakeTurnException e) {
                res.redirect("/exception");
            }
            return render(model, "index.html");
        });

        get("/finish", (req, res) -> {
            return render(turnService.receiveOnlyTurn(), "finish.html");
        });

        get("/exception", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "exception.html");
        });

//        post("/status", (req, res) -> {
//            GameResult gameResult = new GameResult();
//            double blackScore = gameResult.calculateScore(board, Team.BLACK);
//            double whiteScore = gameResult.calculateScore(board, Team.WHITE);
//            if (blackScore == 0 && whiteScore == 0) {
//                return render(BoardService.receiveEmptyBoard(), "index.html");
//            } else {
//                Map<String, Object> model = BoardService.receiveLoadedBoard(boardDAO);
//                model.put("black", blackScore);
//                model.put("white", whiteScore);
//                model.put("turn", board.getCurrentTurn());
//                return render(model, "index.html");
//            }
//        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
