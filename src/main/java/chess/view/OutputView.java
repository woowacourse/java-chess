package chess.view;

import java.util.List;

import chess.domain.piece.Piece;

public class OutputView {
    private static final String MESSAGE_START = "> 체스 게임을 시작합니다.";
    private static final String MESSAGE_INPUT_START = "> 게임 시작 : start";
    private static final String MESSAGE_INPUT_END = "> 게임 종료 : end";
    private static final String MESSAGE_INPUT_MOVE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public static void announceStart() {
        System.out.println(MESSAGE_START);
        System.out.println(MESSAGE_INPUT_START);
        System.out.println(MESSAGE_INPUT_END);
        System.out.println(MESSAGE_INPUT_MOVE);
    }

    public static void showBoard(List<List<Piece>> board) {
        for (List<Piece> pieces : board) {
            pieces.stream()
                    .map(Piece::getEmoji)
                    .forEach(System.out::print);
            System.out.println();
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
