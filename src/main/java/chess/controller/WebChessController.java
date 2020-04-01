package chess.controller;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController implements ChessController {
    @Override
    public void run() {
        Board board = new Board();
        AtomicReference<String> message = new AtomicReference<>("");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("score", getScore(board));
            model.put("notification", message);
            model.put("pieces", printChessBoard(board));
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            try {
                Map<String, String[]> queryMap = req.queryMap().toMap();
                board.movePiece(new Position(queryMap.get("source")[0]), new Position(queryMap.get("destination")[0]));
                message.set("");
                res.redirect("/");
            } catch (Exception e) {
                message.set(e.getMessage());
                res.redirect("/");
            }
            return null;
        });
    }

    private Map<String, Piece> printChessBoard(Board board) {
        Map<String, Piece> model = new HashMap<>();
        for (Piece piece : board.getPieces().getAlivePieces()) {
            model.put(piece.getPosition().toString(), piece);
        }
        return model;
    }

    private Map<String, Double> getScore(Board board) {
        Map<String, Double> score = new HashMap<>();
        score.put("blackScore", board.calculateScoreByTeam(Team.BLACK));
        score.put("whiteScore", board.calculateScoreByTeam(Team.WHITE));
        return score;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
