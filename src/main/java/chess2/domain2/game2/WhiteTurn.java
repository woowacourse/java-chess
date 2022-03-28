package chess2.domain2.game2;


import static chess2.domain2.board2.piece2.Color.WHITE;

import chess2.domain2.board2.piece2.Color;
import chess2.domain2.board2.Board;

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
    public String toString() {
        return "WhiteTurn{" + "board=" + board + '}';
    }
}
