package chess;

import static spark.Spark.*;

import chess.dto.CommandRequest;
import chess.dto.request.MoveRequest;
import chess.dto.response.BoardResult;
import chess.dto.response.PieceResult;
import chess.game.Command;
import chess.game.Game;
import chess.game.Position;
import chess.piece.Color;
import chess.piece.Piece;
import chess.status.Ready;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Map;
import java.util.stream.Collectors;

public class WebApplication {
    public static void main(String[] args) {
        port(8082);
        staticFileLocation("/static");

        final Game game = new Game(Ready.start(Command.START));

        get("/", (req, res) -> {
            final Map<Position, Piece> board = game.getBoard().getValue();
            return render(new BoardResult(board).getValue(), "index.html");
        });

        post("/move", "application/json", (req, res) -> {
            final String body = req.body();
            final Gson gson = new Gson();
            final MoveRequest moveRequest = gson.fromJson(body, MoveRequest.class);
            game.run(new CommandRequest("move", moveRequest.getFrom(), moveRequest.getTo()));
            res.redirect("/");
            return null;
        });

        get("/score", (req, res) -> {
            final Map<Color, Double> score = game.run(new CommandRequest("status"));
            final Map<String, Double> scoreResult = score.keySet()
                    .stream()
                    .collect(Collectors.toMap(Enum::name, score::get));
            return new ModelAndView(scoreResult, "index.html");
        }, new JsonTransformer());

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static String render(final Map<String, PieceResult> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
