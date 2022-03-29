package chess2.domain2.game2;

import static chess2.domain2.board2.piece2.Color.BLACK;

import chess2.domain2.board2.piece2.Color;
import chess2.domain2.board2.Board;
import java.util.Objects;

final class BlackTurn extends Running {

    BlackTurn(Board board) {
        super(board);
    }

    @Override
    protected Color currentTurnColor() {
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
