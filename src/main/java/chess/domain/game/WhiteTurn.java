package chess.domain.game;


import static chess.domain.board.piece.Color.WHITE;

import chess.domain.board.piece.Color;
import chess.domain.board.Board;
import java.util.Objects;

final class WhiteTurn extends Running {

    WhiteTurn(Board board) {
        super(board, GameState.WHITE_TURN);
    }

    @Override
    public Color getCurrentTurnColor() {
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
