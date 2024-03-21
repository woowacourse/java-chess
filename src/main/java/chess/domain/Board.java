package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Set;

public class Board {
    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceByPosition(Position position) {
        return pieces.getOrDefault(position, new Empty());
    }

    public void move(Position sourcePosition, Position targetPosition, Color color) {
        Piece sourcePiece = findPieceByPosition(sourcePosition);

        validateIsSamePosition(sourcePosition, targetPosition);
        validateSourceIsEmpty(sourcePiece);
        validateIsNotMyTurn(color, sourcePiece);

        Set<Position> movablePositions = sourcePiece.calculateMovablePositions(sourcePosition, this);
        if (movablePositions.contains(targetPosition)) {
            pieces.put(targetPosition, sourcePiece);
            pieces.remove(sourcePosition);
        }
    }

    private static void validateIsSamePosition(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("같은 위치를 선택할 수 없습니다.");
        }
    }

    private static void validateSourceIsEmpty(Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("선택한 기물이 존재하지 않습니다.");
        }
    }

    private static void validateIsNotMyTurn(Color color, Piece sourcePiece) {
        if (!sourcePiece.isSameColor(color)) {
            throw new IllegalArgumentException("선택한 위치의 기물은 내 말이 아닙니다.");
        }
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
