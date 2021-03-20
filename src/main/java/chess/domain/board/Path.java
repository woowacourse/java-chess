package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Position> positions;

    public Path(List<Position> positions) {
        this.positions = positions;
    }

    public boolean isAble(Position position) {
        if (positions.contains(position)) {
            return true;
        }
        throw new IllegalArgumentException("해당 말이 이동할 수 없는 위치입니다.");
    }

    public List<Position> removeObstacleInPath(Piece piece, Board board) {
        List<Position> cleanPath = new ArrayList<>();
        for (Position position : positions) {
            Piece thatPiece = board.findPieceBy(position);
            if (!piece.isEnemyOrEmpty(thatPiece)) {
                break;
            }
            cleanPath.add(position);
            if (piece.isEnemy(thatPiece)) {
                break;
            }
        }
        return cleanPath;
    }

    public List<Position> positions() {
        return positions;
    }
}
