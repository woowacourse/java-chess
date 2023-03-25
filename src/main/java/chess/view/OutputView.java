package chess.view;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.piece.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String EMPTY_SQUARE = ".";
    private static final String NEW_LINE = System.lineSeparator();
    private static final Map<Class<? extends Piece>, String> UPPER_SIGN_BY_PIECE = Map.of(
            Rook.class, "R",
            Knight.class, "N",
            Bishop.class, "B",
            Queen.class, "Q",
            King.class, "K",
            Pawn.class, "P"
    );
    private static final Map<File, Integer> COLUMN_BY_FILE = Map.of(
            File.A, 0,
            File.B, 1,
            File.C, 2,
            File.D, 3,
            File.E, 4,
            File.F, 5,
            File.G, 6,
            File.H, 7
    );
    private static final Map<Rank, Integer> ROW_BY_RANK = Map.of(
            Rank.ONE, 7,
            Rank.TWO, 6,
            Rank.THREE, 5,
            Rank.FOUR, 4,
            Rank.FIVE, 3,
            Rank.SIX, 2,
            Rank.SEVEN, 1,
            Rank.EIGHT, 0
    );
    private static final Map<Color, String> MESSAGE_BY_COLOR = new LinkedHashMap<>(Map.of(
            Color.BLACK, "black",
            Color.WHITE, "white"
    ));

    public static void printGameStartGuideMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 상황 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final List<Piece> pieces) {
        final String boardMessage = generateBoardMessage(pieces);
        System.out.println(boardMessage);
    }

    private static String generateBoardMessage(final List<Piece> pieces) {
        final StringBuilder mapBuilder = generateEmptyBoardBuilder();

        for (Piece piece : pieces) {
            setUpPieceInBoard(mapBuilder, piece);
        }

        return mapBuilder.toString();
    }

    private static StringBuilder generateEmptyBoardBuilder() {
        final StringBuilder mapBuilder = new StringBuilder();
        for (int index = 0; index < ROW_BY_RANK.size(); index++) {
            mapBuilder.append(EMPTY_SQUARE.repeat(COLUMN_BY_FILE.size()))
                    .append(NEW_LINE);
        }
        return mapBuilder;
    }

    private static void setUpPieceInBoard(final StringBuilder mapBuilder, final Piece piece) {
        final int positionIndex = calculateIndex(piece.getPosition());
        mapBuilder.replace(positionIndex, positionIndex + 1, getPieceAccordingToSign(piece));
    }

    private static int calculateIndex(final Position position) {
        final int oneLineLength = COLUMN_BY_FILE.size() + NEW_LINE.length();
        return oneLineLength * ROW_BY_RANK.get(position.getRank()) + COLUMN_BY_FILE.get(position.getFile());
    }

    private static String getPieceAccordingToSign(final Piece piece) {
        final String upperPieceMessage = UPPER_SIGN_BY_PIECE.get(piece.getClass());
        if (piece.isBlack()) {
            return upperPieceMessage;
        }
        return upperPieceMessage.toLowerCase();
    }

    public static void printScores(final Map<Color, Double> scoreByColor) {
        for (Color color : MESSAGE_BY_COLOR.keySet()) {
            final Double score = scoreByColor.get(color);
            final String colorMessage = MESSAGE_BY_COLOR.get(color);
            System.out.printf("> %s: %s%n", colorMessage, score.toString());
        }
    }

    public static void printWinner(final Color color) {
        System.out.println(printWinnerMessage(color));
    }

    private static String printWinnerMessage(final Color color) {
        if (color == Color.NOTHING) {
            return "현재 동점입니다.%n";
        }
        return String.format("현재 %s 진영이 이기고 있습니다.%n", MESSAGE_BY_COLOR.get(color));
    }
}
