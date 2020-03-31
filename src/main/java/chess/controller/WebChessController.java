package chess.controller;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebChessController implements ChessController{
    @Override
    public void run() {
        Board board = new Board();
        get("/", (req, res) -> render(printChessBoard(board), "index.html"));

        post("/move", (req, res) -> {
            try {
                Map<String, String> bodyMap = new HashMap<>();
                for (String body : req.body().split("&")) {
                    String[] instruction = body.split("=");
                    bodyMap.put(instruction[0], instruction[1]);
                }
                board.movePiece(new Position(bodyMap.get("source")), new Position(bodyMap.get("destination")));
                res.redirect("/");
            }catch(Exception e) {
                res.body(e.getMessage());
                res.redirect("/");
            }
            return null;
        });
    }

    private Map<String, Object> printChessBoard(Board board) {
        Map<String, Object> model = new HashMap<>();
        model.put("notification", "테스트 안내 입니다.");
        model.putAll(getScore(board));
        Pieces pieces = board.getPieces();
        for (Piece piece : pieces.getAlivePieces()) {
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
