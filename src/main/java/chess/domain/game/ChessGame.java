package chess.domain.game;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.domain.piece.Score;
import chess.domain.piece.Team;
import java.util.List;
import java.util.Map;

public final class ChessGame {

    private ChessBoard chessBoard;
    private GameState gameState;

    public ChessGame() {
        gameState = GameState.READY;
    }

    public void start() {
        validateGameIsStart();
        this.chessBoard = new ChessBoard();
        gameState = GameState.RUNNING;
    }

    private void validateGameIsStart() {
        if (gameState != GameState.READY) {
            throw new IllegalArgumentException("이미 게임이 시작하였습니다.");
        }
    }

    public void move(final Coordinate from, final Coordinate to) {
        validateGameIsRunning();

        chessBoard.move(from, to);

        if (anyKingDead(chessBoard.isKingAlive())) {
            this.end();
        }
    }

    private void validateGameIsRunning() {
        if (gameState != GameState.RUNNING) {
            throw new IllegalArgumentException("게임을 먼저 시작해 주세요.");
        }
    }

    private boolean anyKingDead(final Map<Team, Boolean> kingAliveInfo) {
        return kingAliveInfo.values().stream().anyMatch(value -> !value);
    }

    public void end() {
        gameState = GameState.END;
    }

    public boolean isGameEnd() {
        return gameState == GameState.END;
    }

    public Map<Team, Score> getScore() {
        validateGameIsRunning();

        final List<Square> whiteTeam = chessBoard.squaresByTeam(Team.WHITE);
        final List<Square> blackTeam = chessBoard.squaresByTeam(Team.BLACK);

        final long whiteTeamPawnContinuousCount = chessBoard.countFileOfContinuousPawnByTeam(Team.WHITE);
        final long blackTeamPawnContinuousCount = chessBoard.countFileOfContinuousPawnByTeam(Team.BLACK);

        final Score whiteTeamScore = calculateScore(whiteTeam, whiteTeamPawnContinuousCount);
        final Score blackTeamScore = calculateScore(blackTeam, blackTeamPawnContinuousCount);

        return Map.of(Team.WHITE, whiteTeamScore, Team.BLACK, blackTeamScore);
    }

    private Score calculateScore(final List<Square> teamSquares, long pawnContinuousFileCount) {
        Score score = teamSquares.stream()
                .map(Square::getScore)
                .reduce(Score.from(0), Score::add);

        return score.subtract(Score.from(0.5).multiply(pawnContinuousFileCount));
    }


    public ChessBoard getChessBoard() {
        return this.chessBoard;
    }
}
