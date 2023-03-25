package chess.view;

import java.util.Arrays;
import java.util.List;

import chess.controller.dto.PieceResponse;

public class OutputView {

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printPieces(List<PieceResponse> pieces) {
        char[][] board = setUpBoard();
        writePiecesToBoard(pieces, board);
        printBoard(board);
    }

    public void printException(String message) {
        System.out.println("[ERROR] " + message);
    }

    private char[][] setUpBoard() {
        char[][] board = new char[8][8];
        for (char[] line : board) {
            Arrays.fill(line, '.');
        }
        return board;
    }

    private void writePiecesToBoard(List<PieceResponse> pieces, char[][] board) {
        for (PieceResponse piece : pieces) {
            int y = piece.getRankIndex() - 1;
            int x = piece.getFileIndex() - 1;
            board[y][x] = piece.getLetter();
        }
    }

    private void printBoard(char[][] board) {
        for (int i = board.length - 1; i >= 0; i--) {
            printLine(board[i]);
            System.out.println();
        }
    }

    private void printLine(char[] line) {
        for (char square : line) {
            System.out.print(square);
        }
    }
}
