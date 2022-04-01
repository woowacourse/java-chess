package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.chessboard.ChessBoardFactory;
import chess.result.StartResult;
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
            res.redirect("/board");
            return null;
        });

        get("/board", (req, res) -> {
            final StartResult result = chessController.start();
            final Map<String, Object> model = result.getPieceByPosition().entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            entry -> entry.getKey().getValue(),
                            entry -> entry.getValue().name()));
            return render(model, "board.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
