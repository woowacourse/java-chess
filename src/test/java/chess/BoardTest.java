package chess;

import chess.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @DisplayName("기물을 각자의 자리에 생성한다.")
    @MethodSource("boardInitTestSet")
    void test(String position, Class<? extends Piece> type, Color color) {
        Board board = new Board();
        boolean exist = board.exist(position, type, color);

        assertThat(exist).isTrue();
    }

    static Stream<Arguments> boardInitTestSet() {
        return Stream.of(
                Arguments.of("a8", Rook.class, Color.BLACK),
                Arguments.of("b8", Knight.class, Color.BLACK),
                Arguments.of("c8", Bishop.class, Color.BLACK),
                Arguments.of("d8", Queen.class, Color.BLACK),
                Arguments.of("e8", King.class, Color.BLACK),
                Arguments.of("f8", Bishop.class, Color.BLACK),
                Arguments.of("g8", Knight.class, Color.BLACK),
                Arguments.of("h8", Rook.class, Color.BLACK),

                Arguments.of("a7", Pawn.class, Color.BLACK),
                Arguments.of("b7", Pawn.class, Color.BLACK),
                Arguments.of("c7", Pawn.class, Color.BLACK),
                Arguments.of("d7", Pawn.class, Color.BLACK),
                Arguments.of("e7", Pawn.class, Color.BLACK),
                Arguments.of("f7", Pawn.class, Color.BLACK),
                Arguments.of("g7", Pawn.class, Color.BLACK),
                Arguments.of("h7", Pawn.class, Color.BLACK),

                Arguments.of("a1", Rook.class, Color.WHITE),
                Arguments.of("b1", Knight.class, Color.WHITE),
                Arguments.of("c1", Bishop.class, Color.WHITE),
                Arguments.of("d1", Queen.class, Color.WHITE),
                Arguments.of("e1", King.class, Color.WHITE),
                Arguments.of("f1", Bishop.class, Color.WHITE),
                Arguments.of("g1", Knight.class, Color.WHITE),
                Arguments.of("h1", Rook.class, Color.WHITE),

                Arguments.of("a2", Pawn.class, Color.WHITE),
                Arguments.of("b2", Pawn.class, Color.WHITE),
                Arguments.of("c2", Pawn.class, Color.WHITE),
                Arguments.of("d2", Pawn.class, Color.WHITE),
                Arguments.of("e2", Pawn.class, Color.WHITE),
                Arguments.of("f2", Pawn.class, Color.WHITE),
                Arguments.of("g2", Pawn.class, Color.WHITE),
                Arguments.of("h2", Pawn.class, Color.WHITE)
        );
    }
}
