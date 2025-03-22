package chess.domain.piece.limited_moving_chess_piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.ChessPiece;
import java.util.List;

public class None implements ChessPiece {

    protected final Color side;

    public None() {
        this.side = Color.EMPTY;
    }

    @Override
    public List<Movement> findRoute(Position origin, Position destination) {
        throw new IllegalStateException("움직일 말이 존재하지 않습니다.");
    }

    @Override
    public String name() {
        return "-";
    }

    @Override
    public void validateCanMove(List<Movement> route, boolean isExistHurdleOnRoute, ChessPiece targetPiece) {
        throw new IllegalStateException("움직일 말이 존재하지 않습니다.");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Color getColor() {
        return side;
    }

    @Override
    public void capture() {

    }

    @Override
    public boolean isCaptured() {
        return false;
    }
}
