package chess.board;

import chess.piece.*;
import chess.piece.coordinate.Coordinate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RowPieces implements Comparable<RowPieces> {
    private static final int ROW_INDEX = 1;
    private static final int COLUMN_INDEX = 0;
    private static final int MIN_COLUMN_INDEX = 0;
    private static final int MAX_COLUMN_INDEX = 7;
    private static final char MIN_COLUMN_CHAR = 'a';
    
    private final List<Piece> pieces;
    
    public RowPieces(int rowNum) {
        this(initRowPieces(rowNum));
    }
    
    private RowPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
    
    @Override
    public int compareTo(RowPieces otherRowPieces) {
        return firstPiece(this).compareTo(firstPiece(otherRowPieces));
    }
    
    private Piece firstPiece(RowPieces rowPieces) {
        return rowPieces.pieces.get(0);
    }
    
    private static List<Piece> initRowPieces(int rowNum) {
        return IntStream.rangeClosed(MIN_COLUMN_INDEX, MAX_COLUMN_INDEX)
                .mapToObj(columnIndex -> parsePiece(rowNum, columnIndex))
                .collect(Collectors.toList());
    }
    
    private static Piece parsePiece(int rowNum, int columnIndex) {
        InitialSymbols initialSymbols = InitialSymbols.from(rowNum);
        Character symbol = initialSymbols.findSymbolByColumnIndex(columnIndex);
        Team team = Team.from(rowNum);
        Coordinate coordinate = new Coordinate(rowNum, parseColumn(columnIndex));
        
        return PieceMatcher.of(symbol, team, coordinate);
    }
    
    private static char parseColumn(int columnIndex) {
        return (char) (columnIndex + MIN_COLUMN_CHAR);
    }
    
    public boolean isMovable(RowPieces targetRowPieces, List<Integer> sourceCoordinate, List<Integer> destinationCoordinate) {
        char sourceColumnNumber = parseColumn(sourceCoordinate);
        char destinationColumnNumber = parseColumn(destinationCoordinate);
        Piece sourcePiece = findPieceByColumn(this, sourceColumnNumber);
        Piece destinationPiece = findPieceByColumn(targetRowPieces, destinationColumnNumber);
        
        return sourcePiece.isMovable(destinationPiece);
    }
    
    private char parseColumn(List<Integer> splitedSourceCoordinate) {
        return (char)(int) splitedSourceCoordinate.get(COLUMN_INDEX);
    }
    
    private Piece findPieceByColumn(RowPieces rowPieces, char column) {
        return rowPieces.pieces.stream()
                .filter(piece -> piece.isSameColumn(column))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컬럼입니다"));
    }
    
    public boolean isSameRow(int otherRow) {
        return pieces.get(0).isSameRow(otherRow);
    }
    
    public boolean isPieceByColumnNotEmpty(int researchColumn) {
        return !findPieceByColumn(this, (char) researchColumn).isSameTeam(Team.EMPTY);
    }
    
    public void move(
            RowPieces rowPiecesContainsDestinationPiece,
            List<Integer> sourceCoordinate,
            List<Integer> destinationCoordinate
    ) {
        Piece sourcePiece = findPieceByColumn(this, parseColumn(sourceCoordinate));
        Piece movedPiece = sourcePiece.movedSourcePiece(destinationCoordinate);
        
        rowPiecesContainsDestinationPiece.switchPiece(movedPiece, parseColumn(destinationCoordinate));
        switchPiece(createEmpty(sourceCoordinate), parseColumn(sourceCoordinate));
    }
    
    private Empty createEmpty(List<Integer> coordinate) {
        return new Empty(Team.EMPTY, new Coordinate(findRow(coordinate), parseColumn(coordinate)));
    }
    
    private void switchPiece(Piece movedPiece, char column) {
        pieces.set(column - 'a', movedPiece);
    }
    
    private int findRow(List<Integer> splitedSourceCoordinate) {
        return splitedSourceCoordinate.get(ROW_INDEX);
    }
    
    public boolean isPieceByColumnKnight(char column) {
        return findPieceByColumn(this, column).isKnight();
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
