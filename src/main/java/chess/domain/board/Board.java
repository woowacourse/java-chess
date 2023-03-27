package chess.domain.board;

import chess.constant.ExceptionCode;
import chess.dao.PieceDao;
import chess.domain.piece.BlankPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.maker.PiecesGenerator;
import chess.domain.piece.property.Color;
import chess.domain.position.Path;
import chess.domain.position.Position;

import java.util.Optional;
import java.util.Set;

public class Board {

    private final Set<Piece> existingPieces;
    private final PieceDao pieceDao;

    private Board(final Set<Piece> pieces, final PieceDao pieceDao) {
        this.existingPieces = pieces;
        this.pieceDao = pieceDao;
    }

    public static Board createWith(final PiecesGenerator piecesGenerator, final PieceDao pieceDao) {
        final Set<Piece> generatedPieces = piecesGenerator.generate();

        for (Piece piece : generatedPieces) {
            pieceDao.addPiece(piece);
        }

        return new Board(generatedPieces, pieceDao);
    }

    public static Board loadWith(final PiecesGenerator piecesGenerator, final PieceDao pieceDao) {
        final Set<Piece> generatedPieces = piecesGenerator.generate();

        return new Board(generatedPieces, pieceDao);
    }

    public void move(final Position currentPosition, final Position targetPosition) {
        final Piece pieceToMove = findPieceOrElseThrowIn(currentPosition);
        final Path passingPositions = pieceToMove.getPassingPositions(targetPosition);

        validatePieceExistenceIn(passingPositions);

        processMoving(pieceToMove, targetPosition);
    }

    private Piece findPieceOrElseThrowIn(final Position position) {
        return findPieceOptionalIn(position)
                .orElseThrow(() -> new IllegalArgumentException(ExceptionCode.PIECE_CAN_NOT_FOUND.name()));
    }

    private Optional<Piece> findPieceOptionalIn(final Position position) {
        return existingPieces.stream()
                .filter(piece -> position.equals(piece.getPosition()))
                .findAny();
    }

    private void validatePieceExistenceIn(final Path passingPositions) {
        final boolean isBlocked = existingPieces.stream()
                .anyMatch(piece -> passingPositions.contains(piece.getPosition()));
        if (isBlocked) {
            throw new IllegalArgumentException(ExceptionCode.PIECE_MOVING_PATH_BLOCKED.name());
        }
    }

    private void processMoving(final Piece pieceToMove, final Position targetPosition) {
        final Piece pieceInTargetPosition = findPieceOptionalIn(targetPosition)
                .orElseGet(() -> BlankPiece.of(targetPosition));
        final Piece movedPiece = pieceToMove.move(pieceInTargetPosition);

        existingPieces.remove(pieceToMove);
        existingPieces.remove(pieceInTargetPosition);
        existingPieces.add(movedPiece);
        pieceDao.deletePiece(pieceInTargetPosition);
        pieceDao.deletePiece(pieceToMove);
        pieceDao.addPiece(movedPiece);
    }

    public boolean isSameColor(final Position position, final Color otherColor) {
        return findPieceOrElseThrowIn(position).isSameColor(otherColor);
    }

    public Set<Piece> getExistingPieces() {
        return Set.copyOf(existingPieces);
    }

    public boolean isKingExist(final Color color) {
        return existingPieces.stream()
                .anyMatch(piece -> piece.isSameColor(color) && PieceType.findByPiece(piece).equals(PieceType.KING));
    }

    public void removeAllPieceInDb() {
        pieceDao.deleteAllInGame();
    }
}
