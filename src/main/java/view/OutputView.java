package view;

import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {
    private static final String EMPTY_SPACE = ".";

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
            return convertCaseByTeam(board.get(position));
        }

        return EMPTY_SPACE;
    }

    private static String convertCaseByTeam(final Piece piece) {
        if (piece.isBlack()) {
            return piece.getName();
        }

        return piece.getName().toLowerCase();
    }

    public static void printError(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
