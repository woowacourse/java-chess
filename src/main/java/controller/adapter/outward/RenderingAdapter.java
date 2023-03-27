package controller.adapter.outward;

import domain.board.Board;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.move.Coordinate;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class RenderingAdapter {

    public static final int RANK_SIZE = 8;
    public static final int COL_SIZE = 8;

    public static final String GAME_SCORE_FORMAT = "%s : %.1f";

    public static String unpackBoard(final Board board) {
        Map<Coordinate, Piece> squareLocations = board.getPieceLocations();
        return unpackSquareLocations(squareLocations);
    }

    private static String unpackSquareLocations(final Map<Coordinate, Piece> squareLocations) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < RANK_SIZE; index++) {
            stringBuilder.insert(0, makeRank(squareLocations, index));
            stringBuilder.insert(0, System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private static String makeRank(final Map<Coordinate, Piece> squareLocations, final int rankNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int col = 0; col < COL_SIZE; col++) {
            Coordinate coordinate = new Coordinate(rankNumber, col);
            stringBuilder.append(PieceTypeMapper.getTarget(squareLocations.get(coordinate)));
        }
        return stringBuilder.toString();
    }

    public static String unpackGameResult(final Map<Color, Double> gameResult) {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        gameResult.forEach((k, v) -> stringJoiner.add(String.format(GAME_SCORE_FORMAT, k, v)));
        return stringJoiner.toString();
    }

    public static String convertWinningColor(final List<Color> winningColor) {
        StringJoiner stringJoiner = new StringJoiner(",");
        winningColor.forEach(color -> stringJoiner.add(color.toString()));
        return stringJoiner.toString();
    }
}
