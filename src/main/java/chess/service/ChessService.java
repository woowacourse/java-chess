package chess.service;

import chess.dao.ChessDao;
import chess.domain.ChessBoardFactory;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final ChessDao chessDao;
    private final Map<RoomNumber, ChessGame> gameRooms;

    public ChessService(final ChessDao chessDao) {
        this.chessDao = chessDao;
        this.gameRooms = new HashMap<>();
    }

    public List<Integer> fetchAllRoomNumbers() {
        return chessDao.fetchAllRoomNumbers();
    }

    public void initialize(final RoomNumber roomNumber) {
        gameRooms.put(roomNumber, createGameRoom(roomNumber));
    }

    private ChessGame createGameRoom(final RoomNumber roomNumber) {
        if (roomNumber.isNewRoomNumber()) {
            return new ChessGame(ChessBoardFactory.create());
        }
        return chessDao.fetchGame(roomNumber);
    }

    public boolean isGameRunning(final RoomNumber roomNumber) {
        return findGame(roomNumber).isNotEnd();
    }

    public void start(final RoomNumber roomNumber) {
        findGame(roomNumber).initialize();
    }

    public void executeMove(final RoomNumber roomNumber, final Position source, final Position destination) {
        findGame(roomNumber).executeMove(source, destination);
    }

    public void end(final RoomNumber roomNumber) {
        findGame(roomNumber).end();
    }

    public boolean isFirstTurn(final RoomNumber roomNumber) {
        return findGame(roomNumber).isFirstTurn();
    }

    public void clearRoomIfKingDead(final RoomNumber roomNumber) {
        ChessGame chessGame = findGame(roomNumber);
        if (chessGame.isKingDead() && !roomNumber.isNewRoomNumber()) {
            chessDao.clearAll(roomNumber);
        }
    }

    private ChessGame findGame(final RoomNumber roomNumber) {
        return gameRooms.get(roomNumber);
    }

    public double calculateScore(final RoomNumber roomNumber, final Team team) {
        return findGame(roomNumber).calculateScore(team);
    }

    public Team findWinningTeam(final RoomNumber roomNumber) {
        return findGame(roomNumber).findWinningTeam();
    }

    public ChessGame getGame(final RoomNumber roomNumber) {
        return findGame(roomNumber);
    }
}
