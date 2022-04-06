package chess.view;

import chess.domain.game.Color;
import chess.domain.game.board.ChessBoard;
import chess.domain.piece.ChessPiece;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Optional;

public class OutputView {

    private static final char RANK_START = 'a';
    private static final char RANK_END = 'h';
    private static final int FILE_END = 8;
    private static final int FILE_START = 1;
    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : start\n" +
            "> 게임 종료 : end\n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String EMPTY = ".";
    private static final String DELIMITER = " : ";
    private static final String ERROR = "[ERROR] : ";
    private static final String STATUS = "## 중간점수 ##";
    private static final String RESULT = "## 최종결과 ##";
    private static final String FILE_FORMAT = "abcdefgh";
    private static final String RANK_INFO = " (rank %d)";
    private static final String RANK_FORMAT = "  %d";
    private static final String WINNER_FORMAT = "승자는 %s 입니다.%n";

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        for (int j = FILE_END; j >= FILE_START; j--) {
            printRank(chessBoard, j);
            System.out.printf(String.format(RANK_FORMAT, j));

            printRankFormat(j);
        }
        printFileFormat();
    }

    private static void printFileFormat() {
        System.out.println();
        System.out.println(FILE_FORMAT);
    }

    private static void printRankFormat(int j) {
        if (j == FILE_START || j == FILE_END) {
            System.out.printf(String.format(RANK_INFO, j));
        }
        System.out.println();
    }

    private static void printRank(ChessBoard chessBoard, int j) {
        for (int i = RANK_START; i <= RANK_END; i++) {
            String position = (char) i + String.valueOf(j);

            Optional<ChessPiece> possiblePiece = chessBoard.findPiece(new Position(position));
            possiblePiece.ifPresentOrElse(OutputView::printPieceByColor, () -> System.out.print(EMPTY));
        }
    }

    private static void printPieceByColor(ChessPiece piece) {
        if (piece.isBlack()) {
            System.out.printf(Color.BLACK.convertByColor(piece.getName()));
            return;
        }
        System.out.printf(Color.WHITE.convertByColor(piece.getName()));

    }


    public static void printStatus(Map<Color, Double> scoreByColor) {
        System.out.println(STATUS);
        printTotalScore(scoreByColor);
    }

    public static void printResult(Map<Color, Double> scoreByColor, Color color) {
        System.out.println(RESULT);
        printTotalScore(scoreByColor);
        printWinner(color);
    }

    private static void printWinner(Color winnerColor) {
        System.out.printf(WINNER_FORMAT, winnerColor.name());
    }

    private static void printTotalScore(Map<Color, Double> scoreByColor) {
        for (Map.Entry<Color, Double> entry : scoreByColor.entrySet()) {
            printScore(entry);
        }
    }

    private static void printScore(Map.Entry<Color, Double> entry) {
        System.out.print(entry.getKey().name() + DELIMITER);

        double score = entry.getValue();
        if (score == (int) score) {
            System.out.printf("%d%n", (int) score);
            return;
        }
        System.out.printf("%s%n", score);
    }

    public static void printError(String errorMessage) {
        System.out.println(ERROR + errorMessage);
    }
}
