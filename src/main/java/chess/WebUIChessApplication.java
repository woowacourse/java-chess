package chess;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        final ChessBoard chessBoard = new ChessBoard();
        Map<Position, Piece> chessBoard1 = chessBoard.getChessBoard();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            for(Position position: chessBoard1.keySet()){
                model.put(position.position(), chessBoard1.get(position).getPieceName());
            }
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
