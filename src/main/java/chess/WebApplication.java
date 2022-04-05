package chess;

import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.state.Ready;
import chess.dto.ResponseDto;
import chess.dto.ResultDto;
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
        final WebChessGame webChessGame = new WebChessGame(new Ready(initBoard));
        webChessGame.start();

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            final BoardDto boardDto = BoardDto.of(webChessGame.state());
            model.put("board", boardDto);

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            final String commandString = "move " + req.body();
            final Command command = Command.from(commandString);
            final ResponseDto responseDto = webChessGame.progress(command);

            return responseDto.toString();
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            final Command command = Command.from("status");
            webChessGame.progress(command);
            var score = webChessGame.state().status();
            model.put("score", score);

            return render(model, "index.html");
        });

        get("/end", (req, res) -> {
            final Command command = Command.from("end");
            webChessGame.progress(command);
            webChessGame.restart();

            return "게임종료되었습니다.";
        });

        get("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            final ResultDto result = ResultDto.of(webChessGame.state());
            model.put("result", result);

            return render(model, "result.html");
        });

        get("/restart", (req, res) -> {
            webChessGame.restart();
            res.redirect("/start");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
