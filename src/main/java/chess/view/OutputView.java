package chess.view;

import chess.domain.Score;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

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
    private static final String ERROR_PREFIX = "[ERROR] : ";
    private static final String STATUS_PREFIX = "## 중간점수 ##";
    private static final String RESULT_PREFIX = "## 최종결과 ##";

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printChessBoard(final Map<Position, ChessPiece> pieceByPosition) {
        for (int file = FILE_END; file >= FILE_START; file--) {
            printRank(pieceByPosition, file);
        }
    }

    private static void printRank(final Map<Position, ChessPiece> pieceByPosition, final int file) {
        for (int rank = RANK_START; rank <= RANK_END; rank++) {
            printPosition(pieceByPosition, (char) rank, file);
        }
        System.out.println();
    }

    private static void printPosition(final Map<Position, ChessPiece> chessBoard, final char rank, final int file) {
        final String position = rank + String.valueOf(file);
        final ChessPiece chessPiece = chessBoard.get(Position.from(position));
        if (Objects.isNull(chessPiece)) {
            System.out.print(EMPTY);
            return;
        }
        System.out.print(chessPiece.name());
    }

    public static void printResult(final Score score) {
        System.out.println(RESULT_PREFIX);
        printTotalScore(score);
    }

    public static void printStatus(final Score score) {
        System.out.println(STATUS_PREFIX);
        printTotalScore(score);
    }

    private static void printTotalScore(final Score score) {
        Arrays.stream(Color.values())
                .forEach(color -> printScore(color, score));
    }

    private static void printScore(final Color color, final Score score) {
        System.out.print(color.name() + DELIMITER);
        final double value = score.findScore(color);
        if (value == (int) value) {
            System.out.printf("%d%n", (int) value);
            return;
        }
        System.out.printf("%s%n", value);
    }

    public static void printError(final String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }
}
