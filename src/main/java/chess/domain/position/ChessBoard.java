package chess.domain.position;

import chess.domain.piece.Piece;
import java.util.Map;

public final class ChessBoard {

    private final Map<Position, Piece> piecesPosition;

    public ChessBoard(Map<Position, Piece> piecesPosition) {
        this.piecesPosition = piecesPosition;
    }

    public Piece peekPiece(Position position) {
        return piecesPosition.get(position);
    }

    public boolean isPieceExist(Position position) {
        return piecesPosition.containsKey(position);
    }

    public void movePiece(Position fromPosition, Position toPosition) {
        piecesPosition.put(toPosition, piecesPosition.get(fromPosition));
        piecesPosition.remove(fromPosition);
    }

    public Map<Position, Piece> getPiecesPosition() {
        return piecesPosition;
    }
}
