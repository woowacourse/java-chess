package chess.domain.board;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @DisplayName("기물을 각자의 자리에 생성한다.")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("boardInitTestSet")
    void test(Position position, Class<? extends Piece> type, Color color) {
        Board board = new Board();
        boolean exist = board.exist(position, type, color);

        assertThat(exist).isTrue();
    }

    static Stream<Arguments> boardInitTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a8"), Rook.class, Color.BLACK),
                Arguments.of(Position.of("b8"), Knight.class, Color.BLACK),
                Arguments.of(Position.of("c8"), Bishop.class, Color.BLACK),
                Arguments.of(Position.of("d8"), Queen.class, Color.BLACK),
                Arguments.of(Position.of("e8"), King.class, Color.BLACK),
                Arguments.of(Position.of("f8"), Bishop.class, Color.BLACK),
                Arguments.of(Position.of("g8"), Knight.class, Color.BLACK),
                Arguments.of(Position.of("h8"), Rook.class, Color.BLACK),

                Arguments.of(Position.of("a7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("b7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("c7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("d7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("e7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("f7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("g7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("h7"), Pawn.class, Color.BLACK),

                Arguments.of(Position.of("a1"), Rook.class, Color.WHITE),
                Arguments.of(Position.of("b1"), Knight.class, Color.WHITE),
                Arguments.of(Position.of("c1"), Bishop.class, Color.WHITE),
                Arguments.of(Position.of("d1"), Queen.class, Color.WHITE),
                Arguments.of(Position.of("e1"), King.class, Color.WHITE),
                Arguments.of(Position.of("f1"), Bishop.class, Color.WHITE),
                Arguments.of(Position.of("g1"), Knight.class, Color.WHITE),
                Arguments.of(Position.of("h1"), Rook.class, Color.WHITE),

                Arguments.of(Position.of("a2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("b2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("c2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("d2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("e2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("f2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("g2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("h2"), Pawn.class, Color.WHITE)
        );
    }
}
