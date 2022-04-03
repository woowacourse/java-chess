package chess.controller;

import chess.dto.CommandRequest;
import chess.dto.request.MoveRequest;
import chess.dto.response.BoardResult;
import chess.dto.response.PieceResult;
import chess.game.Command;
import chess.game.Game;
import chess.game.Position;
import chess.piece.Color;
import chess.piece.Piece;
import chess.state.Ready;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessController {

    private final Game game;
    private final Gson gson;

    public ChessController() {
        game = new Game(Ready.start(Command.START));
        gson = new Gson();
    }

    public Route home() {
        return (req, res) -> new HandlebarsTemplateEngine().render(new ModelAndView(null, "index.html"));
    }

    public Route move() {
        return (req, res) -> {
            final MoveRequest moveRequest = gson.fromJson(req.body(), MoveRequest.class);
            game.run(new CommandRequest("move", moveRequest.getFrom(), moveRequest.getTo()));
            res.redirect("/game");
            return null;
        };
    }

    public Route game() {
        final Map<Position, Piece> board = game.getBoard().getValue();
        return (req, res) -> render(new BoardResult(board).getValue(), "game.html");
    }

    public Route score() {
        final Map<Color, Double> score = game.run(new CommandRequest("status"));
        final Map<String, Double> scoreResult = score.keySet()
                .stream()
                .collect(Collectors.toMap(Enum::name, score::get));
        return (req, res) -> new ModelAndView(scoreResult, "game.html");
    }

    private static String render(final Map<String, PieceResult> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

