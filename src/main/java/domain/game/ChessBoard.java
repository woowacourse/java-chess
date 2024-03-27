package domain.game;

import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public class ChessBoard {
    private Turn turn;
    private final Map<Position, Piece> piecePosition;

    public ChessBoard(final Map<Position, Piece> piecePosition) {
        this.turn = new Turn();
        this.piecePosition = piecePosition;
    }

    public void move(Position sourcePosition, Position targetPosition) {
        validateMovement(sourcePosition, targetPosition);
        update(sourcePosition, targetPosition);
        turn.change();
    }

    private void validateMovement(Position sourcePosition, Position targetPosition) {
        validateSourceExists(sourcePosition);

        Piece sourcePiece = piecePosition.get(sourcePosition);
        validateCorrectTurn(sourcePiece);

        validateDifferentSourceTarget(sourcePosition, targetPosition);
        validateOpponentTarget(sourcePosition, targetPosition);

        sourcePiece.validateMovableRoute(sourcePosition, targetPosition, piecePosition);
    }

    private void validateOpponentTarget(final Position sourcePosition, final Position targetPosition) {
        if (hasSameColorPiece(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("[ERROR]같은 진영의 기물이 있는 곳으로 옮길 수 없습니다.");
        }
    }

    private boolean hasSameColorPiece(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = piecePosition.get(sourcePosition);

        if (piecePosition.containsKey(targetPosition)) {
            Piece targetPiece = piecePosition.get(targetPosition);
            return sourcePiece.isEqualColor(targetPiece.getColor());
        }
        return false;
    }

    private void validateSourceExists(final Position sourcePosition) {
        if (!piecePosition.containsKey(sourcePosition)) {
            throw new IllegalArgumentException("[ERROR]해당 위치에 Piece가 존재하지 않습니다.");
        }
    }

    private void validateDifferentSourceTarget(final Position sourcePosition, final Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("[ERROR]같은 위치로의 이동입니다. 다시 입력해주세요.");
        }
    }

    private void validateCorrectTurn(final Piece sourcePiece) {
        if (turn.isNotTurn(sourcePiece)) {
            throw new IllegalArgumentException("[ERROR]현재는 " + turn.getName() + "의 이동 차례입니다.");
        }
    }

    private void update(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = piecePosition.get(sourcePosition);
        piecePosition.put(targetPosition, sourcePiece);
        piecePosition.remove(sourcePosition);
    }

    public boolean hasPiece(final Position position) {
        return piecePosition.containsKey(position);
    }

    public Piece findPieceByPosition(Position targetPosition) {
        return piecePosition.get(targetPosition);
    }
}
