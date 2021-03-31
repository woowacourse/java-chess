package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.ChessWebController;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private final static Gson gson = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessWebController webController = new ChessWebController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "Chess.html");
        });

        post("/initial", (req, res) -> {
            Map<String, Object> initialChessBoard = new HashMap<>();
            Map<Position, Piece> startedBoard= webController.startedBoard();
            for (Map.Entry<Position, Piece> elem : startedBoard.entrySet()) {
                Position position = elem.getKey();
                Piece piece = elem.getValue();
                initialChessBoard.put(position.symbol(), piece.symbol());
            }

            return gson.toJson(initialChessBoard);
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}