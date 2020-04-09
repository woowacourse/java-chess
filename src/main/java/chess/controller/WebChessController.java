package chess.controller;

import chess.dao.ChessDAO;
import chess.domain.Position;
import chess.domain.service.BoardService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController implements ChessController {
    private static final String BLANK = "";
    private static final String WINNER_ALERT = "이 승리했습니다!";

    private BoardService boardService = new BoardService();

    @Override
    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/status", "application/json", (req, res) -> boardService.getStatus(), new JsonTransformer());

        post("/move", (req, res) -> {
            ChessDAO chessDAO = ChessDAO.getInstance();
            try {
                boardService.movePiece(new Position(req.queryParams("source")), new Position(req.queryParams("destination")));
                chessDAO.saveGame(boardService.getStatus());
                return parseMoveMessage();
            } catch (Exception e) {
                res.status(400);
                return e.getMessage();
            }
        });

        get("/reset", (req, res) -> {
            boardService.resetBoard();
            res.redirect("/");
            return null;
        });

        get("/load", (req, res) -> {
            ChessDAO chessDAO = ChessDAO.getInstance();
            boardService.loadGame(chessDAO.loadGame());
            res.redirect("/");
            return null;
        });
    }

    private String parseMoveMessage() {
        if (boardService.checkEndOfGame()) {
            return boardService.getWinner().getName() + WINNER_ALERT;
        }
        return BLANK;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
