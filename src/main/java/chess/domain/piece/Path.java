package chess.domain.piece;

import chess.domain.Board;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Position> positions;

    private Path(List<Position> positions) {
        this.positions = positions;
    }

    public static Path of(Piece piece, Board board) {
        final List<Position> dummy = new ArrayList<>();
        // todo : 피스가 보드에서 움직일 수 있는 모든 위치 더미에 넣기
        return new Path(dummy);
    }

    public boolean isAble(Position position) {
        return positions.contains(position);
    }
}
