package chess2.domain2.game2;

import static chess2.domain2.board2.piece2.Color.BLACK;

import chess2.domain2.board2.piece2.Color;
import chess2.domain2.board2.Board;

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
    public String toString() {
        return "BlackTurn{" + "board=" + board + '}';
    }
}
