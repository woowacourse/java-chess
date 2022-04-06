package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessGame;
import chess.domain.TeamScore;
import chess.domain.command.Command;
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
        restart(chessGame);
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
            Command command = new Command(List.of("move", req.queryParams("source"), req.queryParams("target")));
            try {
                chessGame.move(command.getSourceLocation(), command.getTargetLocation());
                checkGameEnd(chessGame);
            } catch (Exception exception) {
                return printException(exception.getMessage(), "exception.html");
            }
            res.redirect("/board");
            return null;
        });
    }

    private static void checkGameEnd(ChessGame chessGame) {
        if (!chessGame.isRunning()) {
            throw new IllegalArgumentException("게임 끝!");
        }
    }

    private static String getStatus(ChessGame chessGame) {
        get("/status", (req, res) -> {
            TeamScore score = chessGame.getScore();
            return score.getTeam() + "팀 점수는" + score.getScore();
        });
        return " ";
    }

    private static void restart(ChessGame chessGame) {
        post("/restart", (req, res) -> {
            if (chessGame.isRunning()) {
                chessGame.end();
            }
            chessGame.start();
            res.redirect("/board");
            return null;
        });
    }

    private static String render(Map<String, PieceDto> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String printException(String model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
