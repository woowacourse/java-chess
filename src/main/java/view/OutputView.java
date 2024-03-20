package view;

import java.util.Arrays;
import java.util.List;
import view.dto.PieceInfo;

public class OutputView {
    private static final int BOARD_SIZE = 8;
    private final char[][] chessBoard = new char[BOARD_SIZE][BOARD_SIZE];

    public void printChessBoard(List<PieceInfo> pieceInfos) {
        initializeChessBoard();
        pieceInfos.forEach(this::placePieceOnBoard);
        printBoard();
    }

    private void initializeChessBoard() {
        for (char[] row : chessBoard) {
            Arrays.fill(row, ChessSymbol.getSymbolForRole("Square"));
        }
    }

    private void placePieceOnBoard(PieceInfo pieceInfo) {
        int row = 8 - pieceInfo.rank();
        int column = pieceInfo.file() - 1;
        chessBoard[row][column] = getPieceSymbol(pieceInfo);
    }

    private char getPieceSymbol(PieceInfo pieceInfo) {
        char symbol = ChessSymbol.getSymbolForRole(pieceInfo.role());
        if (pieceInfo.color()
                .equals("WHITE")) {
            return Character.toLowerCase(symbol);
        }
        return symbol;
    }

    private void printBoard() {
        Arrays.stream(chessBoard)
                .map(String::valueOf)
                .forEach(System.out::println);
    }
}
