package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.JsonTransformer;
import chess.command.Command;
import chess.controller.dto.request.MoveRequest;
import chess.controller.dto.response.BoardResponse;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    public void run() {
        final ChessGame chessGame = new ChessGame(new Board(new CreateCompleteBoardStrategy()));
        final JsonTransformer jsonTransformer = new JsonTransformer();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        get("/api/start", (req, res) -> {
            chessGame.start();
            return new BoardResponse(chessGame.getBoard());
        }, jsonTransformer);

        post("/api/move", (req, res) -> {
            MoveRequest moveRequest = new Gson().fromJson(req.body(), MoveRequest.class);
            Command move = moveRequest.toCommand();
            move.execute(chessGame);
            return new BoardResponse(chessGame.getBoard());
        }, jsonTransformer);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
