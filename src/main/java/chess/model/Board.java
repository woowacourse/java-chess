package chess.model;

import java.util.Map;
import java.util.Objects;

import chess.model.boardinitializer.BoardInitializer;
import chess.model.piece.Piece;

public class Board {

    static final String ERROR_SOURCE_PIECE_EMPTY = "[ERROR] 출발 위치에는 말이 있어야 합니다.";
    static final String ERROR_NOT_MOVABLE = "[ERROR] 이동할 수 없는 위치입니다.";

    private final Map<Position, Piece> values;

    public Board(BoardInitializer initializer) {
        this.values = initializer.apply();
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = pieceAt(source);
        Piece targetPiece = pieceAt(target);

        validateSourceNotEmpty(source);
        validateChangeable(source, target,
            MoveType.of(isEmpty(targetPiece), isTargetFriendly(sourcePiece, targetPiece)));

        values.put(target, sourcePiece);
        values.remove(source);
    }

    private void validateSourceNotEmpty(Position source) {
        if (isEmpty(pieceAt(source))) {
            throw new IllegalArgumentException(ERROR_SOURCE_PIECE_EMPTY);
        }
    }

    private boolean isTargetFriendly(Piece sourcePiece, Piece targetPiece) {
        if (Objects.isNull(targetPiece)) {
            return false;
        }
        return sourcePiece.isFriendly(targetPiece);
    }

    private void validateChangeable(Position source, Position target, MoveType moveType) {
        if (!pieceAt(source).isMovable(new Path(source, target), moveType) || isBlocked(source, target)
            || moveType.isFriendly()) {
            throw new IllegalArgumentException(ERROR_NOT_MOVABLE);
        }
    }

    private boolean isBlocked(Position source, Position target) {
        if (pieceAt(source).isKnight()) {
            return false;
        }

        return new Path(source, target).possiblePositions().stream()
            .anyMatch(position -> !isEmpty(pieceAt(position)));
    }

    public boolean isTargetKing(Position target) {
        if (isEmpty(pieceAt(target))) {
            return false;
        }
        return pieceAt(target).isKing();
    }

    private boolean isEmpty(Piece piece) {
        return Objects.isNull(piece);
    }

    Piece pieceAt(Position position) {
        return values.get(position);
    }

    public Map<Position, Piece> getValues() {
        return values;
    }
}
