package chess;

import chess.controller.Command;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.web.Response;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");

        ChessGame chessGame = new ChessGame();
        Command.execute("start", chessGame);

        get("/", (req, res) -> {
            Response response = Response.success(chessGame.getBoard());
            return render(response, "index.html");
        });

        post("/move", (req, res) -> {
            Response response;
            try {
                String commands = getCommands(req.body());
                Command.execute(commands, chessGame);
                response = Response.success(chessGame.getBoard());
            } catch (Exception exception) {
                response = Response.exception(chessGame.getBoard(), exception.getMessage());
            }
            return render(response, "index.html");
        });
    }

    private static String render(final Response response, final String templatePath) {
        return new HandlebarsTemplateEngine()
                .render(new ModelAndView(response, templatePath));
    }

    private static String getCommands(final String body) {
        if (body == null) {
            return "";
        }
        String[] commands = convert(body).split(":|=");
        if (commands.length < 2) {
            return "";
        }
        return commands[1];
    }

    private static String convert(final String string) {
        return string.replace("\"", "")
                .replace("{", "")
                .replace("}", "")
                .replace("+", " ");
    }
}
