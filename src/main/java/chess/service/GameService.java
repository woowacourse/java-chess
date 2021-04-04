package chess.service;

import chess.controller.dto.GameDto;
import chess.dao.GameDao;
import chess.dao.RoomDao;
import chess.domain.ChessGame;
import chess.domain.board.position.Position;
import spark.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GameService {

    private ChessGame chessGame;
    private final GameDao gameDao;
    private final RoomDao roomDao;

    public GameService(Connection connection) {
        this.gameDao = new GameDao(connection);
        this.roomDao = new RoomDao(connection);
    }

    public void createGame(final Request req) throws SQLException {
        final Long roomId = System.currentTimeMillis();
        roomDao.save(roomName(req), roomId);

        chessGame = ChessGame.initNew();
        gameDao.save(roomId, chessGame.turn(), chessGame.board());
    }


    public List<String> show(final Request req) throws SQLException {
        chessGame = loadChessGame(req);

        final Map<String, String> requestParams = RequestHandler.parse(req);
        final Position source = new Position(requestParams.get("source"));
        return chessGame.reachablePositions(source);
    }

    public void move(Request request) throws SQLException {
        chessGame = loadChessGame(request);

        final Map<String, String> requestParams = RequestHandler.parse(request);
        final Position source = new Position(requestParams.get("source"));
        final Position target = new Position(requestParams.get("target"));
        chessGame.move(source, target);

        gameDao.save(roomId(request), chessGame.turn(), chessGame.board());
    }

    public ChessGame loadChessGame(final Request req) throws SQLException {
        final GameDto gameDto = gameDao.load(roomId(req));
        return ChessGame.load(gameDto.getBoard(), gameDto.getTurn());
    }

    private String roomName(final Request req){
        return req.params(":roomName");
    }

    private Long roomId(final Request req) throws SQLException {
        return roomDao.id(roomName(req));
    }
}
