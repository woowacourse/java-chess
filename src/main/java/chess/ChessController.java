package chess;

import static spark.Spark.get;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            chessGame.initChessBoard();
            Map<Position, Piece> chessBoard = chessGame.getChessBoard();
            return gson.toJson(chessBoard);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
