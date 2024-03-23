package domain.game;

import domain.piece.Piece;
import domain.piece.PieceGenerator;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> piecePosition;

    public ChessBoard() {
        this.piecePosition = new HashMap<>();
        PieceGenerator.generate(piecePosition);
    }

    public void add(Position position, Piece piece) {
        piecePosition.put(position, piece);
    }

    public void move(Position sourcePosition, Position targetPosition) {
        commonMoveValidate(sourcePosition, targetPosition);

        Piece findPiece = piecePosition.get(sourcePosition);

        findPiece.validateMovableRoute(sourcePosition, targetPosition, piecePosition);
        update(sourcePosition, targetPosition, findPiece);
    }

    private void commonMoveValidate(Position sourcePosition, Position targetPosition) {
        if (!piecePosition.containsKey(sourcePosition)) {
            throw new IllegalArgumentException("해당 위치에 Piece가 존재하지 않습니다.");
        }
        if (hasSameColorPiece(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("같은 진영의 기물이 있는 곳으로 옮길 수 없습니다.");
        }
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("같은 위치로의 이동입니다. 다시 입력해주세요.");
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

    private void update(Position sourcePosition, Position targetPosition, Piece findPiece) {
        if (piecePosition.containsKey(targetPosition)) {
            piecePosition.remove(targetPosition);
        }
        piecePosition.put(targetPosition, findPiece);
        piecePosition.remove(sourcePosition);
    }

    public boolean hasPiece(final Position position) {
        return piecePosition.containsKey(position);
    }

    public Piece findPieceBySquare(Position targetPosition) {
        return piecePosition.get(targetPosition);
    }
}
