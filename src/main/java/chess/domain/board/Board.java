package chess.domain.board;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.maker.PiecesFactory;
import chess.domain.piece.BlankPiece;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Optional;

public class Board {

    private final List<Piece> pieces;

    private Board(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board createBoardWith(final PiecesFactory piecesFactory) {
        return new Board(piecesFactory.generate());
    }

    public void move(final Position currentPosition, final Position targetPosition) {
        final Piece pieceToMove = findPieceToMoveIn(currentPosition);
        final List<Position> passingPositions = pieceToMove.getPassingPositions(targetPosition);

        validateExistenceIn(passingPositions);

        processMoving(pieceToMove, targetPosition);
    }

    private Piece findPieceToMoveIn(final Position position) {
        return findPieceOptionalIn(position)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다."));
    }

    private Optional<Piece> findPieceOptionalIn(final Position position) {
        return pieces.stream()
                .filter(piece -> position.equals(piece.getPosition()))
                .findAny();
    }

    private Piece findPieceIn(final Position position) {
        return findPieceOptionalIn(position)
                .orElseGet(() -> new BlankPiece(position.getFile(), position.getRank()));
    }

    private void validateExistenceIn(final List<Position> passingPositions) {
        final boolean isBlocked = pieces.stream()
                .anyMatch(piece -> passingPositions.contains(piece.getPosition()));
        if (isBlocked) {
            throw new IllegalArgumentException("이동 경로에 다른 말이 있습니다.");
        }
    }

    private void processMoving(final Piece pieceToMove, final Position targetPosition) {
        final Piece pieceInTargetPosition = findPieceIn(targetPosition);
        final Piece movedPiece = pieceToMove.move(pieceInTargetPosition);

        pieces.remove(pieceToMove);
        pieces.remove(pieceInTargetPosition);
        pieces.add(movedPiece);
    }

    public boolean isSameColor(final Position position, final Color otherColor) {
        return findPieceToMoveIn(position).isSameColor(otherColor);
    }

    public boolean hasPieces() {
        return !pieces.isEmpty();
    }

    public boolean hasTwoKings() {
        return 2 == pieces.stream()
                .filter(Piece::isKing)
                .count();
    }

    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }
}
