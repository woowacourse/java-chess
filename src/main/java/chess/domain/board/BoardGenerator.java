package chess.domain.board;

import chess.domain.piece.piecefigure.Piece;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;

import java.util.*;
import java.util.stream.IntStream;

public class BoardGenerator {
    private static final int FIRST_LETTER = 0;
    private static final int MIN_BOUND = 0;
    private static final int MAX_BOUND = 7;
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final String BLANK = "";

    public static Board createBoard(String input) {
        List<List<String>> rows = convertListBy(input.replaceAll(SPACE, BLANK));

        return new Board(fillPieces(rows));
    }

    private static Map<Position, Piece> fillPieces(List<List<String>> rows) {
        Map<Position, Piece> pieces = new LinkedHashMap<>();

        IntStream.rangeClosed(MIN_BOUND, MAX_BOUND)
                .forEach(row -> pieces.putAll(fillRow(rows, row)));
        return pieces;
    }

    private static Map<Position, Piece> fillRow(List<List<String>> rows, int row) {
        Map<Position, Piece> pieces = new LinkedHashMap<>();

        IntStream.rangeClosed(MIN_BOUND, MAX_BOUND)
                .forEach(col -> {
                    Position position = Position.of(Position.makeKey(row, col));
                    String piece = rows.get(row).get(col);
                    pieces.put(position, PieceType.getPiece(piece, judgeTeamType(piece)));
                });
        return pieces;
    }

    private static List<List<String>> convertListBy(String input) {
        List<List<String>> rows = new ArrayList<>();

        for (String row : input.split(NEW_LINE)) {
            rows.add(Arrays.asList(row.split(BLANK)));
        }
        return rows;
    }

    private static TeamType judgeTeamType(String teamType) {
        return isBlackTeam(teamType) ? TeamType.BLACK : TeamType.WHITE;
    }

    private static boolean isBlackTeam(String teamType) {
        return Character.isUpperCase(teamType.charAt(FIRST_LETTER));
    }
}
