package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Set;

public class Empty extends Piece {

    public Empty() {
        super(Color.NONE, PieceType.NONE, Collections.emptySet());
    }

    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        throw new UnsupportedOperationException("비어 있는 칸은 움직일 수 없습니다.");
    }
}
