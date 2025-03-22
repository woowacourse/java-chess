package chess.piece;

import chess.Board;
import chess.Color;
import chess.Position;

public class Empty extends Piece {
    public Empty(final Position position) {
        super(position, Color.EMPTY, PieceType.EMPTY);
    }

    @Override
    public Piece move(final Board board, final Position destination) {
        throw new IllegalArgumentException("비어 있는 칸입니다");
    }
}
