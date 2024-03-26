package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.ColumnPosition;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class NormalPieceSetting {
    private static final Map<Piece, List<Position>> INITIAL_BLACK_PIECES_SETTING = new HashMap<>() {
        {
            List<Position> rookPositions = List.of(Position.of(0, 0), Position.of(0, 7));
            put(new Rook(Team.BLACK), rookPositions);

            List<Position> knightPositions = List.of(Position.of(0, 1), Position.of(0, 6));
            put(new Knight(Team.BLACK), knightPositions);

            List<Position> bishopPositions = List.of(Position.of(0, 2), Position.of(0, 5));
            put(new Bishop(Team.BLACK), bishopPositions);

            List<Position> queenPositions = List.of(Position.of(0, 3));
            put(new Queen(Team.BLACK), queenPositions);

            List<Position> kingPositions = List.of(Position.of(0, 4));
            put(new King(Team.BLACK), kingPositions);

            List<Position> pawnPositions = IntStream.rangeClosed(ColumnPosition.MIN_NUMBER, ColumnPosition.MAX_NUMBER)
                    .mapToObj(col -> Position.of(1, col))
                    .toList();
            put(Pawn.blackPawn(), pawnPositions);
        }
    };

    private static final Map<Piece, List<Position>> INITIAL_WHITE_PIECES_SETTING = new HashMap<>() {
        {
            List<Position> rookPositions = List.of(Position.of(7, 0), Position.of(7, 7));
            put(new Rook(Team.WHITE), rookPositions);

            List<Position> knightPositions = List.of(Position.of(7, 1), Position.of(7, 6));
            put(new Knight(Team.WHITE), knightPositions);

            List<Position> bishopPositions = List.of(Position.of(7, 2), Position.of(7, 5));
            put(new Bishop(Team.WHITE), bishopPositions);

            List<Position> queenPositions = List.of(Position.of(7, 3));
            put(new Queen(Team.WHITE), queenPositions);

            List<Position> kingPositions = List.of(Position.of(7, 4));
            put(new King(Team.WHITE), kingPositions);

            List<Position> pawnPositions = IntStream.rangeClosed(ColumnPosition.MIN_NUMBER, ColumnPosition.MAX_NUMBER)
                    .mapToObj(col -> Position.of(6, col))
                    .toList();
            put(Pawn.whitePawn(), pawnPositions);
        }
    };

    private NormalPieceSetting() {
    }

    public static Map<Piece, List<Position>> blackPiecesSetting() {
        return Map.copyOf(INITIAL_BLACK_PIECES_SETTING);
    }

    public static Map<Piece, List<Position>> whitePiecesSetting() {
        return Map.copyOf(INITIAL_WHITE_PIECES_SETTING);
    }
}
