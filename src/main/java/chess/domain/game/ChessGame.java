package chess.domain.game;

import java.util.Map;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.game.state.FinishedState;
import chess.domain.game.state.GameState;
import chess.domain.game.state.RunningState;
import chess.domain.player.Players;

public class ChessGame {

    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Long TEMPORARY_PLAYER_ID = 0L;

    private final Long id;
    private GameState gameState;

    private ChessGame(final Long id, final GameState gameState) {
        this.id = id;
        this.gameState = gameState;
    }

    public static ChessGame initializeChessGame() {
        return new ChessGame(TEMPORARY_PLAYER_ID, new FinishedState(new Players(), DEFAULT_COLOR));
    }

    public static ChessGame loadChessGame(final Long id, final Players players,
                                          final boolean finished, final Color currentTurnColor) {
        if (finished) {
            return new ChessGame(id, new FinishedState(players, currentTurnColor));
        }
        if (players.isPlayerAbleToPromotePawn(currentTurnColor.reverse())) {
            return new ChessGame(id, new RunningState(players, currentTurnColor.reverse()));
        }
        return new ChessGame(id, new RunningState(players, currentTurnColor));
    }

    public static ChessGame initializeAndStartChessGame() {
        final ChessGame chessGame = initializeChessGame();
        chessGame.start();
        return chessGame;
    }

    public void start() {
        validateGameAvailableToStart();
        gameState = RunningState.createFirstTurnRunning();
    }

    private void validateGameAvailableToStart() {
        if (gameState.isRunning()) {
            throw new IllegalStateException("게임이 이미 실행중입니다.");
        }
    }

    public void movePiece(final Position source, final Position target) {
        final RunningState runningState = convertToRunningState(gameState);
        gameState = runningState.move(source, target);
    }

    public void promotePiece(final String pieceName) {
        final RunningState runningState = convertToRunningState(gameState);
        gameState = runningState.promotion(pieceName);
    }

    public Map<Color, Double> getPlayerScores() {
        return gameState.getPlayerScores(new ScoreCalculator());
    }

    public void end() {
        gameState = gameState.end();
    }

    public boolean isRunning() {
        return gameState.isRunning();
    }

    public boolean isFinished() {
        return !isRunning();
    }

    public boolean isPromotable() {
        final RunningState runningState = convertToRunningState(gameState);
        return runningState.isPromotable();
    }

    private RunningState convertToRunningState(final GameState gameState) {
        validateGameIsRunning();
        return (RunningState) gameState;
    }

    private void validateGameIsRunning() {
        if (!gameState.isRunning()) {
            throw new IllegalStateException("게임이 시작되지 않았습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public Players getPlayers() {
        return gameState.getPlayers();
    }

    public Color getColorOfCurrentTurn() {
        return gameState.getColor();
    }
}
