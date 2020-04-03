package chess;

import chess.controller.dto.ChessBoardDto;
import chess.domain.chesspiece.Piece;
import chess.domain.game.ChessBoard;
import chess.domain.game.PieceFactory;
import chess.domain.position.Position;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        staticFiles.location("templates");
//        staticFiles.location("./public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/test", (req, res) -> {
            Map<Position, Piece> chessBoards = new ChessBoard(PieceFactory.create())
                    .getChessBoard();
            return gson.toJson(new ChessBoardDto(chessBoards));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
