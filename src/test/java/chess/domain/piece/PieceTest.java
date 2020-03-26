package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Path;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class PieceTest {
    @DisplayName("체스 말 move test")
    @ParameterizedTest(name = "{0}")
    @MethodSource("movableParams")
    void movable(String message, Type type, Path path, boolean expected) {
        assertThat(Piece.of(type, Side.WHITE).isMovable(path)).isEqualTo(expected);
    }

    static Stream<Arguments> movableParams() {
        Board board = new Board(new HashMap<Position, Optional<Piece>>() {{
            Position.getAllPositions().forEach(position -> put(position, Optional.empty()));
            final Side side = Side.WHITE;
            final Row row = Row.ONE;
            put(Position.of(row, Column.A), Optional.of(Piece.of(Type.ROOK, side)));
            put(Position.of(row, Column.B), Optional.of(Piece.of(Type.KNIGHT, side)));
            put(Position.of(row, Column.C), Optional.of(Piece.of(Type.BISHOP, side)));
            put(Position.of(row, Column.D), Optional.of(Piece.of(Type.QUEEN, side)));
            put(Position.of(row, Column.E), Optional.of(Piece.of(Type.KING, side)));
            put(Position.of(row, Column.F), Optional.of(Piece.of(Type.PAWN, side)));
        }});
        return Stream.of(
            of(
                "시작과 끝이 같은 경우 false",
                Type.QUEEN, board.generatePath(Position.of(Row.ONE, Column.D), Position.of(Row.ONE, Column.D)),
                false
            ),
            of(
                "시작과 끝이 다른 경우 true",
                Type.QUEEN, board.generatePath(Position.of(Row.ONE, Column.D), Position.of(Row.EIGHT, Column.D)),
                true
            ),
            of(
                "King의 정상적인 이동",
                Type.KING, board.generatePath(Position.of(Row.ONE, Column.E), Position.of(Row.TWO, Column.E)),
                true
            ),
            of(
                "King의 비정상적인 이동",
                Type.KING, board.generatePath(Position.of(Row.ONE, Column.E), Position.of(Row.THREE, Column.B)),
                false
            ),
            of(
                "Rook의 정상적인 이동",
                Type.ROOK, board.generatePath(Position.of(Row.ONE, Column.A), Position.of(Row.EIGHT, Column.A)),
                true
            ),
            of(
                "Rook의 비정상적인 이동",
                Type.ROOK, board.generatePath(Position.of(Row.ONE, Column.A), Position.of(Row.EIGHT, Column.B)),
                false
            ),
            of(
                "Bishop의 정상적인 이동",
                Type.BISHOP, board.generatePath(Position.of(Row.ONE, Column.B), Position.of(Row.TWO, Column.C)),
                true
            ),
            of(
                "Bishop의 비정상적인 이동",
                Type.BISHOP, board.generatePath(Position.of(Row.ONE, Column.B), Position.of(Row.EIGHT, Column.A)),
                false
            ),
            of(
                "Queen의 직선 이동",
                Type.QUEEN, board.generatePath(Position.of(Row.ONE, Column.D), Position.of(Row.EIGHT, Column.D)),
                true
            ),
            of(
                "Queen의 대각선 이동",
                Type.QUEEN, board.generatePath(Position.of(Row.ONE, Column.D), Position.of(Row.FOUR, Column.A)),
                true
            ),
            of(
                "Queen의 비정상적인 이동",
                Type.QUEEN, board.generatePath(Position.of(Row.ONE, Column.D), Position.of(Row.THREE, Column.C)),
                false
            ),
            of(
                "Knight의 정상적인 이동",
                Type.KNIGHT, board.generatePath(Position.of(Row.ONE, Column.A), Position.of(Row.THREE, Column.B)),
                true
            ),
            of(
                "Knight의 비정상적인 이동",
                Type.KNIGHT, board.generatePath(Position.of(Row.ONE, Column.A), Position.of(Row.THREE, Column.C)),
                false
            ),
            of(
                "White Pawn의 정상적인 이동",
                Type.PAWN, board.generatePath(Position.of(Row.ONE, Column.F), Position.of(Row.TWO, Column.F)),
                true
            ),
            of(
                "White Pawn의 비정상적인 이동",
                Type.PAWN, board.generatePath(Position.of(Row.ONE, Column.F), Position.of(Row.EIGHT, Column.A)),
                false
            )
        );
    }
}
