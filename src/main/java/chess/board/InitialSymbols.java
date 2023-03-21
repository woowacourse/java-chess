package chess.board;

import chess.piece.Bishop;
import chess.piece.Empty;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;
import chess.piece.coordinate.Coordinate;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum InitialSymbols {
    NOBLE_LINE(List.of(Rook::new, Knight::new, Bishop::new, Queen::new, King::new, Bishop::new, Knight::new, Rook::new)),
    PAWN_LINE(List.of(Pawn::new,Pawn::new,Pawn::new,Pawn::new,Pawn::new,Pawn::new,Pawn::new,Pawn::new)),
    EMPTY_LINE(List.of(Empty::new,Empty::new,Empty::new,Empty::new,Empty::new,Empty::new,Empty::new,Empty::new));
    
    private static final int FIRST_INITIAL_ROW_OF_NOBLE = 1;
    private static final int SECOND_INITIAL_ROW_OF_NOBLE = 8;
    private static final int FIRST_INITIAL_ROW_OF_PAWN = 2;
    private static final int SECOND_INITIAL_ROW_OF_PAWN = 7;
    private static final int MIN_COLUMN_INDEX = 0;
    private static final int MAX_COLUMN_INDEX = 7;
    private static final char MIN_COLUMN_CHAR = 'a';
    private final List<BiFunction<Team, Coordinate, Piece>> pieces;
    
    InitialSymbols(List<BiFunction<Team, Coordinate, Piece>> pieces) {
        this.pieces = pieces;
    }
    
    public static List<Piece> from(int rowNum, Team team) {
        if (isNobleRow(rowNum)) {
            return getPieces(NOBLE_LINE,rowNum, team);
        }
    
        if (isPawnRow(rowNum)) {
            return getPieces(PAWN_LINE,rowNum, team);
        }

        return getPieces(EMPTY_LINE,rowNum, team);
    }

    private static List<Piece> getPieces(InitialSymbols symbols,int rowNum, Team team) {
        return IntStream.rangeClosed(MIN_COLUMN_INDEX, MAX_COLUMN_INDEX)
            .mapToObj(columnIndex -> symbols.pieces.get(columnIndex)
                .apply(team, Coordinate.createCoordinate(rowNum, parseColumn(columnIndex))))
            .collect(Collectors.toList());
    }

    private static char parseColumn(int columnIndex) {
        return (char) (columnIndex + MIN_COLUMN_CHAR);
    }
    
    private static boolean isPawnRow(int rowNum) {
        return rowNum == FIRST_INITIAL_ROW_OF_PAWN || rowNum == SECOND_INITIAL_ROW_OF_PAWN;
    }
    
    private static boolean isNobleRow(int rowNum) {
        return rowNum == FIRST_INITIAL_ROW_OF_NOBLE || rowNum == SECOND_INITIAL_ROW_OF_NOBLE;
    }

}
