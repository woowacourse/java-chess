package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/templates");
        port(8080);

        get("/", (req, res) -> render(new HashMap<>(), "start.html"));
        get("/chess", ChessService::makeChessBoard);
        get("/restart", ChessService::restartChess);
        post("/user", ChessService::login);
        post("/signup", ChessService::signUp);
        get("/adduser", (req, res) -> render(new HashMap<>(), "form.html"));
        post("/board", ChessService::matchBoardImageSouce);
        post("/addboard", ChessService::addBoard);
        post("/piece", ChessService::matchPieceName);
        post("/move", ChessService::moveRequest);
        post("/color", ChessService::makeCurrentColor);
        post("/turn", ChessService::makeNextColor);
        post("/score", ChessService::score);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}