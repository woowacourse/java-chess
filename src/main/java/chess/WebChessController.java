package chess;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardFactory;
import chess.domain.position.Position;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {

    private static final String MOVE_SUCCESS = "";
    private ChessBoard chessBoard;

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
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
