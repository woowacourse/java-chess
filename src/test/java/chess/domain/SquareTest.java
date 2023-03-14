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
        Square square = new Square(new Piece(Type.PAWN, Team.BLACK), new Position(0, 0));

        // expect
        assertAll(
                () -> assertThat(square.getTeam()).isEqualTo(Team.BLACK),
                () -> assertThat(square.getType()).isEqualTo(Type.PAWN),
                () -> assertThat(square.getPosition()).isEqualTo(new Position(0, 0))
        );
    }
}
