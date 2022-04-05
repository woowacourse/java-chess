package chess;

import static spark.Spark.*;

import chess.controller.GameController;
import chess.dto.BoardDto;
import chess.dto.ResponseDto;
import chess.dto.ResultDto;
import chess.game.Game;
import chess.piece.detail.Color;
import chess.status.Ready;
import chess.status.State;
import chess.view.Command;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        final Game game = new Game(ready());
        final GameController controller = new GameController();

        get("/start", (req, res) -> {
            final BoardDto boardDto = BoardDto.toDto(game.getBoard());
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDto);
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            final String command = "move " + req.body();
            final ResponseDto responseDto = progress(controller, game, command);
            return responseDto.toString();
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            final Map<Color, Double> result = game.getResult();
            model.put("result", result);
            return render(model, "index.html");
        });

        get("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            final ResultDto result = new ResultDto(game.getResult(), game.getWinColor());
            model.put("result", result);

            return render(model, "result.html");
        });

        get("/restart", (req, res) -> {
            game.restart();
            res.redirect("/start");
            return null;
        });
    }

    private static ResponseDto progress(final GameController controller, final Game game, final String command) {
        try {
            controller.move(game, command);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return new ResponseDto(400, e.getMessage(), game.isRunning());
        }

        if (!game.isRunning()) {
            return new ResponseDto(200, "", game.isRunning());
        }

        return new ResponseDto(200, "", game.isRunning());
    }

    private static State ready() {
        return Ready.start(Command.of("start"));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
