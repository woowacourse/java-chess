package chess.board;

import chess.piece.Piece;
import chess.piece.PieceMatcher;
import chess.piece.Team;
import chess.piece.coordinate.Coordinate;
import chess.piece.InitialSymbols;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RowPieces {
    private static final int MIN_COLUMN_INDEX = 0;
    private static final int MAX_COLUMN_INDEX = 7;
    
    private final List<Piece> pieces;
    
    public RowPieces(int rowNum) {
        this(initRowPieces(rowNum));
        
    }
    
    private RowPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
    
    private static List<Piece> initRowPieces(int rowNum) {
        return IntStream.rangeClosed(MIN_COLUMN_INDEX, MAX_COLUMN_INDEX)
                .mapToObj(columnIndex -> parsePiece(rowNum, columnIndex))
                .collect(Collectors.toUnmodifiableList());
    }
    
    private static Piece parsePiece(int rowNum, int columnIndex) {
        InitialSymbols initialSymbols = InitialSymbols.from(rowNum);
        Character symbol = initialSymbols.findSymbolByColumnIndex(columnIndex);
        Team team = Team.from(rowNum);
        Coordinate coordinate = new Coordinate(rowNum, parseColumn(columnIndex));
        
        return PieceMatcher.of(symbol, team, coordinate);
    }
    
    private static char parseColumn(int columnIndex) {
        return (char) (columnIndex + 'a');
    }
    
    public List<Piece> pieces() {
        return pieces;
    }
}
