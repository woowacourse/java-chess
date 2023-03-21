package chess.domain.piece;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    @Test
    @DisplayName("searchPathTo() : King이 움직일 수 있다면, 그 이동 경로를 구할 수 있다.")
    void test_searchPathTo() {

        //given
        final Piece piece = new Knight(Color.WHITE);
        final Position from = new Position(2, 1);
        final Position to = new Position(3, 3);

        //when
        final Path path = piece.searchPathTo(from, to, null);

        //then
        assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                        .containsExactly();
    }

    @Test
    @DisplayName("searchPathTo() : Knight가 움직일 수 없는 경로일 때, IllegalStateException을 반환한다.")
    void test_searchPathTo2() {

        //given
        final Piece piece = new Knight(Color.WHITE);
        final Position from = new Position(2, 1);
        final Position to = new Position(4, 5);

        //when & then
        assertThatThrownBy(() -> piece.searchPathTo(from, to, null))
                .isInstanceOf(IllegalStateException.class);
    }
}
