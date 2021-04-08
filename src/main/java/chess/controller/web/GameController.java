package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.RoomDto;
import chess.controller.dto.ScoresDto;
import chess.domain.piece.Owner;
import chess.service.GameService;
import chess.service.RequestHandler;
import chess.service.RoomService;
import chess.view.web.OutputView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class GameController {

    private final RoomService roomService;
    private final GameService gameService;

    public GameController(Connection connection) {
        this.roomService = new RoomService(connection);
        this.gameService = new GameService(connection);
    }

    public void mapping() {
        createGame();
        deleteGame();
        loadGame();
        show();
        move();
    }

    private void createGame() {
        get("/game/create/:roomId", (req, res) -> {
            final Long roomId = RequestHandler.roomId(req);
            gameService.create(roomId);

            res.status(200);
            res.redirect("/game/load/" + roomId);
            return null;
        });
    }

    private void deleteGame() {
        get("/game/delete/:roomId", (req, res) -> {
            gameService.delete(RequestHandler.roomId(req));

            res.status(200);
            res.redirect("/main");
            return null;
        });
    }

    private void loadGame() {
        get("/game/load/:roomId", (req, res) -> {
            res.status(200);
            return printGame(RequestHandler.roomId(req));
        });
    }

    private void show() {
        post("/game/show/:roomId", (req, res) -> {
            res.status(200);
            return gameService.show(RequestHandler.roomId(req), RequestHandler.source(req));
        });
    }

    private void move() {
        post("/game/move/:roomId", (req, res) -> {
            final Long roomId = RequestHandler.roomId(req);
            gameService.move(roomId, RequestHandler.source(req), RequestHandler.target(req));

            res.status(200);
            return printGame(roomId);
        });
    }

    private String printGame(final Long roomId) throws SQLException {
        if (gameService.isGameEnd(roomId)) {
            final List<Owner> winner = gameService.winner(roomId);
            roomService.delete(roomId);
            gameService.delete(roomId);
            return OutputView.printResult(winner);
        }

        final RoomDto roomDto = roomService.room(roomId);
        final BoardDto boardDto = gameService.board(roomId);
        final ScoresDto scoresDto = gameService.scores(roomId);
        return OutputView.printGame(roomDto, boardDto, scoresDto);
    }
}