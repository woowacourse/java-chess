package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.piece.Color;
import chess.domain.game.statistics.GameState;
import java.util.Objects;

final class BlackTurn extends Running {

    BlackTurn(Board board) {
        super(board);
    }

    @Override
    public Color getCurrentTurnColor() {
        return Color.BLACK;
    }

    @Override
    protected Game continueGame() {
        return new WhiteTurn(board);
    }

    @Override
    protected GameState getState() {
        return GameState.BLACK_TURN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlackTurn blackTurn = (BlackTurn) o;
        return Objects.equals(board, blackTurn.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }

    @Override
    public String toString() {
        return "BlackTurn{" + "board=" + board + '}';
    }
}
