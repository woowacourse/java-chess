package chess.domain.piece;

import chess.domain.move.enums.MoveEnum;
import chess.domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    Piece piece;

    @BeforeEach
    void setUp() {
        piece = new Piece(Team.BLACK) {
            @Override
            public String name() {
                return "T";
            }

            @Override
            public boolean movable(final MoveEnum move) {
                return false;
            }

            @Override
            public boolean movableByCount(final int count) {
                return false;
            }
        };
    }

    @Test
    @DisplayName("체스말을 이름을 갖고 있다")
    void name() {
        // given
        final var expected = "T";

        // when
        final var actual = piece.name();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
