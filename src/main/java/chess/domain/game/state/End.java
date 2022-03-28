package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public class End implements GameState {

    @Override
    public GameState movePiece(Board board, Position fromPosition, Position toPosition) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public boolean isWaiting() {
        return false;
    }

    @Override
    public double calculateScore(Board board, Color color) {
        return board.calculateScore(color);
    }

    @Override
    public Color getWinTeamColor(Board board) {
        return board.getWinnerTeamColor();
    }
}
