package chess.service;

import static chess.domain.GameState.READY;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.GameRoom;
import chess.domain.GameState;
import chess.domain.Team;
import chess.repository.GameDao;
import chess.repository.JdbcTemplate;
import java.util.List;
import java.util.Map;

public class GameService {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private final GameDao gameDao = new GameDao(new JdbcTemplate());

    public GameRoom createGameRoom() {
        final ChessGame chessGame = new ChessGame(new Board(), WHITE);
        final int roomNumber = gameDao.saveGame(chessGame);
        gameDao.saveState(roomNumber, READY);
        return new GameRoom(roomNumber, chessGame, READY);
    }

    public GameRoom loadGameRoom(final int roomNumber) {
        final ChessGame chessGame = gameDao.findGameById(roomNumber);
        final GameState gameState = gameDao.findState(roomNumber);
        return new GameRoom(roomNumber, chessGame, gameState);
    }

    public void move(List<String> commands, GameRoom gameRoom) {
        final String source = commands.get(SOURCE_INDEX);
        final String target = commands.get(TARGET_INDEX);
        gameRoom.move(source, target);
    }

    public boolean isCheckmate(GameRoom gameRoom) {
        return gameDao.countKing(gameRoom.roomNumber()) < 2;
    }

    public List<Integer> findAllGameRooms() {
        return gameDao.findAllRooms();
    }

    public void updateGame(GameRoom gameRoom) {
        gameDao.deleteBoard(gameRoom.roomNumber());
        gameDao.saveBoard(gameRoom.roomNumber(), gameRoom.board());
        gameDao.deleteState(gameRoom.roomNumber());
        gameDao.saveState(gameRoom.roomNumber(), gameRoom.state());
        gameDao.updateTeamById(gameRoom.roomNumber(), gameRoom.turn());
    }

    public Map<String, Double> scores(GameRoom gameRoom) {
        final double whiteTeamScore = gameRoom.chessGame().calculateScoreBy(WHITE);
        final double blackTeamScore = gameRoom.chessGame().calculateScoreBy(BLACK);
        return Map.of(WHITE.name(), whiteTeamScore, BLACK.name(), blackTeamScore);
    }

    public List<Team> winningTeams(GameRoom gameRoom) {
        return gameRoom.winningTeams();
    }

    public void deleteAll(GameRoom gameRoom) {
        gameDao.deleteGameById(gameRoom.roomNumber());
        gameDao.deleteBoard(gameRoom.roomNumber());
        gameDao.deleteState(gameRoom.roomNumber());
    }
}
