package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public class Running implements GameState {

    @Override
    public GameState movePiece(Board board, Position fromPosition, Position toPosition) {
        board.movePiece(fromPosition, toPosition);
        if (!board.isAllKingExist()) {
            return new End();
        }
        return new Running();
    }

    @Override
    public boolean isFinish() {
        return false;
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
        throw new IllegalArgumentException();
    }
}
