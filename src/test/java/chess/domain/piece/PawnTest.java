package chess.domain.piece;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    private static final Position INITIAL_POSITION = new Position(1, 2);

    @ParameterizedTest
    @MethodSource("searchPathTo")
    @DisplayName("searchPathTo() : Pawn이 움직일 수 있다면, 그 이동 경로를 구할 수 있다.")
    void test_searchPathTo() {

        Pawn pawn = new Pawn(Color.WHITE);

        Path path = pawn.searchPathTo(INITIAL_POSITION, new Position(1, 3), null);

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }

    static Stream<Arguments> searchPathTo() {

        final Position to1 = new Position(1, 3);
        final Optional<Piece> destination1 = Optional.empty();

        final List<Position> path1 = List.of();

        final Position to2 = new Position(1, 4);
        final Optional<Piece> destination2 = Optional.empty();

        final List<Position> path2 = List.of(new Position(1, 3));

        final Position to3 = new Position(2, 3);
        final Optional<Piece> destination3 = Optional.of(new Pawn(Color.BLACK));

        final List<Position> path3 = List.of();

        return Stream.of(
                Arguments.of(to1, destination1, path1),
                Arguments.of(to2, destination2, path2),
                Arguments.of(to3, destination3, path3)
        );
    }

    @Test
    @DisplayName("searchPathTo() : Pawn 이 움직일 수 없는 경로일 때, IllegalStateException을 반환한다.")
    void test_searchPathTo_impossible_movement_IllegalStateException() {
        //given
        final Position to = new Position(2, 3);
        final Pawn pawn = new Pawn(Color.WHITE);

        //when & then
        assertThatThrownBy(() -> pawn.searchPathTo(INITIAL_POSITION, to, new Pawn(Color.WHITE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이(가) 이동할 수 없는 경로입니다.");
    }
}
