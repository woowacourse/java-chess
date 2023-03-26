package view;

import domain.piece.Piece;
import domain.piece.Team;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {
    private static final String EMPTY_SPACE = ".";
    private static final String KING = "King";
    private static final String BISHOP = "Bishop";
    private static final String KNIGHT = "Knight";
    private static final String PAWN = "Pawn";
    private static final String QUEEN = "Queen";
    private static final String KING_VIEW = "K";
    private static final String BISHOP_VIEW = "B";
    private static final String KNIGHT_VIEW = "N";
    private static final String PAWN_VIEW = "P";
    private static final String QUEEN_VIEW = "Q";
    private static final String ROOK_VIEW = "R";
    private static final String WINNER_BLACK = "Black";
    private static final String WINNER_WHITE = "White";
    private static final String WINNER_DRAW = "없음";

    public static void printBoard(final Map<Position, Piece> board) {
        Arrays.stream(Rank.values())
                .filter(rank -> !Rank.NOTHING.equals(rank))
                .map(rank -> makeAnRankView(board, rank))
                .forEach(System.out::println);
        System.out.println();
    }

    private static String makeAnRankView(final Map<Position, Piece> board, final Rank rank) {
        return Arrays.stream(File.values())
                .filter(file -> !File.NOTHING.equals(file))
                .map(file -> Position.of(file, rank))
                .map(position -> makePieceView(board, position))
                .collect(Collectors.joining());
    }

    private static String makePieceView(final Map<Position, Piece> board, final Position position) {
        if (board.containsKey(position)) {
            return convertPieceByTeam(board.get(position));
        }

        return EMPTY_SPACE;
    }

    private static String convertPieceByTeam(final Piece piece) {
        String name = makePieceName(piece);
        if (!piece.isBlack()) {
            name = name.toLowerCase();
        }

        return name;
    }

    private static String makePieceName(final Piece piece) {
        final String name = piece.getName();
        if (name.equals(KING)) {
            return KING_VIEW;
        }
        if (name.equals(BISHOP)) {
            return BISHOP_VIEW;
        }
        if (name.equals(KNIGHT)) {
            return KNIGHT_VIEW;
        }
        if (name.equals(PAWN)) {
            return PAWN_VIEW;
        }
        if (name.equals(QUEEN)) {
            return QUEEN_VIEW;
        }
        return ROOK_VIEW;
    }

    public static void printStatus(double blackScore, double whiteScore) {
        System.out.println("Black Score: " + blackScore);
        System.out.println("White Score: " + whiteScore);
        printCurrentWinner(blackScore, whiteScore);
        System.out.println();
    }

    private static void printCurrentWinner(final double blackScore, final double whiteScore) {
        System.out.print("현재 승리: ");
        if (blackScore > whiteScore) {
            System.out.println(WINNER_BLACK);
        }
        if (blackScore < whiteScore) {
            System.out.println(WINNER_WHITE);
        }
        if (blackScore == whiteScore) {
            System.out.println(WINNER_DRAW);
        }
    }

    public static void printWinner(final Team winner) {
        System.out.println(winner.name() + "가 승리했습니다!!");
    }

    public static void printError(final String errorMessage) {
        System.out.println(errorMessage);
        System.out.println();
    }
}
