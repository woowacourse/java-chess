package chess.view;

import chess.domain.ChessBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.Color;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Optional;

public class OutputView {

    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : start\n" +
            "> 게임 종료 : end\n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final int FILE_START = 1;
    private static final int FILE_END = 8;
    private static final char RANK_START = 'a';
    private static final char RANK_END = 'h';
    private static final String EMPTY = ".";
    private static final String DELIMITER = " : ";
    private static final String ERROR = "[ERROR] : ";
    private static final String STATUS = "## 중간점수 ##";
    private static final String RESULT = "## 최종결과 ##";

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printChessBoard(final ChessBoard chessBoard) {
        for (int j = FILE_END; j >= FILE_START; j--) {
            printRank(chessBoard, j);
        }
    }

    private static void printRank(final ChessBoard chessBoard, final int j) {
        for (int i = RANK_START; i <= RANK_END; i++) {
            final String position = (char) i + String.valueOf(j);

            final Optional<ChessPiece> possiblePiece = chessBoard.findPiece(Position.from(position));
            possiblePiece.ifPresentOrElse(
                    (piece) -> System.out.print(piece.getName()),
                    () -> System.out.print(EMPTY));
        }
        System.out.println();
    }

    public static void printStatus(final Map<Color, Double> scoreByColor) {
        System.out.println(STATUS);
        printTotalScore(scoreByColor);
    }

    public static void printResult(final Map<Color, Double> scoreByColor) {
        System.out.println(RESULT);
        printTotalScore(scoreByColor);
    }

    private static void printTotalScore(final Map<Color, Double> scoreByColor) {
        for (final Map.Entry<Color, Double> entry : scoreByColor.entrySet()) {
            printScore(entry);
        }
    }

    private static void printScore(final Map.Entry<Color, Double> entry) {
        System.out.print(entry.getKey().name() + DELIMITER);

        final double score = entry.getValue();
        if (score == (int) score) {
            System.out.printf("%d%n", (int) score);
            return;
        }
        System.out.printf("%s%n", score);
    }

    public static void printError(final String errorMessage) {
        System.out.println(ERROR + errorMessage);
    }
}
