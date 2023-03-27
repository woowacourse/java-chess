package chess.service;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.START;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

import chess.controller.Command;
import chess.repository.GameDao;
import chess.repository.JdbcTemplate;
import chess.domain.Board;
import chess.domain.ChessGame;
import java.util.List;
import java.util.Map;

public class GameService {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private ChessGame chessGame = new ChessGame(new Board(), WHITE);
    private final GameDao gameDao = new GameDao(new JdbcTemplate());

    public int saveGame() {
        return gameDao.saveGame(chessGame);
    }

    public void updateGame(final int gameId) {
        gameDao.updateGameById(chessGame, gameId);
    }

    public void findGame(final int gameId) {
        chessGame = gameDao.findGameById(gameId);
    }

    public Command start(List<String> commands) {
        Command command = Command.from(commands);
        if (command != START) {
            throw new IllegalArgumentException("start를 입력하여 게임을 실행하세요");
        }
        return command;
    }

    public Command move(List<String> commands) {
        chessGame.movePiece(commands.get(SOURCE_INDEX), commands.get(TARGET_INDEX));
        if (chessGame.isCheckmate()) {
            return END;
        }
        return MOVE;
    }

    public Map<String, Double> scores() {
        final double whiteTeamScore = chessGame.calculateScoreBy(WHITE);
        final double blackTeamScore = chessGame.calculateScoreBy(BLACK);
        return Map.of(WHITE.name(), whiteTeamScore, BLACK.name(), blackTeamScore);
    }

    public List<String> winningTeams() {
        return chessGame.determineWinningTeam();
    }

    public String teamName() {
        return chessGame.teamName();
    }

    public Board board() {
        return chessGame.getBoard();
    }
}
