package chess.controller;

import chess.exception.InvalidPositionException;
import chess.exception.PieceImpossibleMoveException;
import chess.exception.TakeTurnException;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessController {
    private ChessGameService chessGameService;

    public WebUIChessController() throws SQLException {
        this.chessGameService = new ChessGameService();
    }

    public void run() {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            return render(chessGameService.receiveLoadedBoard(), "index.html");
        });

        post("/start", (req, res) -> {
            chessGameService.initializeTurn();
            return render(chessGameService.receiveInitializedBoard(), "index.html");
        });

        post("/end", (req, res) -> render(chessGameService.receiveEmptyBoard(), "index.html"));

        post("/load", (req, res) -> render(chessGameService.receiveLoadedBoard(), "index.html"));

        post("/move", (req, res) -> {
            Map<String, Object> model = chessGameService.receiveLoadedBoard();
            try {
                model = chessGameService.receiveMovedBoard(
                        req.queryParams("fromPiece"), req.queryParams("toPiece"));
                if (chessGameService.isFinish()) {
                    chessGameService.initializeFinish();
                    res.redirect("/finish");
                }
            } catch(InvalidPositionException | PieceImpossibleMoveException | TakeTurnException e) {
                res.redirect("/exception");
            }
            return render(model, "index.html");
        });

        get("/finish", (req, res) -> render(chessGameService.receiveWinner(), "finish.html"));

        get("/exception", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "exception.html");
        });

        post("/status", (req, res) -> {
            Map<String, Object> model = chessGameService.receiveScoreStatus();
            model.put("turn", chessGameService.getCurrentTurn() + "차례입니다.");
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
