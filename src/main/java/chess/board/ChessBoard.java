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
    private static final boolean FIRST_TRY = false;
    
    private final List<RowPieces> chessBoard;
    
    private ChessBoard(List<RowPieces> chessBoard) {
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
        List<Integer> parsedSourceCoordinate = parseCoordinate(sourceCoordinate);
        List<Integer> parsedDestinationCoordinate = parseCoordinate(destinationCoordinate);
        RowPieces rowPiecesContainsSourcePiece = findRowPiecesByRow(parsedSourceCoordinate.get(ROW_INDEX));
        RowPieces rowPiecesContainsDestinationPiece = findRowPiecesByRow(parsedDestinationCoordinate.get(ROW_INDEX));
    
        boolean isMovableSourcePiece = isMovableSourcePiece(parsedSourceCoordinate, parsedDestinationCoordinate);
        if (isMovableSourcePiece) {
            rowPiecesContainsSourcePiece.move(rowPiecesContainsDestinationPiece, parsedSourceCoordinate, parsedDestinationCoordinate);
        }
    }
    
    private List<Integer> parseCoordinate(String coordinate) {
        List<String> parsedCoordinate = splitCoordinate(coordinate);
        return List.of((int)parsedCoordinate.get(COLUMN_INDEX).charAt(0), Integer.parseInt(parsedCoordinate.get(ROW_INDEX)));
    }
    
    private List<String> splitCoordinate(String coordinate) {
        return Arrays.stream(coordinate.split(""))
                .collect(Collectors.toUnmodifiableList());
    }
    
    private RowPieces findRowPiecesByRow(int row) {
        return chessBoard.stream()
                .filter(rowPieces -> rowPieces.isSameRow(row))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Row는 존재하지 않습니다."));
    }
    
    private boolean isMovableSourcePiece(List<Integer> sourceCoordinate, List<Integer> destinationCoordinate) {
        RowPieces rowPiecesContainsSourcePiece = findRowPiecesByRow(sourceCoordinate.get(ROW_INDEX));
        RowPieces rowPiecesContainsDestinationPiece = findRowPiecesByRow(destinationCoordinate.get(ROW_INDEX));
    
        boolean isMovablePiece = rowPiecesContainsSourcePiece
                .isMovable(rowPiecesContainsDestinationPiece, sourceCoordinate, destinationCoordinate);
        boolean isEmptyRoute = isEmptyRoute(FIRST_TRY, sourceCoordinate, destinationCoordinate);
        boolean isSourcePieceKnight = rowPiecesContainsSourcePiece.isPieceByColumnKnight(parseColumn(sourceCoordinate));
        
        return isMovablePiece && (isEmptyRoute || isSourcePieceKnight);
    }
    
    private boolean isEmptyRoute(boolean isNotFirstTry, List<Integer> researchCoordinate, List<Integer> destinationCoordinate) {
        int researchColumn = researchCoordinate.get(COLUMN_INDEX);
        RowPieces rowPiecesContainsResearchPiece = findRowPiecesByRow(researchCoordinate.get(ROW_INDEX));
        boolean isPieceByColumnNotEmpty = rowPiecesContainsResearchPiece.isPieceByColumnNotEmpty(researchColumn);
        
        if (isReachedAtDestination(researchCoordinate, destinationCoordinate)) {
            return true;
        }
        if (isPieceByColumnNotEmpty && isNotFirstTry) {
            return false;
        }
        return repeatResearch(researchCoordinate, destinationCoordinate);
    }
    
    private boolean isReachedAtDestination(List<Integer> researchCoordinate, List<Integer> destinationCoordinate) {
        return researchCoordinate.equals(destinationCoordinate);
    }
    
    private boolean repeatResearch(List<Integer> researchCoordinate, List<Integer> destinationCoordinate) {
        return isEmptyRoute(
                true,
                moveForDestination(researchCoordinate, destinationCoordinate),
                destinationCoordinate
        );
    }
    
    private List<Integer> moveForDestination(List<Integer> researchCoordinate, List<Integer> destinationCoordinate) {
        return IntStream.rangeClosed(COLUMN_INDEX, ROW_INDEX)
                .mapToObj(coordinateIndex -> moveCoordinate(researchCoordinate, destinationCoordinate, coordinateIndex))
                .collect(Collectors.toUnmodifiableList());
    }
    
    private int moveCoordinate(List<Integer> researchCoordinate, List<Integer> destinationCoordinate, int coordinateIndex) {
        return researchCoordinate.get(coordinateIndex) +
                findDirectionNumber(researchCoordinate, destinationCoordinate, coordinateIndex);
    }
    
    private int findDirectionNumber(List<Integer> researchCoordinate, List<Integer> destinationCoordinate, int coordinateIndex) {
        return Integer.compare(destinationCoordinate.get(coordinateIndex), researchCoordinate.get(coordinateIndex));
    }
    
    private char parseColumn(List<Integer> coordinate) {
        return (char)(int) coordinate.get(COLUMN_INDEX);
    }
    
    public List<RowPieces> chessBoard() {
        return chessBoard;
    }
}
