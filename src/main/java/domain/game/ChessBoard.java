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

    public void move(final Position source, final Position target) {
        validateMovement(source, target);
        update(source, target);
        turn.change();
    }

    private void validateMovement(final Position source, final Position target) {
        validateSourceExists(source);

        Piece sourcePiece = piecePosition.get(source);
        validateCorrectTurn(sourcePiece);

        validateDifferentSourceTarget(source, target);
        validateOpponentTarget(source, target);

        sourcePiece.validateMovableRoute(source, target, piecePosition);
    }

    private void validateOpponentTarget(final Position source, final Position target) {
        if (hasSameColorPiece(source, target)) {
            throw new IllegalArgumentException("[ERROR]같은 진영의 기물이 있는 곳으로 옮길 수 없습니다.");
        }
    }

    private boolean hasSameColorPiece(final Position source, final Position target) {
        Piece sourcePiece = piecePosition.get(source);

        if (piecePosition.containsKey(target)) {
            Piece targetPiece = piecePosition.get(target);
            return sourcePiece.isEqualColor(targetPiece.getColor());
        }
        return false;
    }

    private void validateSourceExists(final Position source) {
        if (!piecePosition.containsKey(source)) {
            throw new IllegalArgumentException("[ERROR]해당 위치에 Piece가 존재하지 않습니다.");
        }
    }

    private void validateDifferentSourceTarget(final Position source, final Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("[ERROR]같은 위치로의 이동입니다. 다시 입력해주세요.");
        }
    }

    private void validateCorrectTurn(final Piece sourcePiece) {
        if (turn.isNotTurn(sourcePiece)) {
            throw new IllegalArgumentException("[ERROR]현재는 " + turn.getName() + "의 이동 차례입니다.");
        }
    }

    private void update(final Position source, final Position target) {
        Piece sourcePiece = piecePosition.get(source);
        piecePosition.put(target, sourcePiece);
        piecePosition.remove(source);
    }

    public boolean hasPiece(final Position position) {
        return piecePosition.containsKey(position);
    }

    public Piece findPieceByPosition(final Position position) {
        return piecePosition.get(position);
    }
}
