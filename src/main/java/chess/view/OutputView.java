package chess.view;

import java.util.List;

import chess.piece.Piece;

public class OutputView {
    private static final String MESSAGE_START = "체스 게임을 시작합니다.";

    public static void announceStart() {
        System.out.println(MESSAGE_START);
    }

    public static void showBoard(List<List<Piece>> board) {
        for (List<Piece> pieces : board) {
            pieces.stream()
                    .map(Piece::getEmoji)
                    .forEach(System.out::print);
            System.out.println();
        }
    }
}
