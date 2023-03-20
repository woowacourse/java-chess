package controller;

import domain.chessboard.ChessBoard;
import domain.coordinate.PositionFactory;
import domain.squarestatus.SquareStatus;
import view.ChessBoardElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardDTO {

    private final List<List<String>> rowDTOs;

    private ChessBoardDTO(final List<List<String>> rowDTOs) {
        this.rowDTOs = rowDTOs;
    }

    public static ChessBoardDTO from(final ChessBoard chessBoard) {
        List<List<String>> rowDTOs = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            rowDTOs.add(convertRowToChessBoardElement(chessBoard, i));
        }

        return new ChessBoardDTO(rowDTOs);
    }

    private static List<String> convertRowToChessBoardElement(final ChessBoard chessBoard, final int row) {
        return PositionFactory.findRow(row).stream()
                .map(chessBoard::findPosition)
                .map(SquareStatus::getType)
                .map(ChessBoardElement::getElementName)
                .collect(Collectors.toList());
    }

    public List<List<String>> getRowDTOs() {
        return rowDTOs;
    }
}
