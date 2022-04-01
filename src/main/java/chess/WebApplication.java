package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import chess.result.MoveResult;
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
            final Map<Position, ChessPiece> pieceByPosition = chessController.findAllPiece();
            return render(toModel(pieceByPosition), "board.html");
        });

        post("/board", (req, res) -> {
            final MoveResult result = chessController.move(
                    Position.from(req.queryParams("from")),
                    Position.from(req.queryParams("to"))
            );
            return render(toModel(result.getPieceByPosition()), "board.html");
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
