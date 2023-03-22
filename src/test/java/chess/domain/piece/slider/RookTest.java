package chess.domain.piece.slider;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.jumper.King;
import chess.domain.piece.slider.Rook;
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

class RookTest {

    @ParameterizedTest
    @MethodSource("searchPathTo")
    @DisplayName("searchPathTo() : Rook이 움직일 수 있다면, 그 이동 경로를 구할 수 있다.")
    void test_searchPathTo(final Position from, final Position to,
                           final Piece destination, final List<Position> possiblePositions) {

        //given
        Piece Rook = new Rook(Color.WHITE);

        //when
        Path path = Rook.searchPathTo(from, to, destination);

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

        final Position from2 = new Position(5, 5);
        final Position to2 = new Position(5, 1);
        final Piece destination2 = null;

        final List<Position> path2 = List.of(new Position(5, 4),
                                             new Position(5, 3),
                                             new Position(5, 2));

        return Stream.of(
                Arguments.of(from1, to1, destination1, path1),
                Arguments.of(from2, to2, destination2, path2)
        );
    }

    @Test
    @DisplayName("searchPathTo() : Rook이 움직일 수 없는 경로일 때, IllegalStateException을 반환한다.")
    void test_searchPathTo_impossible_movement_IllegalStateException() {

        //given
        final Piece piece = new Rook(Color.WHITE);
        final Position from = new Position(5, 5);
        final Position to = new Position(6, 4);
        final Piece destination = new King(Color.BLACK);

        //when & then
        assertThatThrownBy(() -> piece.searchPathTo(from, to, destination))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이(가) 이동할 수 없는 경로입니다.");
    }
}
