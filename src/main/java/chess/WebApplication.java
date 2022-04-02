package chess;

import static spark.Spark.*;

import chess.domain.board.BasicChessBoardGenerator;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.web.MoveCommand;
import chess.web.RequestToCommand;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        port(8089);
        staticFileLocation("/static");
        Board board = BasicChessBoardGenerator.generator();
        var ref = new Object() {
            Color turn = Color.WHITE;
        };

        get("/", (req, res) -> {
            Map<String, Object> model = board.toMap();
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            final MoveCommand command = RequestToCommand.toMoveCommand(req.body());
            board.move(command.getSourcePosition(), command.getDestinationPosition(), ref.turn);
            ref.turn = ref.turn.not();
            res.redirect("/");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
