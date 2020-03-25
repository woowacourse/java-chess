package chess.view;

import chess.domains.board.PlayingPiece;

import java.util.List;

public class OutputView {

    public static final String END = "end";

    public static void printBoard(List<PlayingPiece> showingBoard) {
        StringBuilder sb = new StringBuilder();
        for (PlayingPiece piece : showingBoard) {
            sb.append(piece.showPieceName());
            if (sb.length() == 8) {
                System.out.println(sb);
                sb = new StringBuilder();
            }
        }
    }

    public static void printEnd() {
        System.out.println(END);
    }
}
