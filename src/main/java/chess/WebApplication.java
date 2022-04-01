package chess;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static ChessController chessController;

    public static void main(String[] args) {
        chessController = new ChessController(new ChessGame(ChessBoardFactory.createChessBoard()));

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            chessController.start();
            res.redirect("/board");
            return null;
        });

        get("/board", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                model = toModel(chessController.findAllPiece());
            } catch (IllegalArgumentException e) {
                model.put("error", e.getMessage());
                return render(model, "index.html");
            }
            final Score score = chessController.status();
            for (Color color : Color.values()) {
                model.put(color.name(), score.findScore(color));
            }

            return render(model, "board.html");
        });

        path("/command", () -> {

            post("/move", (req, res) -> {
                chessController.move(
                        Position.from(req.queryParams("from")),
                        Position.from(req.queryParams("to"))
                );
                res.redirect("/board");
                return null;
            });
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static Map<String, Object> toModel(final Map<Position, ChessPiece> pieceByPosition) {
        return pieceByPosition.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getValue(),
                        entry -> entry.getValue().name()));
    }
}
