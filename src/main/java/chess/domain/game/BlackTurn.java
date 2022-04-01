package chess.domain.game;

import static chess.domain.board.piece.Color.BLACK;

import chess.domain.board.piece.Color;
import chess.domain.board.Board;
import java.util.Objects;

final class BlackTurn extends Running {

    BlackTurn(Board board) {
        super(board);
    }

    @Override
    public Color getCurrentTurnColor() {
        return BLACK;
    }

    @Override
    protected Game continueGame() {
        return new WhiteTurn(board);
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
