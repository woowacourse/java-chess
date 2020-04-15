package chess;

import chess.exception.InvalidPositionException;
import chess.exception.PieceImpossibleMoveException;
import chess.exception.TakeTurnException;
import chess.service.BoardService;
import chess.service.FinishService;
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
    private static FinishService finishService = new FinishService();

    static {
        try {
            turnService = new TurnService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", (req, res) -> render(boardService.receiveEmptyBoard(), "index.html"));

        post("/start", (req, res) -> {
            turnService.initializeTurn();
            return render(boardService.receiveInitializedBoard(), "index.html");
        });

        post("/end", (req, res) -> render(boardService.receiveEmptyBoard(), "index.html"));

        post("/load", (req, res) -> {
            Map<String, Object> model = boardService.receiveLoadedBoard(turnService);
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = boardService.receiveLoadedBoard(turnService);
            try {
                model = boardService.receiveMovedBoard(
                        req.queryParams("fromPiece"), req.queryParams("toPiece"), turnService, finishService);
                if (finishService.isFinish()) {
                    finishService.initialize();
                    res.redirect("/finish");
                }
            } catch(InvalidPositionException | PieceImpossibleMoveException | TakeTurnException e) {
                res.redirect("/exception");
            }
            return render(model, "index.html");
        });

        get("/finish", (req, res) -> render(turnService.receiveWinner(), "finish.html"));

        get("/exception", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "exception.html");
        });

        post("/status", (req, res) -> {
            Map<String, Object> model = boardService.receiveScoreStatus();
            model.put("turn", turnService.getCurrentTurn() + "차례입니다.");
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
