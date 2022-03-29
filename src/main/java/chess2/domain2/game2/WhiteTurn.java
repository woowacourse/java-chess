package chess2.domain2.game2;


import static chess2.domain2.board2.piece2.Color.WHITE;

import chess2.domain2.board2.piece2.Color;
import chess2.domain2.board2.Board;
import java.util.Objects;

final class WhiteTurn extends Running {

    WhiteTurn(Board board) {
        super(board);
    }

    @Override
    protected Color currentTurnColor() {
        return WHITE;
    }

    @Override
    protected Game continueGame() {
        return new BlackTurn(board);
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
