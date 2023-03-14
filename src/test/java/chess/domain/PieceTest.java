package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PieceTest {

    @ParameterizedTest
    @DisplayName("Piece가 정상적으로 생성되어야 한다.")
    @EnumSource(Type.class)
    void create_success(Type input) {
        // given
        Piece piece = new Piece(input, Team.BLACK);

        // expect
        assertThat(piece.getType())
                .isEqualTo(input);
    }
}
