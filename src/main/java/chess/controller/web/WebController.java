package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.RoomDto;
import chess.controller.dto.ScoresDto;
import chess.domain.board.position.Position;
import chess.service.GameService;
import chess.service.RequestHandler;
import chess.service.RoomService;
import chess.view.web.OutputView;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {

    private final GameService gameService;
    private final RoomService roomService;

    public WebController(Connection connection) {
        this.gameService = new GameService(connection);
        this.roomService = new RoomService(connection);
    }

    public void mapping() {
        roomList();
        create();
        load();
        show();
        move();
    }

    private void roomList() {
        get("/mainPage", (req, res) ->
                OutputView.printRoomList(roomService.loadList()));
    }

    private void create() {
        get("/create/:roomName", (req, res) -> {
            final String roomName = RequestHandler.roomName(req);
            final Long roomId = roomService.save(roomName);

            gameService.create(roomId);
            res.redirect("/load/" + roomName);
            return null;
        });
    }

    private void load() {
        get("/load/:roomName", (req, res) -> {
            final String roomName = RequestHandler.roomName(req);
            final Long roomId = roomService.roomId(roomName);
            return printGame(roomId);
        });
    }

    private void show() {
        post("/show/:roomName", (req, res) -> {
            final String roomName = RequestHandler.roomName(req);
            final Long roomId = roomService.roomId(roomName);
            final Position source = RequestHandler.source(req);
            return gameService.show(roomId, source);
        });
    }

    private void move() {
        post("/move/:roomName", (req, res) -> {
            final String roomName = RequestHandler.roomName(req);
            final Long roomId = roomService.roomId(roomName);

            final Position source = RequestHandler.source(req);
            final Position target = RequestHandler.target(req);
            gameService.move(roomId, source, target);

            System.out.println(gameService.isGameEnd(roomId));
            return printGame(roomId);
        });
    }

    private String printGame(final Long roomId) throws SQLException {
        if (gameService.isGameEnd(roomId)) {
            System.out.println("ffff");
            return OutputView.printResult(gameService.winner(roomId));
        }

        final ScoresDto scoresDto = gameService.scores(roomId);
        final BoardDto boardDto = gameService.board(roomId);
        final RoomDto roomDto = roomService.room(roomId);

        System.out.println("fff : "+gameService.isGameEnd(roomId));

        return OutputView.printGame(roomDto, boardDto, scoresDto);
    }
}