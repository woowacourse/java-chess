package chess;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardFactory;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {

    private static final String MOVE_SUCCESS = "";
    private ChessBoard chessBoard;

    public WebChessController() {
        staticFileLocation("templates");
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessBoard = new ChessBoard(ChessBoardFactory.create());
            return render(model, "contents/chess.html");
        });


        post("/move", (req, res) -> {
            try {
                chessBoard.move(Position.of(req.queryParams("source")), Position.of(req.queryParams("target")));
                if (chessBoard.isCaughtKing()) {
                    return chessBoard.getPlayerColor().getColor() + "이 승리했습니다!";
                }
                return MOVE_SUCCESS;
            } catch (Exception e) {
                res.status(403);
                return e.getMessage();
            }
        });

        post("/status", (req, res) -> {
            PieceColor playerTurn = chessBoard.getPlayerColor();
            res.body(String.format("%s점수: %.1f", playerTurn.getColor(), chessBoard.calculateScore()));
            return res.body();
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
