package dto;

import controller.ChessBoardElement;
import domain.chessboard.ChessBoard;
import domain.position.PositionFactory;
import domain.piece.Color;
import domain.squarestatus.SquareStatus;
import domain.type.EmptyType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ChessBoardDto {

    private static final int ROW_SIZE = 8;

    private final List<List<String>> rowDTOs;

    private ChessBoardDto(final List<List<String>> rowDTOs) {
        this.rowDTOs = rowDTOs;
    }

    public static ChessBoardDto from(final ChessBoard chessBoard) {
        List<List<String>> rowDTOs = new ArrayList<>();

        for (int i = 0; i < ROW_SIZE; i++) {
            rowDTOs.add(convertRowToChessBoardElement(chessBoard, i));
        }

        return new ChessBoardDto(rowDTOs);
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

    public List<List<String>> getRowDTOs() {
        return rowDTOs;
    }

}
