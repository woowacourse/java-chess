package domain.game;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public class ChessBoard {
    private Color turn;
    private final Map<Position, Piece> piecePosition;

    public ChessBoard(final Map<Position, Piece> piecePosition) {
        this.turn = Color.WHITE;
        this.piecePosition = piecePosition;
    }

    public void add(Position position, Piece piece) {
        piecePosition.put(position, piece);
    }

    public void move(Position sourcePosition, Position targetPosition) {
        commonMoveValidate(sourcePosition, targetPosition);

        Piece findPiece = piecePosition.get(sourcePosition);
        validateCorrectTurn(findPiece);
        findPiece.validateMovableRoute(sourcePosition, targetPosition, piecePosition);
        update(sourcePosition, targetPosition, findPiece);
        turn = turn.switchTurn();
    }

    private void commonMoveValidate(Position sourcePosition, Position targetPosition) {
        if (!piecePosition.containsKey(sourcePosition)) {
            throw new IllegalArgumentException("[ERROR]해당 위치에 Piece가 존재하지 않습니다.");
        }
        if (hasSameColorPiece(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("[ERROR]같은 진영의 기물이 있는 곳으로 옮길 수 없습니다.");
        }
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("[ERROR]같은 위치로의 이동입니다. 다시 입력해주세요.");
        }
    }

    private void validateCorrectTurn(final Piece findPiece) {
        if (!findPiece.isEqualColor(turn)) {
            throw new IllegalArgumentException("[ERROR]현재는 " + turn.name() + "의 이동 차례입니다.");
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
