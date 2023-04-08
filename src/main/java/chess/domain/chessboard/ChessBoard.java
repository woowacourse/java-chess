package chess.domain.chessboard;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PieceType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {
    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public Piece moveWithCapture(final Position from, final Position to) {
        validateMove(from, to);

        final Piece target = pieces.get(to);
        move(from, to);

        return target;
    }

    public void validateMove(final Position from, final Position to) {
        validateNotEmpty(from);
        validateEmptyPath(from, to);
    }

    private void validateNotEmpty(final Position from) {
        final Piece pieceToMove = pieces.get(from);
        if (pieceToMove.getPieceType() == PieceType.EMPTY) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다");
        }
    }

    private void validateEmptyPath(final Position from, final Position to) {
        if (isNotEmptyPath(from, to)) {
            throw new IllegalArgumentException("이동 불가능한 경로입니다");
        }
    }

    private boolean isNotEmptyPath(final Position from, final Position to) {
        return from.positionsOfPath(to)
                   .stream()
                   .map(pieces::get)
                   .anyMatch(piece -> !piece.isEmpty());
    }

    private void move(final Position from, final Position to) {
        final Piece pieceToMove = pieces.get(from);
        final Piece target = pieces.get(to);

        pieceToMove.move(from, to, target);
        pieces.put(to, pieceToMove);
    }

    public boolean isPieceColorNotMatch(final Position position, final Color color) {
        final Piece piece = pieces.get(position);
        return piece.isColorMatch(color);
    }

    public List<Piece> getPieces(final Color color) {
        return pieces.values()
                     .stream()
                     .filter(piece -> piece.isColorMatch(color))
                     .collect(Collectors.toList());
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
