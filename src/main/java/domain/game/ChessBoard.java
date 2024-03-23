package domain.game;

import domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Square, Piece> pieceBySquare;

    public ChessBoard() {
        this.pieceBySquare = new HashMap<>();
    }

    public void add(Square square, Piece piece) {
        pieceBySquare.put(square, piece);
    }

    public void move(Square sourceSquare, Square targetSquare) {
        commonMoveValidate(sourceSquare, targetSquare);

        Piece findPiece = pieceBySquare.get(sourceSquare);

        findPiece.validateMovableRoute(sourceSquare.position(), targetSquare.position(), pieceBySquare);
        update(sourceSquare, targetSquare, findPiece);
    }

    private void commonMoveValidate(Square sourceSquare, Square targetSquare) {
        if (!pieceBySquare.containsKey(sourceSquare)) {
            throw new IllegalStateException("해당 위치에 Piece가 존재하지 않습니다.");
        }
        if (hasSameColorPiece(sourceSquare, targetSquare)) {
            throw new IllegalStateException("같은 진영의 기물이 있는 곳으로 옮길 수 없습니다.");
        }
        if (sourceSquare.equals(targetSquare)) {
            throw new IllegalStateException("같은 위치로의 이동입니다. 다시 입력해주세요.");
        }
    }

    private boolean hasSameColorPiece(Square sourceSquare, Square targetSquare) {
        Piece sourcePiece = pieceBySquare.get(sourceSquare);

        if (pieceBySquare.containsKey(targetSquare)) {
            Piece targetPiece = pieceBySquare.get(targetSquare);
            return sourcePiece.isEqualColor(targetPiece.getColor());
        }
        return false;
    }

    private void update(Square sourceSquare, Square targetSquare, Piece findPiece) {
        if (pieceBySquare.containsKey(targetSquare)) {
            pieceBySquare.remove(targetSquare);
        }
        pieceBySquare.put(targetSquare, findPiece);
        pieceBySquare.remove(sourceSquare);
    }

    public boolean hasPiece(final Square square) {
        return pieceBySquare.containsKey(square);
    }

    public Piece findPieceBySquare(Square targetSquare) {
        return pieceBySquare.get(targetSquare);
    }
}
