package domain.game;

import domain.chessboard.Square;
import domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class PieceMover {

    private final Map<Square, Piece> pieceBySquare;

    public PieceMover() {
        this.pieceBySquare = new HashMap<>();
    }

    public void add(Square square, Piece piece) {
        pieceBySquare.put(square, piece);
    }

    public void move(Square sourceSquare, Square targetSquare) {
        if (!pieceBySquare.containsKey(sourceSquare)) {
            throw new IllegalStateException("해당 위치에 Piece가 존재하지 않습니다.");
        }

        Piece findPiece = pieceBySquare.get(sourceSquare);
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
