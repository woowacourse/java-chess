package chess;

import chess.webController.*;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("templates");
        StatusUrlController statusUrlController = new StatusUrlController();
        MoveUrlController moveUrlController = new MoveUrlController();
        ChessStartUrlController chessStartUrlController = new ChessStartUrlController();
        ChessUrlController chessUrlController = new ChessUrlController();

        get("/", (req, res) -> render(new HashMap<>(), "index.html"));
        post("/chessStart", (req, res) -> chessStartUrlController.initialChessBoard());
        get("/chess", (req, res) -> render(chessUrlController.gameSetting(),
                "contents/chess.html"));
        post("/move", (req, res) -> (moveChessPiece(req, moveUrlController)));
        post("/status", (req, res) -> statusUrlController.calculateScore());


        exception(MoveException.class, (exception, req, res) -> {
            res.status(403);
            res.body(exception.getMessage());
        });
    }

    private static Object moveChessPiece(Request req, MoveUrlController moveUrlController) {
        try {
            return moveUrlController.moveChessPiece(req.queryParams("source"), req.queryParams("target"));
        } catch (Exception e) {
            throw new MoveException(e.getMessage());
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
