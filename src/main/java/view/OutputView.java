package view;

import dto.PieceInfo;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(final List<PieceInfo> pieceInfo) {
        List<List<String>> board = new ArrayList<>();
        initBoard(board);
        putPiecesOnBoard(pieceInfo, board);
        printBoard(board);
        System.out.println();
    }

    private static void initBoard(final List<List<String>> board) {
        for (int rank = 0; rank < 8; rank++) {
            board.add(new ArrayList<>());
            setBoardEmpty(board, rank);
        }
    }

    private static void setBoardEmpty(final List<List<String>> board, final int rank) {
        for (int file = 0; file < 8; file++) {
            board.get(rank).add(".");
        }
    }

    private static void putPiecesOnBoard(final List<PieceInfo> pieceInfo, final List<List<String>> board) {
        for (PieceInfo piece : pieceInfo) {
            int rank = piece.position().rank();
            int file = piece.position().file();
            String shape = PieceShape.shapeValue(piece);

            board.get(rank).set(file, shape);
        }
    }

    private static void printBoard(final List<List<String>> board) {
        for (int rank = 7; rank >= 0; rank--) {
            printRank(board, rank);
            System.out.println();
        }
    }

    private static void printRank(final List<List<String>> board, final int rank) {
        for (int file = 0; file < 8; file++) {
            System.out.print(board.get(rank).get(file));
        }
    }
}
