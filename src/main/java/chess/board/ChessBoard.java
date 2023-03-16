package chess.board;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard {
    private static final int MIN_ROW_NUMBER = 1;
    private static final int MAX_ROW_NUMBER = 8;
    private static final int ROW_INDEX = 1;
    private static final int COLUMN_INDEX = 0;
    
    private final List<RowPieces> chessBoard;
    
    public ChessBoard(List<RowPieces> chessBoard) {
        this.chessBoard = chessBoard;
    }
    
    public static ChessBoard create(){
        return new ChessBoard(initChessBoard());
    }
    
    private static List<RowPieces> initChessBoard() {
        return IntStream.rangeClosed(MIN_ROW_NUMBER, MAX_ROW_NUMBER)
                .mapToObj(RowPieces::new)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
    
    public void move(String sourceCoordinate, String destinationCoordinate) {
        List<String> splitedSourceCoordinate = splitedCoordinate(sourceCoordinate);
        List<String> splitedDestinationCoordinate = splitedCoordinate(destinationCoordinate);
        List<Integer> parsedSourceCoordinate = parseCoordinate(splitedSourceCoordinate);
        List<Integer> parsedDestinationCoordinate = parseCoordinate(splitedDestinationCoordinate);
        RowPieces rowPiecesContainsSourcePiece = findRowPiecesByRow(parsedSourceCoordinate.get(ROW_INDEX));
        RowPieces rowPiecesContainsDestinationPiece = findRowPiecesByRow(parsedDestinationCoordinate.get(ROW_INDEX));
        
        if (isMovableSourcePiece(splitedSourceCoordinate, splitedDestinationCoordinate)) {
            rowPiecesContainsSourcePiece.move(rowPiecesContainsDestinationPiece, parsedSourceCoordinate, parsedDestinationCoordinate);
        }
    }
    
    private boolean isMovableSourcePiece(List<String> splitedSourceCoordinate, List<String> splitedDestinationCoordinate) {
        List<Integer> sourceCoordinate = parseCoordinate(splitedSourceCoordinate);
        List<Integer> destinationCoordinate = parseCoordinate(splitedDestinationCoordinate);
        RowPieces rowPiecesContainsSourcePiece = findRowPiecesByRow(sourceCoordinate.get(ROW_INDEX));
        RowPieces rowPiecesContainsDestinationPiece = findRowPiecesByRow(destinationCoordinate.get(ROW_INDEX));
        
        boolean isSourcePieceKnight = rowPiecesContainsSourcePiece.isPieceByColumnKnight(parseColumn(splitedSourceCoordinate));
        boolean isMovablePiece = rowPiecesContainsSourcePiece
                .isMovable(rowPiecesContainsDestinationPiece, sourceCoordinate, destinationCoordinate);
        boolean isMovableRoute = isMovableRoute(0, sourceCoordinate, destinationCoordinate);
        
        return isMovablePiece && (isMovableRoute || isSourcePieceKnight);
    }
    
    private List<Integer> parseCoordinate(List<String> splitedSourceCoordinate) {
        return List.of((int) parseColumn(splitedSourceCoordinate), parseRow(splitedSourceCoordinate));
    }
    
    private RowPieces findRowPiecesByRow(int row) {
        return chessBoard.stream()
                .filter(rowPieces -> rowPieces.isSameRow(row))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Row는 존재하지 않습니다."));
    }
    
    private boolean isMovableRoute(int count, List<Integer> researchCoordinate, List<Integer> destinationCoordinate) {
        int researchColumn = findCoordinateByRowOrColumnIndex(researchCoordinate, COLUMN_INDEX);
        boolean isReachedAtDestination = isReachedAtDestination(researchCoordinate, destinationCoordinate);
        RowPieces rowPiecesContainsResearchPiece = findRowPiecesByRow(researchCoordinate.get(ROW_INDEX));
        boolean isPieceByColumnNotEmpty = rowPiecesContainsResearchPiece.isPieceByColumnNotEmpty(researchColumn);
        
        if (isReachedAtDestination) {
            return true;
        }
        if (isPieceByColumnNotEmpty && count > 0) {
            return false;
        }
    
        return repeatResearch(count, researchCoordinate, destinationCoordinate);
    }
    
    private boolean repeatResearch(int count, List<Integer> researchCoordinate, List<Integer> destinationCoordinate) {
        int researchColumn = findCoordinateByRowOrColumnIndex(researchCoordinate, COLUMN_INDEX);
        int destinationColumn = findCoordinateByRowOrColumnIndex(destinationCoordinate, COLUMN_INDEX);
        int researchRow = findCoordinateByRowOrColumnIndex(researchCoordinate, ROW_INDEX);
        int destinationRow = findCoordinateByRowOrColumnIndex(destinationCoordinate, ROW_INDEX);
        
        int directionNumberOfDestinationColumn = findDirectionNumber(researchColumn, destinationColumn);
        int directionNumberOfDestinationRow = findDirectionNumber(researchRow, destinationRow);
        return isMovableRoute(
                count + 1,
                List.of(researchColumn + directionNumberOfDestinationColumn, researchRow + directionNumberOfDestinationRow),
                List.of(destinationColumn, destinationRow)
        );
    }
    
    private int findCoordinateByRowOrColumnIndex(List<Integer> coordinate, int rowOrColumnIndex) {
        return coordinate.get(rowOrColumnIndex);
    }
    
    private boolean isReachedAtDestination(List<Integer> researchCoordinate, List<Integer> destinationCoordinate) {
        int researchRow = findCoordinateByRowOrColumnIndex(researchCoordinate, ROW_INDEX);
        int destinationRow = findCoordinateByRowOrColumnIndex(destinationCoordinate, ROW_INDEX);
        int researchColumn = findCoordinateByRowOrColumnIndex(researchCoordinate, COLUMN_INDEX);
        int destinationColumn = findCoordinateByRowOrColumnIndex(destinationCoordinate, COLUMN_INDEX);
        
        return researchRow == destinationRow && researchColumn == destinationColumn;
    }
    
    private int parseRow(List<String> splitedSourceCoordinate) {
        return Integer.parseInt(splitedSourceCoordinate.get(ROW_INDEX));
    }
    
    private char parseColumn(List<String> splitedSourceCoordinate) {
        return splitedSourceCoordinate.get(COLUMN_INDEX).charAt(0);
    }
    
    private int findDirectionNumber(int source, int destination) {
        return Integer.compare(destination, source);
    }
    
    private List<String> splitedCoordinate(String coordinate) {
        return Arrays.stream(coordinate.split(""))
                .collect(Collectors.toUnmodifiableList());
    }
    
    public List<RowPieces> chessBoard() {
        return chessBoard;
    }
}
