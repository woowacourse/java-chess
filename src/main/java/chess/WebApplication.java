package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dao.BoardDao;
import chess.dao.TurnDao;
import chess.domain.ChessGame;
import chess.domain.TeamScore;
import chess.domain.command.Command;
import chess.service.ChessGameService;
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
        ChessGameService chessGameService = new ChessGameService(new BoardDao(), new TurnDao());

        initialGame();
        startGame(chessGameService);
        getBoard(chessGameService);
        move(chessGameService);
        getStatus(chessGameService);
        restart(chessGameService);
    }

    private static void initialGame() {
        get("/", (req, res) -> {
            Map<String, PieceDto> model = new HashMap<>();
            return render(model, "initial.html");
        });
    }

    private static void startGame(ChessGameService chessGameService) {
        get("/start", (req, res) -> {
            ChessGame chessGame = new ChessGame(chessGameService.getState());
            if (!chessGame.isRunning()) {
                chessGame.start();
            }
            chessGameService.save(chessGame.getBoard(), chessGame.getTeam());
            res.redirect("/board");
            return null;
        });
    }

    private static void getBoard(ChessGameService chessGameService) {
        get("/board", (req, res) -> {
            Map<String, PieceDto> model = chessGameService.getBoardData();
            return render(model, "board.html");
        });
    }

    private static void move(ChessGameService chessGameService) {
        post("/move", (req, res) -> {
            Command command = new Command(List.of("move", req.queryParams("source"), req.queryParams("target")));
            try {
                ChessGame chessGame = new ChessGame(chessGameService.getState());
                chessGame.move(command.getSourceLocation(), command.getTargetLocation());
                chessGameService.save(chessGame.getBoard(), chessGame.getTeam());
                checkGameEnd(chessGame, chessGameService);
            } catch (Exception exception) {
                return printException(exception.getMessage(), "exception.html");
            }
            res.redirect("/board");
            return null;
        });
    }

    private static void checkGameEnd(ChessGame chessGame, ChessGameService chessGameService) {
        if (!chessGame.isRunning()) {
            chessGameService.removeData();
            throw new IllegalArgumentException("게임 끝!");
        }
    }

    private static String getStatus(ChessGameService chessGameService) {
        get("/status", (req, res) -> {
            ChessGame chessGame = new ChessGame(chessGameService.getState());
            TeamScore score = chessGame.getScore();
            return score.getTeam() + "팀 점수는" + score.getScore();
        });
        return " ";
    }

    private static void restart(ChessGameService chessGameService) {
        post("/restart", (req, res) -> {
            ChessGame chessGame = new ChessGame(chessGameService.getState());
            if (chessGame.isRunning()) {
                chessGame.end();
            }
            chessGame.start();
            chessGameService.save(chessGame.getBoard(), chessGame.getTeam());
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
