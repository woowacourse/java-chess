package chess.chessboard;

import chess.piece.EmptyPiece;
import chess.piece.Piece;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toUnmodifiableMap;

public class ChessBoard {
    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public Piece moveAndCapture(final Position source, final Position destination) {
        final Piece pieceOfSource = pieces.get(source);
        final Piece pieceOfDestination = pieces.get(destination);

        validateNotEmptyPiece(pieceOfSource);
        validateIsValidPath(source, destination);

        move(source, destination);

        return pieceOfDestination;
    }

    private void move(final Position source, final Position destination) {
        final Piece pieceOfSource = pieces.get(source);
        final Piece pieceOfDestination = pieces.get(destination);

        pieces.put(destination, pieceOfSource);
        pieces.put(source, EmptyPiece.getInstance());
    }

    private void validateNotEmptyPiece(final Piece target) {
        if (target == EmptyPiece.getInstance()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다");
        }
    }

    private void validateIsValidPath(final Position source, final Position destination) {
        if (isValidMove(source, destination)) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다");
        }
        if (hasObstacleAlongPath(source, destination)) {
            throw new IllegalArgumentException("경로에 다른 기물이 존재합니다");
        }
    }

    private boolean isValidMove(final Position source, final Position destination) {
        final Piece pieceOfSource = pieces.get(source);
        final Piece pieceOfDestination = pieces.get(destination);

        return !pieceOfSource.isMovable(source, destination, pieceOfDestination);
    }

    private boolean hasObstacleAlongPath(final Position source, final Position destination) {
        return source.positionsOfPath(destination)
                     .stream()
                     .map(pieces::get)
                     .anyMatch(piece -> piece != EmptyPiece.getInstance());
    }

    public Side getPieceSideAt(final Position position) {
        final Piece piece = pieces.get(position);

        return piece.getSide();
    }

    public Map<Position, Piece> getPieces(final Side side) {
        return pieces.entrySet()
                     .stream()
                     .filter(positionPieceEntry -> positionPieceEntry.getValue()
                                                                     .hasSideOf(side))
                     .collect(toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Position, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
