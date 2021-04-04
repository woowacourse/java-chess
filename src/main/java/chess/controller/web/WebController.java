package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.GameDto;
import chess.controller.dto.RoomStatusDto;
import chess.controller.dto.ScoresDto;
import chess.dao.GameDao;
import chess.dao.RoomDao;
import chess.domain.piece.Owner;
import chess.service.ChessGame;
import chess.service.RequestHandler;
import chess.view.web.OutputView;
import spark.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebController {

    private ChessGame chessGame;
    private final GameDao gameDao;
    private final RoomDao roomDao;

    public WebController(Connection connection) {
        this.gameDao = new GameDao(connection);
        this.roomDao = new RoomDao(connection);
    }

    public void mapping() {
        list();
        create();
        load();
        show();
        move();
    }

    private void list(){
        get("/mainPage", (req, res) ->
                OutputView.printRoomList(roomDao.load()));
    }

    private void create(){
        get("/create/:roomName", (req, res) -> {
            final String roomName = roomName(req);
            final long roomId = System.currentTimeMillis();
            roomDao.save(roomName, roomId);

            chessGame = ChessGame.initNew();
            gameDao.save(roomId, chessGame.turn(), chessGame.board());

            res.redirect("/load/"+roomName);
            return printGame(roomName);
        });
    }

    private void load() {
        get("/load/:roomName", (req, res) -> {
            chessGame = loadChessGame(req);
            return printGame(roomName(req));
        });
    }

    private void show() {
        post("/show/:roomName", (req, res) -> {
            chessGame = loadChessGame(req);
            final Map<String, String> requestParams = RequestHandler.parse(req.body());
            try {
                chessGame.validateTurn(requestParams);
                return chessGame.reachablePositions(requestParams);
            } catch (Exception e) {
                return Collections.EMPTY_LIST;
            }
        });
    }

    private void move() {
        post("/move/:roomName", (req, res) -> {
            final String roomName = roomName(req);
            final long roomId = roomDao.id(roomName);

            chessGame = loadChessGame(req);
            chessGame.move(RequestHandler.parse(req.body()));
            gameDao.save(roomId, chessGame.turn(), chessGame.board());

            if (chessGame.isGameEnd()) {
                return OutputView.printResult(chessGame.winner());
            }
            return printGame(roomName);
        });
    }

    private String printGame(final String roomName) {
        final ScoresDto scoresDto = new ScoresDto();
        final BoardDto boardDto = new BoardDto();
        final RoomStatusDto roomStatusDto = new RoomStatusDto();

        scoresDto.setWhiteScore(chessGame.score(Owner.WHITE));
        scoresDto.setBlackScore(chessGame.score(Owner.BLACK));
        boardDto.setBoard(chessGame.unicodeBoard());
        roomStatusDto.setName(roomName);
        roomStatusDto.setTurn(chessGame.turn().name());

        return OutputView.printGame(roomStatusDto,boardDto,scoresDto);
    }

    private ChessGame loadChessGame(final Request req) throws SQLException {
        final long roomId = roomDao.id(roomName(req));
        final GameDto gameDto = gameDao.load(roomId);
        return ChessGame.load(gameDto.getBoard(), gameDto.getTurn());
    }

    private String roomName(final Request req){
        return req.params(":roomName");
    }
}
