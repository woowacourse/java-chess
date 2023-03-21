package chess.piece;

import static chess.domain.piece.Color.WHITE;
import static chess.domain.position.InitialPositionFixtures.WHITE_KNIGHT_LEFT_POSITION;
import static chess.fixture.PositionFixture.C3;
import static chess.fixture.PositionFixture.D5;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.path.Path;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo() {
        Piece piece = Knight.from(WHITE);

        Path path = piece.searchPathTo(WHITE_KNIGHT_LEFT_POSITION, C3, null);

        assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class)).containsExactly();
    }

    @DisplayName("비정상 경로를 받으면 예외 처리한다.")
    @Test
    void test_searchPathTo2() {
        Piece piece = Knight.from(WHITE);

        assertThatThrownBy(() -> piece.searchPathTo(WHITE_KNIGHT_LEFT_POSITION, D5, null)).isInstanceOf(
                IllegalStateException.class);
    }
}
