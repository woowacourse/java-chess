package chess.model.move;

import static chess.model.board.PositionFixture.B1;
import static chess.model.board.PositionFixture.C3;
import static chess.model.board.PositionFixture.C4;
import static chess.model.board.PositionFixture.E2;
import static chess.model.board.PositionFixture.G1;
import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.KNIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.board.Board;
import chess.model.piece.type.Piece;
import chess.model.position.Position;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardKnightMoveTest {

    private Board board;

    @BeforeEach
    void init() {
        board = Board.create();
    }

    @Test
    @DisplayName("나이트가 움직일 수 있는 방향으로 움직이면 이동할 수 있다.")
    void move_knight_givenValidTarget_thenSuccess() {
        // when
        board.move(B1, C3, WHITE);

        // then
        final Map<Position, Piece> squares = board.getSquares();

        assertAll(
                () -> assertThat(squares.get(C3).isEmpty()).isFalse(),
                () -> assertThat(squares.get(C3).update().getType()).isEqualTo(KNIGHT)
        );
    }

    @ParameterizedTest(name = "나이트를 {0}에서 {1}로 이동하면 예외가 발생한다.")
    @MethodSource("initialWrongMoveKnight")
    void move_knight_givenInvalidTarget_thenFail(
            final Position source,
            final Position target,
            final Class<Exception> result
    ) {
        assertThatThrownBy(() -> board.move(source, target, WHITE))
                .isInstanceOf(result);
    }

    private static Stream<Arguments> initialWrongMoveKnight() {
        return Stream.of(
                Arguments.of(B1, C4, IllegalArgumentException.class),
                Arguments.of(G1, E2, IllegalArgumentException.class)
        );
    }
}
