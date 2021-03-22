package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Path {

    private static final String INVALID_TARGET_POSITION_ERROR_MESSAGE = "해당 말이 이동할 수 없는 위치입니다.";

    private final List<Position> positions;

    public Path(List<Position> positions) {
        this.positions = positions;
    }

    public boolean isAble(Position position) {
        if (positions.contains(position)) {
            return true;
        }
        throw new IllegalArgumentException(INVALID_TARGET_POSITION_ERROR_MESSAGE);
    }

    public List<Position> removeObstacleInPath(Piece piece, Board board) {
        List<Position> cleanPath = new ArrayList<>();
        if (piece.isPawn()) {
            Pawn pawn = (Pawn) piece;
            calculatePawnPath(pawn, board, cleanPath);
            return cleanPath;
        }
        calculateNonePawnPath(piece, board, cleanPath);
        return cleanPath;
    }

    private void calculatePawnPath(Pawn pawn, Board board, List<Position> cleanPath) {
        cleanPath.addAll(positions.stream()
            .filter(position -> {
                Piece target = board.findPieceBy(position);
                Position pawnPosition = board.findPositionBy(pawn);
                return pawn.isEnemyOrEmpty(target) && pawn
                    .isMovable(pawnPosition, position, target);
            })
            .collect(Collectors.toList()));
    }

    private void calculateNonePawnPath(Piece piece, Board board, List<Position> cleanPath) {
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
    }

    public List<Position> positions() {
        return positions;
    }
}
