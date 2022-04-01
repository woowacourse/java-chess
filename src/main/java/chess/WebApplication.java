package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.domain.Board;
import chess.domain.ChessBoard;
import chess.domain.generator.InitBoardGenerator;
import chess.web.dto.BoardResponse;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        ChessBoard chessBoard = new ChessBoard(new InitBoardGenerator());
        Board board = chessBoard.getBoard();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("pieces", new BoardResponse(board).getValue());
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
