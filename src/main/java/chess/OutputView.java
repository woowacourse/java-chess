package chess;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class OutputView {

    public static void printChessBoard(Map<Column, List<ChessPiece>> chessBoard){
        chessBoard.values()
                .forEach(OutputView::printOneRow);
    }

    private static void printOneRow(List<ChessPiece> row) {
        row.forEach(chessPiece -> System.out.print(chessPiece.getName()));
        System.out.println();
    }
}
