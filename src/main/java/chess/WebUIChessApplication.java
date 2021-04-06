package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import chess.controller.WebChessGame;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public/assets");

        WebChessGame chessGame = new WebChessGame();
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.start();
            return render(model, "index.html");
        });

        get("/chessboard", "application/json", (req, res) -> {
            JsonArray chessBoardArray = new JsonArray();
            for (Map.Entry<Position, Piece> board : chessGame.getChessBoard().entrySet()) {
                chessBoardArray.add(boardToJSON(board));
            }
            return chessBoardArray;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static JsonObject boardToJSON(Map.Entry<Position, Piece> board) {
        JsonObject square = new JsonObject();
        square.addProperty("id", board.getKey().getStringPosition());
        square.addProperty("position", board.getKey().getStringPosition());

        JsonObject piece = new JsonObject();
        piece.addProperty("type", board.getValue().getName());
        piece.addProperty("color", board.getValue().getColorAsString());

        square.add("piece", piece);
        return square;
    }
}
