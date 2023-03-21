package chess.chessboard;

import chess.piece.EmptyPiece;
import chess.piece.Piece;

import java.util.Map;

public class ChessBoard {
    private final Map<Square, Piece> pieces;

    public ChessBoard(Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean move(Square from, Square to) {
        final Piece target = pieces.get(from);
        if (target == EmptyPiece.getInstance()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다");
        }
        if (target.isMovable(from, to, pieces.get(to)) && !hasObstacleAlongPath(from, to)) {
            pieces.put(to, target);
            pieces.put(from, EmptyPiece.getInstance());
            return true;
        }
        return false;
    }

    private boolean hasObstacleAlongPath(final Square from, final Square to) {
        return from.squaresOfPath(to)
                   .stream()
                   .anyMatch(square -> pieces.get(square) != EmptyPiece.getInstance());
    }

    public Map<Square, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
