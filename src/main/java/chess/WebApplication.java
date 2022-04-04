package chess;

import chess.controller.Command;
import chess.domain.ChessGame;
import chess.webview.WebOutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebApplication {
    public static void main(String[] args) {
        port(8082);
        staticFiles.location("/public");
        ChessGame chessGame = ChessGame.create();

        get("/welcome", (req, res) -> {
            Command command = Command.of(req.queryParams("command"));
            if (Command.START.equals(command)) {
                chessGame.initialze();
                System.out.println("@@@@@@@@@@@@startstart@@@@@@@@@@@@@@@@@");
                res.redirect("/board");
                return null;
            }
            if (Command.END.equals(command)) {
                stop();
            }
            return null;
        });

        get("/board", (req, res) -> {
            System.out.println("@@@@@@@@@@@@@@boardboardboard@@@@@@@@@@@@@@@@@@@@@@");
            return render(WebOutputView.makeBoardModel(chessGame.getChessBoardInformation()), "board.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
