package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.GameCommand;
import chess.view.BoardDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();

        initialGame();
        printBoard(chessGame);

    }

    private static void initialGame() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "initial.html");
        });
    }

    private static void printBoard(ChessGame chessGame) {
        post("/board", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Command command = new Command(List.of(req.queryParams("command").split(" ")));
            execute(chessGame, command);
            model.put("board", BoardDto.of(chessGame.getBoard()));
            return render(model, "board.html");
        });
    }

    public static void execute(ChessGame chessGame, Command command) {
        executeStart(chessGame, command);
        executeMove(chessGame, command);
        executeStatus(chessGame, command);
        executeEnd(chessGame, command);
    }

    private static void executeStart(ChessGame chessGame, Command command) {
        if (GameCommand.isStart(command.getGameCommand())) {
            chessGame.start();
        }
    }

    private static void executeMove(ChessGame chessGame, Command command) {
        if (GameCommand.isMove(command.getGameCommand())) {
            chessGame.move(command.getSourceLocation(), command.getTargetLocation());
        }
    }

    private static void executeStatus(ChessGame chessGame, Command command) {
        if (GameCommand.isStatus(command.getGameCommand())) {
            chessGame.status();
        }
    }

    private static void executeEnd(ChessGame chessGame, Command command) {
        if (GameCommand.isEnd(command.getGameCommand())) {
            chessGame.end();
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
