package chess.web;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.web.dto.BoardDto;
import chess.web.utils.Request;
import chess.web.utils.Response;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");
        ChessGame chessGame = new ChessGame();
        BoardDto boardDto = new BoardDto(new HashMap<>());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Response.putChessGameData(model, boardDto, chessGame);
            return render(model, "/index.html");
        });

        get("/start", (req, res) -> {
            boardDto.setBoard(chessGame.start());
            res.redirect("/");
            return null;
        });

        post("/move", (req, res) -> {
            boardDto.setBoard(Request.move(chessGame, req.queryParams("command")));
            res.redirect("/");
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
