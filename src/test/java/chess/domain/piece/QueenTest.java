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

class QueenTest {

    @ParameterizedTest
    @MethodSource("searchPathTo")
    @DisplayName("searchPathTo() : Queen이 움직일 수 있다면, 그 이동 경로를 구할 수 있다.")
    void test_searchPathTo(final Position from, final Position to,
                           final Piece destination, final List<Position> possiblePositions) {

        //given
        final Queen queen = new Queen(Color.WHITE);

        //when
        Path path = queen.searchPathTo(from, to, destination);

        //then
        assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                        .containsExactlyElementsOf(possiblePositions);
    }

    static Stream<Arguments> searchPathTo() {

        final Position from1 = new Position(5, 1);
        final Position to1 = new Position(5, 8);
        final Piece destination1 = null;

        final List<Position> path1 = List.of(new Position(5, 2), new Position(5, 3),
                                             new Position(5, 4), new Position(5, 5),
                                             new Position(5, 6), new Position(5, 7));

        final Position from2 = new Position(5, 1);
        final Position to2 = new Position(8, 4);
        final Piece destination2 = null;

        final List<Position> path2 = List.of(new Position(6, 2), new Position(7, 3));

        final Position from3 = new Position(5, 5);
        final Position to3 = new Position(5, 1);
        final Piece destination3 = null;

        final List<Position> path3 = List.of(new Position(5, 4),
                                             new Position(5, 3),
                                             new Position(5, 2));

        return Stream.of(
                Arguments.of(from1, to1, destination1, path1),
                Arguments.of(from2, to2, destination2, path2),
                Arguments.of(from3, to3, destination3, path3)
        );
    }

    @Test
    @DisplayName("searchPathTo() : King이 움직일 수 없는 경로일 때, IllegalStateException을 반환한다.")
    void test_searchPathTo_impossible_movement_IllegalStateException() {

        //given
        final Queen queen = new Queen(Color.WHITE);

        //when
        Position from = new Position(5, 5);
        final Position to = new Position(5, 1);

        //then
        assertThatThrownBy(() -> queen.searchPathTo(from, to, new King(Color.WHITE)))
                .isInstanceOf(IllegalStateException.class);
    }
}
