package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.RoomDto;
import chess.controller.dto.ScoresDto;
import chess.domain.board.position.Position;
import chess.service.GameService;
import chess.service.RequestHandler;
import chess.service.RoomService;
import chess.view.web.OutputView;
import spark.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

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
        list();
        create();
        load();
        show();
        move();
    }

    private void list() {
        get("/mainPage", (req, res) ->
                OutputView.printRoomList(roomService.loadList()));
    }

    private void create() {
        get("/create/:roomName", (req, res) -> {
            final Long roomId = roomService.save(req);
            gameService.create(roomId);
            res.redirect("/load/" + roomName(req));
            return null;
        });
    }

    private void load() {
        get("/load/:roomName", (req, res) -> {
            final Long roomId = roomService.roomId(req);
            return printGame(roomId);
        });
    }

    private void show() {
        post("/show/:roomName", (req, res) -> {
            final Long roomId = roomService.roomId(req);
            final Map<String, String> params = RequestHandler.parse(req);
            final Position source = new Position(params.get("source"));
            return gameService.show(roomId, source);
        });
    }

    private void move() {
        post("/move/:roomName", (req, res) -> {
            final Long roomId = roomService.roomId(req);
            final Map<String, String> params = RequestHandler.parse(req);
            final Position source = new Position(params.get("source"));
            final Position target = new Position(params.get("target"));
            gameService.move(roomId, source, target);
            return printGameResult(roomId);
        });
    }

    private String printGameResult(final Long roomId) throws SQLException {
        if (gameService.isGameEnd(roomId)) {
            return OutputView.printResult(gameService.winner(roomId));
        }
        return printGame(roomId);
    }

    private String printGame(final Long roomId) throws SQLException {
        final ScoresDto scoresDto = gameService.scores(roomId);
        final BoardDto boardDto = gameService.board(roomId);
        final RoomDto roomDto = roomService.room(roomId);
        return OutputView.printGame(roomDto, boardDto, scoresDto);
    }

    private String roomName(final Request req) {
        return req.params(":roomName");
    }
}
