package chess.chessboard;

import chess.piece.EmptyPiece;
import chess.piece.Piece;
import chess.piece.PieceType;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toUnmodifiableMap;

public class ChessBoard {
    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public Piece moveWithCapture(final Position from, final Position to) {
        validateMove(from, to);

        final Piece capturedPiece = pieces.get(to);
        move(from, to);

        return capturedPiece;
    }

    public void validateMove(final Position from, final Position to) {
        validateNotEmpty(from);
        validatePath(from, to);
    }

    private void validateNotEmpty(final Position from) {
        if (pieces.get(from) == EmptyPiece.getInstance()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다");
        }
    }

    private void validatePath(final Position from, final Position to) {
        if (!isValidPath(from, to)) {
            throw new IllegalArgumentException("이동 불가능한 경로입니다");
        }
    }

    private void move(final Position from, final Position to) {
        final Piece movingPiece = pieces.get(from);

        pieces.put(to, movingPiece);
        pieces.put(from, EmptyPiece.getInstance());
    }

    private boolean isValidPath(final Position from, final Position to) {
        final Piece movingPiece = pieces.get(from);
        final Piece targetPiece = pieces.get(to);

        return movingPiece.isValidMove(from, to, targetPiece)
                && hasNoObstacleAlongPath(from, to);
    }

    private boolean hasNoObstacleAlongPath(final Position from, final Position to) {
        return from.positionsOfPath(to)
                   .stream()
                   .map(pieces::get)
                   .noneMatch(piece -> piece != EmptyPiece.getInstance());
    }

    public boolean isKingDead() {
        return pieces.values()
                     .stream()
                     .filter(PieceType::isKing)
                     .count() < PieceType.ALL_KING_COUNT;
    }

    public Side getPieceSideAt(final Position position) {
        final Piece piece = pieces.get(position);

        return piece.getSide();
    }

    public Map<Position, Piece> getPieces(final Side side) {
        return pieces.entrySet()
                     .stream()
                     .filter(positionPieceEntry -> positionPieceEntry.getValue()
                                                                     .isSideOf(side))
                     .collect(toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Piece getPiece(final Position position) {
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
