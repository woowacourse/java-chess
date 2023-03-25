package dto;

import controller.ChessBoardElement;
import domain.chessboard.ChessBoard;
import domain.piece.Color;
import domain.position.PositionFactory;
import domain.squarestatus.SquareStatus;
import domain.type.EmptyType;

import java.util.List;
import java.util.stream.Collectors;

public final class ChessBoardDto {

    private final List<String> row1;
    private final List<String> row2;
    private final List<String> row3;
    private final List<String> row4;
    private final List<String> row5;
    private final List<String> row6;
    private final List<String> row7;
    private final List<String> row8;

    private ChessBoardDto(final List<String> row1, final List<String> row2, final List<String> row3, final List<String> row4, final List<String> row5, final List<String> row6, final List<String> row7, final List<String> row8) {
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
        this.row4 = row4;
        this.row5 = row5;
        this.row6 = row6;
        this.row7 = row7;
        this.row8 = row8;
    }

    public static ChessBoardDto from(final ChessBoard chessBoard) {

        final List<String> row1 = convertRowToChessBoardElement(chessBoard, 0);
        final List<String> row2 = convertRowToChessBoardElement(chessBoard, 1);
        final List<String> row3 = convertRowToChessBoardElement(chessBoard, 2);
        final List<String> row4 = convertRowToChessBoardElement(chessBoard, 3);
        final List<String> row5 = convertRowToChessBoardElement(chessBoard, 4);
        final List<String> row6 = convertRowToChessBoardElement(chessBoard, 5);
        final List<String> row7 = convertRowToChessBoardElement(chessBoard, 6);
        final List<String> row8 = convertRowToChessBoardElement(chessBoard, 7);

        return new ChessBoardDto(row1, row2, row3, row4, row5, row6, row7, row8);
    }

    private static List<String> convertRowToChessBoardElement(final ChessBoard chessBoard, final int row) {
        return PositionFactory.findRow(row).stream()
                .map(chessBoard::findPosition)
                .map(ChessBoardDto::convertPieceToElement)
                .collect(Collectors.toList());
    }

    private static String convertPieceToElement(final SquareStatus squareStatus) {
        final String elementName = ChessBoardElement.getElementName(squareStatus.getType());

        if (squareStatus.isDifferentType(EmptyType.EMPTY) && squareStatus.isSameColor(Color.BLACK)) {
            return elementName.toUpperCase();
        }
        return elementName;
    }

    public List<String> getRow1() {
        return row1;
    }

    public List<String> getRow2() {
        return row2;
    }

    public List<String> getRow3() {
        return row3;
    }

    public List<String> getRow4() {
        return row4;
    }

    public List<String> getRow5() {
        return row5;
    }

    public List<String> getRow6() {
        return row6;
    }

    public List<String> getRow7() {
        return row7;
    }

    public List<String> getRow8() {
        return row8;
    }
}
