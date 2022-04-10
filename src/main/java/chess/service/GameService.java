package chess.service;

import java.util.List;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.dao.GameStateGenerator;
import chess.database.BoardDto;
import chess.database.GameStateDto;
import chess.database.PointDto;
import chess.database.RouteDto;
import chess.domain.board.Board;
import chess.domain.board.CustomBoardGenerator;
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

        gameDao.saveGame(GameStateDto.of(state), roomName);
        boardDao.saveBoard(BoardDto.of(state.getPointPieces()), roomName);
    }

    public void startGame(String roomName) {
        GameState state = readGameState(roomName).start();
        gameDao.updateState(GameStateDto.of(state), roomName);
    }

    public void finishGame(String roomName) {
        GameState state = readGameState(roomName).finish();
        gameDao.updateState(GameStateDto.of(state), roomName);
    }

    public GameState readGameState(String roomName) {
        BoardDto boardDto = boardDao.readBoard(roomName);
        Board board = Board.of(new CustomBoardGenerator(boardDto));
        List<String> stateAndColor = gameDao.readStateAndColor(roomName);

        return GameStateGenerator.generate(board, stateAndColor);
    }

    public GameState moveBoard(String roomName, Arguments arguments) {
        GameState movedState = readGameState(roomName).move(arguments);
        gameDao.updateState(GameStateDto.of(movedState), roomName);

        Route route = Route.of(arguments);
        boardDao.deletePiece(PointDto.of(route.getDestination()), roomName);
        boardDao.updatePiece(RouteDto.of(route), roomName);
        return movedState;
    }
}
