package chess;

import chess.dao.SQLBoardDAO;
import chess.domain.board.Board;
import chess.webutil.WebRequest;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Board board = new Board(new SQLBoardDAO());

    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            WebRequest webRequest = WebRequest.BLANK_BOARD;
            return render(webRequest.generateModel(req, board), "index.html");
        });

        post("/", (req, res) -> {
            WebRequest webRequest = WebRequest.of(req.queryParams("command"));
            return render(webRequest.generateModel(req, board), "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
