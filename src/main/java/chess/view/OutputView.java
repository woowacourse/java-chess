package chess.view;

import chess.controller.dto.PieceResponse;
import chess.domain.game.Status;
import chess.domain.piece.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OutputView {

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 점수 출력 : status");
    }

    public void printPieces(List<PieceResponse> pieces) {
        char[][] board = setUpBoard();
        addPieceToBoard(pieces, board);
        printBoard(board);
    }

    private char[][] setUpBoard() {
        char[][] board = new char[8][8];
        for (char[] line : board) {
            Arrays.fill(line, '.');
        }
        return board;
    }

    private void addPieceToBoard(List<PieceResponse> pieces, char[][] board) {
        for (PieceResponse piece : pieces) {
            int y = piece.getRankIndex() - 1;
            int x = piece.getFileIndex() - 1;
            board[y][x] = getPieceDisplay(piece.getType(), piece.getColor());
        }
    }

    private char getPieceDisplay(String type, String color) {
        return PieceType.valueOf(type).getDisplayOf(color);
    }

    private void printBoard(char[][] board) {
        System.out.println();
        for (int i = board.length - 1; i >= 0; i--) {
            char[] line = board[i];
            System.out.println(String.copyValueOf(line));
        }
    }

    public void printStatus(Status status) {
        printColorStatus(status, Color.WHITE);
        printColorStatus(status, Color.BLACK);
        printWinner(status.getWinner());
    }

    private void printColorStatus(Status status, Color color) {
        System.out.println(color.name() + ": " + status.getScore(color).getValue());
    }

    private void printWinner(Color winner) {
        if (winner == Color.NONE) {
            System.out.println("현재 동점입니다.");
            return;
        }
        System.out.println("현재 " + winner.name() + "이(가) 앞서고있습니다.");
    }

    private enum PieceType {
        KING('K'), QUEEN('Q'), PAWN('P'), ROOK('R'), BISHOP('B'), KNIGHT('N');

        private final char display;

        PieceType(char display) {
            this.display = display;
        }

        public char getDisplayOf(String color) {
            if (Objects.equals(color, "WHITE")) {
                return Character.toLowerCase(display);
            }
            return display;
        }
    }
}
