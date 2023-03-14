package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    @DisplayName("Square가 정상적으로 생성되어야 한다.")
    void create_Success() {
        // given
        Square square = new Square(new Piece(Role.PAWN, Team.BLACK), Position.of(0, 0));

        // expect
        assertAll(
                () -> assertThat(square.getTeam()).isEqualTo(Team.BLACK),
                () -> assertThat(square.getRole()).isEqualTo(Role.PAWN),
                () -> assertThat(square.getPosition()).isEqualTo(Position.of(0, 0))
        );
    }
}
