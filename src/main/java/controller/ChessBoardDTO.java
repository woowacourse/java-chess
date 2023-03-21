package controller;

import domain.chessboard.ChessBoard;
import domain.coordinate.PositionFactory;
import domain.piece.Color;
import domain.squarestatus.SquareStatus;
import domain.type.EmptyType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ChessBoardDTO {

    private static final int ROW_SIZE = 8;
    private final List<List<String>> rowDTOs;

    private ChessBoardDTO(final List<List<String>> rowDTOs) {
        this.rowDTOs = rowDTOs;
    }

    public static ChessBoardDTO from(final ChessBoard chessBoard) {
        List<List<String>> rowDTOs = new ArrayList<>();

        for (int i = 0; i < ROW_SIZE; i++) {
            rowDTOs.add(convertRowToChessBoardElement(chessBoard, i));
        }

        return new ChessBoardDTO(rowDTOs);
    }

    private static List<String> convertRowToChessBoardElement(final ChessBoard chessBoard, final int row) {
        return PositionFactory.findRow(row).stream()
                .map(chessBoard::findPosition)
                .map(ChessBoardDTO::convertPieceToElement)
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
