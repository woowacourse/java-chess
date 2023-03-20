package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Role;
import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PieceFactoryTest {

    @ParameterizedTest
    @EnumSource(Role.class)
    @DisplayName("Piece가 정상적으로 생성되어야 한다.")
    void create_Success(Role role) {
        // given
        Piece piece = PieceFactory.of(role, Team.BLACK);

        // expect
        assertThat(piece.getRole())
                .isEqualTo(role);
    }
}
