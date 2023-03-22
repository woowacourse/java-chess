package chess.domain.board;

import chess.domain.piece.Team;
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
    
    public void move(Coordinate sourceCoordinate, Coordinate destinationCoordinate, Team currentOrderTeam) {
        RowPieces rowPiecesContainsSourcePiece = findRowPiecesByCoordinate(sourceCoordinate);
        RowPieces rowPiecesContainsDestinationPiece = findRowPiecesByCoordinate(destinationCoordinate);
        
        validateMovableSourcePiece(sourceCoordinate, destinationCoordinate, currentOrderTeam);
        rowPiecesContainsSourcePiece.move(rowPiecesContainsDestinationPiece, sourceCoordinate, destinationCoordinate);
    }
    
    private RowPieces findRowPiecesByCoordinate(Coordinate coordinate) {
        return chessBoard.stream()
                .filter(rowPieces -> rowPieces.isSameCoordinate(coordinate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 좌표는 존재하지 않습니다."));
    }
    
    private void validateMovableSourcePiece(Coordinate sourceCoordinate, Coordinate destinationCoordinate, Team currentOrderTeam) {
        RowPieces rowPiecesContainsSourcePiece = findRowPiecesByCoordinate(sourceCoordinate);
        RowPieces rowPiecesContainsDestinationPiece = findRowPiecesByCoordinate(destinationCoordinate);
        
        validateCurrentOrderTeam(sourceCoordinate, currentOrderTeam, rowPiecesContainsSourcePiece);
        validateMovable(sourceCoordinate, destinationCoordinate, rowPiecesContainsSourcePiece, rowPiecesContainsDestinationPiece);
        validateRoute(sourceCoordinate, destinationCoordinate, rowPiecesContainsSourcePiece);
    }
    
    private void validateCurrentOrderTeam(Coordinate sourceCoordinate, Team currentOrderTeam, RowPieces rowPiecesContainsSourcePiece) {
        boolean isCorrectOrderTeam = rowPiecesContainsSourcePiece.isCorrectOrderTeam(sourceCoordinate, currentOrderTeam);
        if (!isCorrectOrderTeam) {
            throw new IllegalArgumentException("해당 기물이 속한 팀의 순서가 아닙니다. 다시 입력해주세요.");
        }
    }
    
    private void validateMovable(Coordinate sourceCoordinate, Coordinate destinationCoordinate, RowPieces rowPiecesContainsSourcePiece, RowPieces rowPiecesContainsDestinationPiece) {
        boolean isMovablePiece = rowPiecesContainsSourcePiece
                .isMovable(rowPiecesContainsDestinationPiece, sourceCoordinate, destinationCoordinate);
        if (!isMovablePiece) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 목적지입니다. 다시 입력해주세요.");
        }
    }
    
    private void validateRoute(Coordinate sourceCoordinate, Coordinate destinationCoordinate, RowPieces rowPiecesContainsSourcePiece) {
        boolean isEmptyRoute = isEmptyRoute(FIRST_TRY, sourceCoordinate, destinationCoordinate);
        boolean isSourcePieceKnight = rowPiecesContainsSourcePiece.isPieceByCoordinateKnight(sourceCoordinate);
        if (!isEmptyRoute && !isSourcePieceKnight) {
            throw new IllegalArgumentException("목적지까지의 경로에 기물이 있어서 목적지로 갈 수 없습니다. 다시 입력해주세요.");
        }
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
    
    private boolean isReachedAtDestination(Coordinate researchCoordinate, Coordinate destinationCoordinate) {
        return researchCoordinate.equals(destinationCoordinate);
    }
    
    private boolean repeatResearchForDestination(Coordinate researchCoordinate, Coordinate destinationCoordinate) {
        return isEmptyRoute(
                true,
                moveForDestination(researchCoordinate, destinationCoordinate),
                destinationCoordinate
        );
    }
    
    private Coordinate moveForDestination(Coordinate researchCoordinate, Coordinate destinationCoordinate) {
        return researchCoordinate.coordinateOneStepFor(destinationCoordinate);
    }
    
    public List<RowPieces> chessBoard() {
        return chessBoard;
    }
}
