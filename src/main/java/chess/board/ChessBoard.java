package chess.board;

import chess.piece.coordinate.Coordinate;
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

    public static ChessBoard create() {
        return new ChessBoard(initChessBoard());
    }

    private static List<RowPieces> initChessBoard() {
        return IntStream.rangeClosed(MIN_ROW_NUMBER, MAX_ROW_NUMBER)
            .mapToObj(num -> new RowPieces(Integer.toString(num)))
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
    }

    public void move(Coordinate sourceCoordinate, Coordinate destinationCoordinate) {
        System.out.println(destinationCoordinate);
        RowPieces sourceRowPieces = findRowPiecesByCoordinate(sourceCoordinate);
        RowPieces destinationRowPieces = findRowPiecesByCoordinate(destinationCoordinate);

        boolean isMovableSourcePiece = isMovableSourcePiece(sourceRowPieces, destinationRowPieces, sourceCoordinate,
            destinationCoordinate);
        if (isMovableSourcePiece) {
            sourceRowPieces.move(destinationRowPieces, sourceCoordinate, destinationCoordinate);
        }
    }

    private RowPieces findRowPiecesByCoordinate(Coordinate coordinate) {
        return chessBoard.stream()
            .filter(rowPieces -> rowPieces.hasCoordinate(coordinate))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 Row는 존재하지 않습니다."));
    }

    private boolean isMovableSourcePiece(RowPieces sourceRowPieces, RowPieces destinationRowPieces,
        Coordinate sourceCoordinate, Coordinate destinationCoordinate) {
        boolean isMovablePiece = sourceRowPieces
            .isMovable(destinationRowPieces, sourceCoordinate, destinationCoordinate);
        boolean isEmptyRoute = isEmptyRoute(FIRST_TRY, sourceCoordinate, destinationCoordinate);
        boolean isSourcePieceKnight = sourceRowPieces.isPieceKnight(sourceCoordinate);

        return isMovablePiece && (isEmptyRoute || isSourcePieceKnight);
    }

    private boolean isEmptyRoute(boolean isNotFirstTry, Coordinate sourceCoordinate, Coordinate destinationCoordinate) {
        RowPieces sourceRowPieces = findRowPiecesByCoordinate(sourceCoordinate);
        boolean isPieceNotEmpty = sourceRowPieces.isPieceByColumnNotEmpty(sourceCoordinate);

        if (isReachedAtDestination(sourceCoordinate, destinationCoordinate)) {
            return true;
        }
        if (isPieceNotEmpty && isNotFirstTry) {
            return false;
        }
        return repeatResearch(sourceCoordinate, destinationCoordinate);
    }

    private boolean isReachedAtDestination(Coordinate researchCoordinate, Coordinate destinationCoordinate) {
        return researchCoordinate.equals(destinationCoordinate);
    }

    private boolean repeatResearch(Coordinate researchCoordinate, Coordinate destinationCoordinate) {
        return isEmptyRoute(
            true,
            moveForDestination(researchCoordinate, destinationCoordinate),
            destinationCoordinate
        );
    }

    private Coordinate moveForDestination(Coordinate researchCoordinate, Coordinate destinationCoordinate) {
        int columnAdd = researchCoordinate.compareByColumn(destinationCoordinate);
        int rowAdd = researchCoordinate.compareByRowNum(destinationCoordinate);
        return researchCoordinate.move(rowAdd,columnAdd);
    }

    public List<RowPieces> chessBoard() {
        return chessBoard;
    }
}
