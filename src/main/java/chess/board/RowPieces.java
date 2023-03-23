package chess.board;

import chess.piece.*;
import chess.piece.coordinate.Coordinate;

import java.util.List;
import java.util.Objects;

public class RowPieces implements Comparable<RowPieces> {
    
    private static final int FIRST_PIECE_INDEX = 0;

    private final List<Piece> pieces;

    public RowPieces(String rowNum) {
        this(InitialPieces.from(rowNum));
    }

    private RowPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public int compareTo(RowPieces o) {
        Piece firstPiece = pieces.get(FIRST_PIECE_INDEX);
        Piece otherPiece = o.pieces.get(FIRST_PIECE_INDEX);
        return firstPiece.compareTo(otherPiece);
    }

    public boolean isMovable(RowPieces targetRowPieces, Coordinate sourceCoordinate, Coordinate destinationCoordinate) {
        Piece sourcePiece = findPieceByCoordinate(this, sourceCoordinate);
        Piece destinationPiece = findPieceByCoordinate(targetRowPieces, destinationCoordinate);

        return sourcePiece.isMovable(destinationPiece);
    }

    private Piece findPieceByCoordinate(RowPieces rowPieces, Coordinate coordinate) {
        return rowPieces.pieces.stream()
            .filter(piece -> piece.hasCoordinate(coordinate))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컬럼입니다"));
    }

    public boolean isPieceByColumnNotEmpty(Coordinate coordinate) {
        return !findPieceByCoordinate(this, coordinate).isSameTeam(Team.EMPTY);
    }

    public void move(
        RowPieces destinationRowPieces,
        Coordinate sourceCoordinate,
        Coordinate destinationCoordinate
    ) {
        Piece sourcePiece = findPieceByCoordinate(this, sourceCoordinate);
        Piece newPiece = sourcePiece.newSourcePiece(destinationCoordinate);

        destinationRowPieces.switchPiece(newPiece, destinationCoordinate);
        switchPiece(createEmpty(sourceCoordinate), sourceCoordinate);
    }

    private void switchPiece(Piece newPiece, Coordinate coordinate) {
        pieces.set(coordinate.columnIndex(), newPiece);
    }

    private Empty createEmpty(Coordinate coordinate) {
        return (Empty) PieceMatcher.of(SymbolMatcher.EMPTY, Team.EMPTY, coordinate);
    }

    public boolean isPieceKnight(Coordinate coordinate) {
        return findPieceByCoordinate(this, coordinate).isKnight();
    }

    public boolean hasCoordinate(Coordinate coordinate) {
        return pieces.stream().anyMatch(
            piece -> piece.hasCoordinate(coordinate)
        );
    }

    public List<Piece> pieces() {
        return pieces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RowPieces rowPieces = (RowPieces) o;
        return Objects.equals(pieces, rowPieces.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}
