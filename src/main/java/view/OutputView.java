package view;

import java.util.Arrays;
import java.util.List;
import view.dto.PieceInfo;

public class OutputView {

    private static final int BOARD_SIZE = 8;

    private static final char[][] chessBoard = new char[BOARD_SIZE][BOARD_SIZE];

    private OutputView() {
    }

    public static void printInitialGamePrompt() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(List<PieceInfo> pieceInfos) {
        initializeChessBoard();
        pieceInfos.forEach(OutputView::placePieceOnBoard);
        printBoard();
    }

    private static void initializeChessBoard() {
        for (char[] row : chessBoard) {
            Arrays.fill(row, ChessSymbol.getSymbolForRole("Square"));
        }
    }

    private static void placePieceOnBoard(PieceInfo pieceInfo) {
        int row = 8 - pieceInfo.rank();
        int column = pieceInfo.file() - 1;
        chessBoard[row][column] = getPieceSymbol(pieceInfo);
    }

    private static char getPieceSymbol(PieceInfo pieceInfo) {
        char symbol = ChessSymbol.getSymbolForRole(pieceInfo.role());
        if (pieceInfo.color()
                .equals("WHITE")) {
            return Character.toLowerCase(symbol);
        }
        return symbol;
    }

    private static void printBoard() {
        Arrays.stream(chessBoard)
                .map(String::valueOf)
                .forEach(System.out::println);
        System.out.println();
    }
}
