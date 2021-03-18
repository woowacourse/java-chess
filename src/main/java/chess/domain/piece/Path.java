package chess.domain.piece;

import chess.domain.Board;
import chess.domain.piece.strategy.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Path {

    private final List<Position> positions;

    private Path(List<Position> positions) {
        this.positions = positions;
    }

    public static Path of(Piece piece, Board board) {
        final List<Position> dummy = new ArrayList<>();
        // todo : 피스가 보드에서 움직일 수 있는 모든 위치 더미에 넣기
        for (Direction direction : piece.allowedDirection()) {
            Position currentPosition = board.getCoordinates().get(piece);
            Position nextPosition = currentPosition.moveTo(direction);
            while(board.isEmpty(nextPosition)){
                dummy.add(nextPosition);
                nextPosition = nextPosition.moveTo(direction);
            }
            if(piece.isEnemy(board.findPieceBy(nextPosition).get())){
                dummy.add(nextPosition);
            }
        }
        return new Path(dummy);
    }

    public boolean isAble(Position position) {
//        return positions.contains(position);
        return true;
    }
}
