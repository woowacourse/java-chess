package chess.chessboard;

import chess.piece.EmptyPiece;
import chess.piece.Piece;

import java.util.Map;

public class ChessBoard {
    private final Map<Square, Piece> pieces;

    public ChessBoard(Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean move(Square source, Square destination) {
        final Piece toMove = pieces.get(source);
        validateNotEmpty(toMove);
        if (toMove.isMovable(source, destination, pieces.get(destination)) && !hasObstacleAlongPath(source, destination)) {
            pieces.put(destination, toMove);
            pieces.put(source, EmptyPiece.getInstance());
            return true;
        }
        return false;
    }

    private void validateNotEmpty(final Piece target) {
        if (target == EmptyPiece.getInstance()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다");
        }
    }

    private boolean hasObstacleAlongPath(final Square source, final Square destination) {
        return source.squaresOfPath(destination)
                     .stream()
                     .map(pieces::get)
                     .anyMatch(piece -> piece != EmptyPiece.getInstance());
    }

    public Map<Square, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
