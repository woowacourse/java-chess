package chess.chessboard;

import chess.piece.EmptyPiece;
import chess.piece.Piece;

import java.util.Map;

import static java.util.stream.Collectors.toUnmodifiableMap;

public class ChessBoard {
    private final Map<Square, Piece> pieces;

    public ChessBoard(Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece move(final Square source, final Square destination) {
        final Piece pieceToMove = pieces.get(source);
        validateNotEmpty(pieceToMove);
        validateMovablePath(source, destination);

        return movePiece(source, destination);
    }

    private Piece movePiece(final Square source, final Square destination) {
        final Piece pieceToMove = pieces.get(source);
        final Piece existingPiece = pieces.get(destination);

        pieces.put(destination, pieceToMove);
        pieces.put(source, EmptyPiece.getInstance());

        return existingPiece;
    }

    private void validateNotEmpty(final Piece target) {
        if (target == EmptyPiece.getInstance()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다");
        }
    }

    private void validateMovablePath(final Square source, final Square destination) {
        if (isNotValidMove(source, destination)) {
            throw new IllegalArgumentException("갈 수 없는 경로입니다");
        }
        if (hasObstacleAlongPath(source, destination)) {
            throw new IllegalArgumentException("갈 수 없는 경로입니다");
        }
    }

    private boolean isNotValidMove(final Square source, final Square destination) {
        final Piece pieceToMove = pieces.get(source);
        final Piece pieceOfDestination = pieces.get(destination);

        return !pieceToMove.isMovable(source, destination, pieceOfDestination);
    }

    private boolean hasObstacleAlongPath(final Square source, final Square destination) {
        return source.squaresOfPath(destination)
                     .stream()
                     .map(pieces::get)
                     .anyMatch(piece -> piece != EmptyPiece.getInstance());
    }

    public Side getPieceSideAt(final Square square) {
        final Piece piece = pieces.get(square);

        return piece.getSide();
    }

    public Map<Square, Piece> getPieces(final Side side) {
        return pieces.entrySet()
                     .stream()
                     .filter(squarePieceEntry -> {
                         final Piece piece = squarePieceEntry.getValue();
                         return piece.isSameSide(side);
                     })
                     .collect(toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Square, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
