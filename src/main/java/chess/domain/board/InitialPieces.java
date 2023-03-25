package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Column;
import chess.domain.piece.coordinate.Coordinate;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum InitialPieces {
    NOBLE_LINE(
        List.of(Rook::new, Knight::new, Bishop::new, Queen::new, King::new, Bishop::new, Knight::new, Rook::new)),
    PAWN_LINE(List.of(Pawn::new, Pawn::new, Pawn::new, Pawn::new, Pawn::new, Pawn::new, Pawn::new, Pawn::new)),
    EMPTY_LINE(List.of(Empty::new, Empty::new, Empty::new, Empty::new, Empty::new, Empty::new, Empty::new, Empty::new));

    private static final String FIRST_INITIAL_ROW_OF_NOBLE = "1";
    private static final String SECOND_INITIAL_ROW_OF_NOBLE = "8";
    private static final String FIRST_INITIAL_ROW_OF_PAWN = "2";
    private static final String SECOND_INITIAL_ROW_OF_PAWN = "7";
    private static final int MIN_COLUMN_INDEX = 0;
    private static final int MAX_COLUMN_INDEX = 7;
    private final List<BiFunction<Team, Coordinate, Piece>> pieces;

    InitialPieces(List<BiFunction<Team, Coordinate, Piece>> pieces) {
        this.pieces = pieces;
    }

    public static List<Piece> from(String rowNum) {
        Team team = Team.from(rowNum);
        if (isNobleRow(rowNum)) {
            return getPieces(NOBLE_LINE, rowNum, team);
        }

        if (isPawnRow(rowNum)) {
            return getPieces(PAWN_LINE, rowNum, team);
        }

        return getPieces(EMPTY_LINE, rowNum, team);
    }

    private static List<Piece> getPieces(InitialPieces symbols, String rowNum, Team team) {
        return IntStream.rangeClosed(MIN_COLUMN_INDEX, MAX_COLUMN_INDEX)
            .mapToObj(columnIndex -> symbols.pieces.get(columnIndex)
                .apply(team, Coordinate.createCoordinate(rowNum, Column.symbolFromIndex(columnIndex + 1))))
            .collect(Collectors.toList());
    }

    private static boolean isPawnRow(String rowNum) {
        return rowNum.equals(FIRST_INITIAL_ROW_OF_PAWN) || rowNum.equals(SECOND_INITIAL_ROW_OF_PAWN);
    }

    private static boolean isNobleRow(String rowNum) {
        return rowNum.equals(FIRST_INITIAL_ROW_OF_NOBLE) || rowNum.equals(SECOND_INITIAL_ROW_OF_NOBLE);
    }

}
