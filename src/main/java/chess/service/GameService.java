package chess.service;

import java.util.List;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.dao.GameStateGenerator;
import chess.domain.board.Board;
import chess.domain.board.Route;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.dto.Arguments;

public class GameService {

    private final GameDao gameDao;
    private final BoardDao boardDao;

    public GameService(GameDao gameDao, BoardDao boardDao) {
        this.gameDao = gameDao;
        this.boardDao = boardDao;
    }

    public void createNewGame(String roomName) {
        if (gameDao.readStateAndColor(roomName) != null) {
            throw new IllegalArgumentException(String.format("[ERROR] %s 이름의 방이 이미 존재합니다.", roomName));
        }

        GameState state = new Ready();
        gameDao.saveGame(state.getState(), state.getColor(), roomName);
        boardDao.saveBoard(state.getPointPieces(), roomName);
    }

    public void startGame(String roomName) {
        GameState state = readGameState(roomName).start();
        gameDao.updateState(state.getState(), state.getColor(), roomName);
    }

    public void finishGame(String roomName) {
        GameState state = readGameState(roomName).finish();
        gameDao.updateState(state.getState(), state.getColor(), roomName);
    }

    public GameState readGameState(String roomName) {
        Board board = boardDao.readBoard(roomName);
        List<String> stateAndColor = gameDao.readStateAndColor(roomName);

        return GameStateGenerator.generate(board, stateAndColor);
    }

    public GameState moveBoard(String roomName, Arguments arguments) {
        GameState state = readGameState(roomName);
        GameState moved = state.move(arguments);
        gameDao.updateState(state.getState(), state.getColor(), roomName);

        Route route = Route.of(arguments);
        boardDao.deletePiece(route.getDestination(), roomName);
        boardDao.updatePiece(route, roomName);
        return moved;
    }
}
