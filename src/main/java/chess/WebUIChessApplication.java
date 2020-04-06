package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.webutil.ModelParser;
import chess.webutil.ParserMapper;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Board board;

    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            board = BoardFactory.createEmptyBoard();
            Map<String, Object> model = ModelParser.parseBoard(board);
            return render(model, "index.html");
        });

        post("/", (req, res) -> {
            ParserMapper parserMapper = ParserMapper.of(req.queryParams("command"));
            return render(parserMapper.generateModel(req, board), "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
