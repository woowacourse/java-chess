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

class BishopTest {

    @ParameterizedTest
    @MethodSource("searchPathTo")
    @DisplayName("searchPathTo() : Bishop이 움직일 수 있다면, 그 이동 경로를 구할 수 있다.")
    void test_searchPathTo(final Position from, final Position to,
                           final Piece destination, final List<Position> possiblePositions) {

        //given
        final Piece bishop = new Bishop(Color.WHITE);

        //when
        final Path path = bishop.searchPathTo(from, to, destination);

        //then
        assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                        .containsExactlyElementsOf(possiblePositions);
    }

    @Test
    @DisplayName("searchPathTo() : 목적지가 Bishop이 움직일 수 없는 경로일 때, IllegalStateException을 반환한다.")
    void test_searchPathTo_IllegalStateException() {

        //given
        final Bishop bishop = new Bishop(Color.WHITE);

        //when
        final Position from = new Position(5, 5);
        final Position to = new Position(5, 1);
        final Piece destination = new King(Color.BLACK);


        assertThatThrownBy(() -> bishop.searchPathTo(from, to, destination))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith("Bishop");
    }

    static Stream<Arguments> searchPathTo() {

        final Position from1 = new Position(1, 1);
        final Position to1 = new Position(5, 5);
        final Piece destination1 = null;

        final List<Position> path1 = List.of(
                new Position(2, 2), new Position(3, 3),
                new Position(4, 4)
        );


        final Position from2 = new Position(5, 1);
        final Position to2 = new Position(8, 4);
        final Piece destination2 = null;

        final List<Position> path2 = List.of(new Position(6, 2), new Position(7, 3));

        return Stream.of(
                Arguments.of(from1, to1, destination1, path1),
                Arguments.of(from2, to2, destination2, path2)
        );
    }
}
