package chess.domain;

import static chess.domain.Role.BISHOP;
import static chess.domain.Role.KING;
import static chess.domain.Role.KNIGHT;
import static chess.domain.Role.PAWN;
import static chess.domain.Role.QUEEN;
import static chess.domain.Role.ROOK;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

public class BoardFactory {

    private static final List<Role> CHESSMEN = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
    private static final int BOARD_ROW_SIZE = 8;

    private BoardFactory() {

    }

    public static Map<Position, Piece> create() {
        Map<Position, Piece> squares = new HashMap<>();
        squares.putAll(generateRow(index -> PieceFactory.of(CHESSMEN.get(index), WHITE), 0));
        squares.putAll(generateRow(ignore -> PieceFactory.of(PAWN, WHITE), 1));
        squares.putAll(generateRow(ignore -> Empty.INSTANCE, 2));
        squares.putAll(generateRow(ignore -> Empty.INSTANCE, 3));
        squares.putAll(generateRow(ignore -> Empty.INSTANCE, 4));
        squares.putAll(generateRow(ignore -> Empty.INSTANCE, 5));
        squares.putAll(generateRow(ignore -> PieceFactory.of(PAWN, BLACK), 6));
        squares.putAll(generateRow(index -> PieceFactory.of(CHESSMEN.get(index), BLACK), 7));
        return squares;
    }

    private static Map<Position, Piece> generateRow(IntFunction<Piece> function, int y) {
        Map<Position, Piece> squares = new HashMap<>();
        for (int x = 0; x < BOARD_ROW_SIZE; x++) {
            squares.put(Position.of(x, y), function.apply(x));
        }
        return squares;
    }
}
