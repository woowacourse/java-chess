package chess.domain.board;

import chess.domain.piece.coordinate.Coordinate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard {
    private static final int MIN_ROW_NUMBER = 1;
    private static final int MAX_ROW_NUMBER = 8;
    private static final boolean FIRST_TRY = false;
    
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
    
    public void move(Coordinate sourceCoordinate, Coordinate destinationCoordinate) {
        RowPieces rowPiecesContainsSourcePiece = findRowPiecesByCoordinate(sourceCoordinate);
        RowPieces rowPiecesContainsDestinationPiece = findRowPiecesByCoordinate(destinationCoordinate);
        
        boolean isMovableSourcePiece = isMovableSourcePiece(sourceCoordinate, destinationCoordinate);
        if (isMovableSourcePiece) {
            rowPiecesContainsSourcePiece.move(rowPiecesContainsDestinationPiece, sourceCoordinate, destinationCoordinate);
        }
    }
    
    private RowPieces findRowPiecesByCoordinate(Coordinate coordinate) {
        return chessBoard.stream()
                .filter(rowPieces -> rowPieces.isSameCoordinate(coordinate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 좌표는 존재하지 않습니다."));
    }
    
    private boolean isMovableSourcePiece(Coordinate sourceCoordinate, Coordinate destinationCoordinate) {
        RowPieces rowPiecesContainsSourcePiece = findRowPiecesByCoordinate(sourceCoordinate);
        RowPieces rowPiecesContainsDestinationPiece = findRowPiecesByCoordinate(destinationCoordinate);
    
        boolean isMovablePiece = rowPiecesContainsSourcePiece
                .isMovable(rowPiecesContainsDestinationPiece, sourceCoordinate, destinationCoordinate);
        boolean isEmptyRoute = isEmptyRoute(FIRST_TRY, sourceCoordinate, destinationCoordinate);
        boolean isSourcePieceKnight = rowPiecesContainsSourcePiece.isPieceByCoordinateKnight(sourceCoordinate);
    
        return isMovablePiece && (isEmptyRoute || isSourcePieceKnight);
    }
    
    private boolean isEmptyRoute(boolean isNotFirstTry, Coordinate researchCoordinate, Coordinate destinationCoordinate) {
        RowPieces rowPiecesContainsResearchPiece = findRowPiecesByCoordinate(researchCoordinate);
        boolean isPieceByColumnNotEmpty = rowPiecesContainsResearchPiece.isPieceByCoordinateNotEmpty(researchCoordinate);
    
        if (isReachedAtDestination(researchCoordinate, destinationCoordinate)) {
            return true;
        }
        if (isPieceByColumnNotEmpty && isNotFirstTry) {
            return false;
        }
        return repeatResearchForDestination(researchCoordinate, destinationCoordinate);
    }
    
    private boolean repeatResearchForDestination(Coordinate researchCoordinate, Coordinate destinationCoordinate) {
        return isEmptyRoute(
                true,
                moveForDestination(researchCoordinate, destinationCoordinate),
                destinationCoordinate
        );
    }
    
    private boolean isReachedAtDestination(Coordinate researchCoordinate, Coordinate destinationCoordinate) {
        return researchCoordinate.equals(destinationCoordinate);
    }
    
    private Coordinate moveForDestination(Coordinate researchCoordinate, Coordinate destinationCoordinate) {
        return researchCoordinate.coordinateOneStepFor(destinationCoordinate);
    }
    
    public List<RowPieces> chessBoard() {
        return chessBoard;
    }
}
