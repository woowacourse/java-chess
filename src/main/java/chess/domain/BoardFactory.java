package chess.domain;

import static chess.domain.Role.BISHOP;
import static chess.domain.Role.KING;
import static chess.domain.Role.KNIGHT;
import static chess.domain.Role.PAWN;
import static chess.domain.Role.QUEEN;
import static chess.domain.Role.ROOK;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class BoardFactory {
    private static final List<Role> CHESSMEN = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
    private static final int BOARD_ROW_SIZE = 8;

    private BoardFactory() {

    }

    public static List<Square> create() {
        List<Square> squares = new ArrayList<>();
        squares.addAll(generateRow(index -> new Piece(CHESSMEN.get(index), WHITE), 0));
        squares.addAll(generateRow(ignore -> new Piece(PAWN, WHITE), 1));
        squares.addAll(generateRow(ignore -> Piece.EMPTY_PIECE, 2));
        squares.addAll(generateRow(ignore -> Piece.EMPTY_PIECE, 3));
        squares.addAll(generateRow(ignore -> Piece.EMPTY_PIECE, 4));
        squares.addAll(generateRow(ignore -> Piece.EMPTY_PIECE, 5));
        squares.addAll(generateRow(ignore -> new Piece(PAWN, BLACK), 6));
        squares.addAll(generateRow(index -> new Piece(CHESSMEN.get(index), BLACK), 7));
        return squares;
    }

    private static List<Square> generateRow(IntFunction<Piece> function, int y) {
        return IntStream.range(0, BOARD_ROW_SIZE)
                .mapToObj(value -> new Square(function.apply(value), Position.of(value, y)))
                .collect(toList());
    }
}
