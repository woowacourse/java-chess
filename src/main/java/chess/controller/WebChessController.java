package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {
    public static ChessBoard chessBoard = new ChessBoard();
    public static boolean blackTurn = false;

    public static void run() {
        //staticFileLocation("/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/onGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<Square, Piece> board = chessBoard.getChessBoard();
            Map<String, Piece> boardView = new HashMap<>();
            for (Square square : board.keySet()) {
                boardView.put(square.toString(), board.get(square));
            }
            model.put("chessBoard", boardView);
            return render(model, "onGame.html");
        });

        post("/move", (req, res) -> {
            try {
                List<Square> sourceDestination = Arrays.asList(Square.of(req.queryParams("source")), Square.of(req.queryParams("destination")));
                if (chessBoard.canMove(sourceDestination, blackTurn)) {
                    chessBoard.movePiece(sourceDestination);
                    blackTurn = !blackTurn;
                }
                res.redirect("/onGame");
            } catch (Exception e) {
                res.redirect("/onGame");
            }
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
