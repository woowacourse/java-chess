package chess.domain.game;

import java.util.Map;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.game.command.MoveCommand;
import chess.domain.game.command.PromotionCommand;
import chess.domain.game.state.FinishedState;
import chess.domain.game.state.GameState;
import chess.domain.game.state.MoveState;
import chess.domain.game.state.RunningState;
import chess.domain.player.Players;

public class ChessGame {

    private GameState gameState;

    public ChessGame() {
        gameState = new FinishedState(new Players());
    }

    public void start() {
        validateGameAvailableToStart();
        gameState = MoveState.createFirstTurnRunning();
    }

    private void validateGameAvailableToStart() {
        if (gameState.isRunning()) {
            throw new IllegalStateException("게임이 이미 실행중입니다.");
        }
    }

    public void movePiece(final Position source, final Position target) {
        final RunningState runningState = convertToRunningState(gameState);
        final MoveCommand moveCommand = MoveCommand.of(runningState, source, target);
        gameState = moveCommand.execute();
    }

    public void promotePiece(final String pieceInitial) {
        final RunningState runningState = convertToRunningState(gameState);
        final PromotionCommand promotionCommand = PromotionCommand.of(runningState, pieceInitial);
        gameState = promotionCommand.execute();
    }

    public Map<Color, Double> getPlayerScores() {
        final RunningState runningState = convertToRunningState(gameState);
        return runningState.getPlayerScores(new ScoreCalculator());
    }

    public void end() {
        gameState = new FinishedState(gameState.getPlayers());
    }

    public boolean isRunning() {
        return gameState.isRunning();
    }

    public boolean isPromotable() {
        final RunningState runningState = convertToRunningState(gameState);
        return runningState.isPromotable();
    }

    public Players getPlayers() {
        return gameState.getPlayers();
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
}
