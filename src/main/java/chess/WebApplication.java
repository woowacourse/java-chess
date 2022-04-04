package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.ChessGame;
import chess.domain.piece.ChessmenInitializer;
import chess.dto.CommandDto;
import chess.dto.MovePositionCommandDto;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {


    public static void main(String[] args) {
        port(8082);

        staticFiles.location("/static");

        ChessmenInitializer chessmenInitializer = new ChessmenInitializer();
        ChessGame game = ChessGame.of(chessmenInitializer.init());

        get("/", (req, res) -> {
            Map<String, Object> model = game.toBoard().getBoardMap();

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            String[] request = req.body().split("=");
            CommandDto commandDto = new CommandDto(request[1]);

            game.moveChessmen(new MovePositionCommandDto(commandDto.getFullCommand()));

            res.redirect("/");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
