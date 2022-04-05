package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessGame;
import chess.domain.TeamScore;
import chess.domain.command.Command;
import chess.domain.command.GameCommand;
import chess.view.BoardDto;
import chess.view.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {

        port(8081);
        staticFileLocation("/static");
        ChessGame chessGame = new ChessGame();

        initialGame();
        getBoard(chessGame);
        startGame(chessGame);
        move(chessGame);
        getStatus(chessGame);
    }

    private static void initialGame() {
        get("/", (req, res) -> {
            Map<String, PieceDto> model = new HashMap<>();
            return render(model, "initial.html");
        });
    }

    private static void startGame(ChessGame chessGame) {
        get("/start", (req, res) -> {
            chessGame.start();
            res.redirect("/board");
            return null;
        });
    }

    private static void getBoard(ChessGame chessGame) {
        get("/board", (req, res) -> {
            Map<String, PieceDto> model = BoardDto.of(chessGame.getBoard()).getBoardData();
            return render(model, "board.html");
        });
    }

    private static void move(ChessGame chessGame) {
        post("/move", (req, res) -> {
            Command command = new Command(List.of(req.queryParams("command").split(" ")));
            execute(chessGame, command);
            Map<String, PieceDto> model = BoardDto.of(chessGame.getBoard()).getBoardData();
            return render(model, "board.html");
        });
    }

    private static String getStatus(ChessGame chessGame) {
        get("/status", (req, res) -> {
            TeamScore score = chessGame.getScore();
            return score.getTeam() + "팀 점수는" + score.getScore();
        });
        return " ";
    }

    public static void execute(ChessGame chessGame, Command command) {
        executeMove(chessGame, command);
        executeStatus(chessGame, command);
        executeEnd(chessGame, command);
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

    private static String render(Map<String, PieceDto> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
