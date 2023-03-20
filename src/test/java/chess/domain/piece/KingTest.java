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
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    @Test
    @DisplayName("searchPathTo() : King이 두 칸 이상 움직이려고 할 때, IllegalStateException을 반환한다.")
    void test_searchPathTo_move_twice_IllegalStateException() {

        //given
        final Piece piece = new King(Color.WHITE);

        //when
        final Position from = new Position(6, 1);
        final Position to = new Position(8, 1);
        final Piece destination = new King(Color.BLACK);

        //then
        assertThatThrownBy(() -> piece.searchPathTo(from, to, destination))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("은 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("searchPathTo() : King이 움직일 수 없는 경로일 때, IllegalStateException을 반환한다.")
    void test_searchPathTo_impossible_movement_IllegalStateException() {

        //given
        final Piece piece = new King(Color.WHITE);

        //when
        final Position from = new Position(6, 1);
        final Position to = new Position(7, 3);
        final Piece destination = new King(Color.BLACK);

        //then
        assertThatThrownBy(() -> piece.searchPathTo(from, to, destination))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이(가) 이동할 수 없는 경로입니다.");
    }

    @ParameterizedTest
    @MethodSource("searchPathTo")
    @DisplayName("searchPathTo() : King이 움직일 수 있다면, 그 이동 경로를 구할 수 있다.")
    void test_searchPathTo(final Position from, final Position to,
                           final Piece destination, final List<Position> possiblePositions) {

        //given
        final Piece piece = new King(Color.WHITE);

        //when
        final Path path = piece.searchPathTo(from, to, destination);

        //then
        assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                        .containsExactlyElementsOf(possiblePositions);
    }

    static Stream<Arguments> searchPathTo() {

        final Position from1 = new Position(5, 1);
        final Position to1 = new Position(5, 2);
        final Piece destination1 = null;

        final List<Position> path1 = List.of();

        final Position from2 = new Position(4, 1);
        final Position to2 = new Position(5, 2);
        final Piece destination2 = new Queen(Color.BLACK);

        final List<Position> path2 = List.of();

        return Stream.of(
                Arguments.of(from1, to1, destination1, path1),
                Arguments.of(from2, to2, destination2, path2)
        );
    }
}
