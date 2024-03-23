package view;

import domain.board.Board;
import domain.board.Position;
import domain.piece.*;

public class OutputView {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String NEW_LINE = System.lineSeparator();

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다." + NEW_LINE +
                "> 게임 시작 : " + START_COMMAND + NEW_LINE +
                "> 게임 종료 : " + END_COMMAND + NEW_LINE +
                "> 게임 이동 : " + MOVE_COMMAND + " source위치 target위치 - 예. " + MOVE_COMMAND + " b2 b3");
    }

    public void printBoard(Board board) {
        for (int rank = 8; rank >= 1; rank--) {
            printOneRank(board, rank);
        }
        System.out.println();
    }

    private void printOneRank(Board board, int rank) {
        for (int file = 1; file <= 8; file++) {
            Piece piece = board.findPieceAt(Position.of(file, rank));
            System.out.print(pieceToString(piece));
        }
        System.out.println();
    }

    private String pieceToString(Piece piece) {
        String pieceText = "";
        if (piece instanceof Empty) {
            pieceText = ".";
        }
        if (piece instanceof Bishop) {
            pieceText = "B";
        }
        if (piece instanceof King) {
            pieceText = "K";
        }
        if (piece instanceof Knight) {
            pieceText = "N";
        }
        if (piece instanceof Pawn) {
            pieceText = "P";
        }
        if (piece instanceof Queen) {
            pieceText = "Q";
        }
        if (piece instanceof Rook) {
            pieceText = "R";
        }

        if (piece.isWhite()) {
            return pieceText.toLowerCase();
        }
        return pieceText;
    }
}
