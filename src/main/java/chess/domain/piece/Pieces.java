package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(PiecesGenrator piecesGenrator) {
        this.pieces = new ArrayList<>(piecesGenrator.generate());
    }

    public Pieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceByPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 위치에 존재하는 기물이 없습니다."));
    }

    public void synchronizeMovedPiece(final Piece pieceBeforeMove, final Piece movedPiece) {
        if (pieces.contains(pieceBeforeMove)) {
            final int pieceBeforeMoveIndex = pieces.indexOf(pieceBeforeMove);
            pieces.set(pieceBeforeMoveIndex, movedPiece);
        }
    }

    public boolean isPieceExistOnPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position));
    }

    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }

    public void remove(final Piece pieceToRemove) {
        if (!pieces.contains(pieceToRemove)) {
            throw new IllegalArgumentException("[ERROR] 지우려고 하는 기물이 존재하지 않습니다.");
        }
        pieces.remove(pieceToRemove);
    }

    public double getSumOfScoreBySide(final Side side) {
        List<Piece> piecesBySide = getPiecesBySide(side);
        return scoreBySide(piecesBySide);
    }

    private List<Piece> getPiecesBySide(final Side side) {
        return pieces.stream()
                .filter(piece -> piece.getSide() == side)
                .collect(Collectors.toUnmodifiableList());
    }

    private double scoreBySide(final List<Piece> piecesOfTeam) {
        return piecesOfTeam.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public long getPawnCountByFile(final File file, final Side side) {
        final int fileValue = file.getValue();
        return pieces.stream()
                .filter(piece -> piece.getClass() == Pawn.class &&
                        piece.getSide() == side &&
                        piece.getFile() == fileValue)
                .count();
    }

    public boolean containsKing(Side side) {
        return pieces.stream()
                .anyMatch(piece -> piece.getClass() == King.class && piece.getSide() == side);
    }
}
