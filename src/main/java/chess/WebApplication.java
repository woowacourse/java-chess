package chess;

import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.state.Ready;
import chess.dto.ResponseDto;
import chess.dto.board.BoardDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFiles.location("/static");

        final Board initBoard = new BoardInitializer().init();
        final ChessController chessController = new ChessController(new Ready(initBoard));
        chessController.start();

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            final BoardDto boardDto = BoardDto.of(chessController.state());
            model.put("board", boardDto);
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            final String commandString = "move " + req.body();
            final Command command = Command.from(commandString);
            final ResponseDto responseDto = chessController.progress(command);
            return responseDto.toString();
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            final Command command = Command.from("status");
            chessController.progress(command);
            var score = chessController.state().status();

            model.put("score", score);
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
