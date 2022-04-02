package chess.console.view;

import static java.util.stream.Collectors.joining;

import chess.domain.Result;
import chess.domain.piece.Piece;
import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String NEW_LINE = System.lineSeparator();

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message + NEW_LINE);
    }

    public static void printChessBoard(List<List<Piece>> board) {
        board.stream()
                .map(OutputView::generateRow)
                .forEach(System.out::println);

        System.out.println();
    }

    private static String generateRow(List<Piece> pieces) {
        return pieces.stream()
                .map(Piece::getNotation)
                .collect(joining());
    }

    public static void printStatusMessage(double blackScore, double whiteScore) {
        System.out.println("WHITE SCORE: " + whiteScore);
        System.out.println("BLACK SCORE: " + blackScore);

        System.out.println();
    }

    public static void printResultMessage(Result result) {
        if (result.isDraw()) {
            System.out.println("무승부입니다.");
            return;
        }

        System.out.println(result + "가 승리 하였습니다.");
    }
}
