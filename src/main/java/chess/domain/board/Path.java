package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Path {

    private final List<Position> positions;

    public Path(List<Position> positions) {
        this.positions = positions;
    }

    public boolean isAble(Position position) {
        return positions.contains(position);
    }

    public List<Position> removeObstacleInPath(Piece piece, Board board) {
        if (piece.isPawn()) {
            Pawn pawn = (Pawn) piece;
            return calculatePawnPath(pawn, board);
        }
        return calculateNonePawnPath(piece, board);
    }

    private List<Position> calculatePawnPath(Pawn pawn, Board board) {
        return positions.stream()
            .filter(position -> {
                Piece target = board.findPieceBy(position);
                Position pawnPosition = board.findPositionBy(pawn);
                return pawn.isEnemyOrEmpty(target) && pawn
                    .isMovable(pawnPosition, position, target);
            })
            .collect(Collectors.toList());
    }

    private List<Position> calculateNonePawnPath(Piece piece, Board board) {
        List<Position> cleanPath = new ArrayList<>();
        for (Position position : positions) {
            Piece thatPiece = board.findPieceBy(position);
            if (piece.isSameSide(thatPiece)) {
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
