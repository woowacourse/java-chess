package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RowPieces implements Comparable<RowPieces> {
    private static final int MIN_COLUMN_INDEX = 0;
    private static final int MAX_COLUMN_INDEX = 7;
    private static final int FIRST_PIECE_INDEX = 0;
    
    private final List<Piece> pieces;
    
    public RowPieces(int rowNum) {
        this(initRowPieces(rowNum));
    }
    
    private RowPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
    
    private static List<Piece> initRowPieces(int rowNum) {
        return IntStream.rangeClosed(MIN_COLUMN_INDEX, MAX_COLUMN_INDEX)
                .mapToObj(column -> Piece.from(new Coordinate(parseColumn(column), rowNum)))
                .collect(Collectors.toList());
    }
    
    private static char parseColumn(int column) {
        return (char) (column + 'a');
    }
    
    @Override
    public int compareTo(RowPieces otherRowPieces) {
        return firstPiece(this).compareToPieceByRowNum(firstPiece(otherRowPieces));
    }
    
    private Piece firstPiece(RowPieces rowPieces) {
        return rowPieces.pieces.get(FIRST_PIECE_INDEX);
    }
    
    public boolean containsSameCoordinate(Coordinate coordinate) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSameCoordinate(coordinate));
    }
    
    Piece findPieceByCoordinate(Coordinate coordinate) {
        return pieces.stream()
                .filter(piece -> piece.isSameCoordinate(coordinate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌표입니다."));
    }
    
    public boolean isPieceByCoordinateNotEmpty(Coordinate researchCoordinate) {
        return !findPieceByCoordinate(researchCoordinate).isSameTeam(Team.EMPTY);
    }
    
    public void move(
            RowPieces rowPiecesContainsDestinationPiece,
            Coordinate sourceCoordinate,
            Coordinate destinationCoordinate
    ) {
        Piece sourcePiece = findPieceByCoordinate(sourceCoordinate);
        Piece movedPiece = sourcePiece.movedSourcePiece(destinationCoordinate);
    
        rowPiecesContainsDestinationPiece.switchPiece(movedPiece, destinationCoordinate);
        switchPiece(createEmpty(sourceCoordinate), sourceCoordinate);
    }
    
    private void switchPiece(Piece movedPiece, Coordinate coordinate) {
        pieces.set(coordinate.columnIndex(), movedPiece);
    }
    
    private Piece createEmpty(Coordinate coordinate) {
        return new Empty(Team.EMPTY, coordinate);
    }
    
    public int row() {
        return firstPiece(this).row();
    }
    
    public List<Piece> pieces() {
        return pieces;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowPieces rowPieces = (RowPieces) o;
        return Objects.equals(pieces, rowPieces.pieces);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}
