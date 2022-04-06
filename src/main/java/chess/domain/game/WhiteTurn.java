package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.piece.Color;
import chess.domain.game.statistics.GameState;
import java.util.Objects;

final class WhiteTurn extends Running {

    WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public Color getCurrentTurnColor() {
        return Color.WHITE;
    }

    @Override
    protected Game continueGame() {
        return new BlackTurn(board);
    }

    @Override
    protected GameState getState() {
        return GameState.WHITE_TURN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WhiteTurn whiteTurn = (WhiteTurn) o;
        return Objects.equals(board, whiteTurn.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }

    @Override
    public String toString() {
        return "WhiteTurn{" + "board=" + board + '}';
    }
}
