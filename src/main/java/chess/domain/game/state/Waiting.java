package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public class Waiting implements GameState {

    @Override
    public GameState movePiece(Board board, Position fromPosition, Position toPosition) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean isWaiting() {
        return true;
    }

    @Override
    public double calculateScore(Board board, Color color) {
        throw new IllegalArgumentException();
    }

    @Override
    public Color getWinTeamColor(Board board) {
        throw new IllegalArgumentException();
    }
}
