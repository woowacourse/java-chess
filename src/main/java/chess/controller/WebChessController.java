package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.game.ChessGame;
import chess.domain.game.StatusCalculator;
import chess.domain.piece.Team;
import chess.dto.GameDto;
import chess.service.BoardService;
import chess.service.GameService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final BoardService boardService = new BoardService(new BoardDao());
    private final GameService gameService = new GameService(new GameDao());

    public void run() {
        port(8081);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            return new ModelAndView(null, "start.html");
        }, new HandlebarsTemplateEngine());

        get("/game/:id", (req, res) -> {
            int gameId = Integer.parseInt(req.params(":id"));
            Board board = boardService.findBoard(gameId);
            Map<String, Object> map = new HashMap<>();
            map.putAll(board.toMap());
            map.put("id", Integer.toString(gameId));
            return new ModelAndView(map, "index.html");
        }, new HandlebarsTemplateEngine());

        post("/game/:id/move", (req, res) -> {
            String from = req.queryParams("from");
            String to = req.queryParams("to");

            int gameId = Integer.parseInt(req.params(":id"));
            Board board = boardService.findBoard(gameId);
            GameDto gameDto = gameService.findById(gameId);

            ChessGame chessGame = new ChessGame(board, gameDto.getState());
            chessGame.move(Coordinate.of(from), Coordinate.of(to));
            if (chessGame.isFinished()) {
                gameService.delete(gameId);
                res.redirect("/");
                return null;
            }

            gameService.changeTurn(gameDto.getState(), gameId);
            boardService.update(board.findPiece(Coordinate.of(from)), from, gameId);
            boardService.update(board.findPiece(Coordinate.of(to)), to, gameId);
            res.redirect("/game/" + gameId);
            return null;
        });

        post("/start", (req, res) -> {
            String whiteUserName = req.queryParams("white_user_name");
            String blackUserName = req.queryParams("black_user_name");
            int gameId = gameService.findGameId(whiteUserName, blackUserName);

            if (gameId == 0) {
                gameService.save(whiteUserName, blackUserName, Team.WHITE.name());
                Board board = Board.create();
                gameId = gameService.findGameId(whiteUserName, blackUserName);
                boardService.save(board.toMap(), gameId);
            }

            res.redirect("/game/" + gameId);
            return null;
        });

        post("/stop", (req, res) -> {
            res.redirect("/");
            return null;
        });

        post("/game/:id/end", (req, res) -> {
            int gameId = Integer.parseInt(req.params(":id"));
            gameService.delete(gameId);
            res.redirect("/");
            return null;
        });

        get("/game/:id/status", (req, res) -> {
            int gameId = Integer.parseInt(req.params(":id"));
            Board board = boardService.findBoard(gameId);
            GameDto gameDto = gameService.findById(gameId);

            ChessGame chessGame = new ChessGame(board, gameDto.getState());
            StatusCalculator status = chessGame.status();

            Map<String, Object> map = new HashMap<>();
            map.putAll(status.createStatus());
            map.putAll(chessGame.getBoard().toMap());
            map.put("id", Integer.toString(gameId));
            return new ModelAndView(map, "index.html");
        }, new HandlebarsTemplateEngine());

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }
}
