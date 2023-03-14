package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceTest {

    @Test
    @DisplayName("위치와 팀을 입력받아 생성한다.")
    void test_Constructor() {
        //given
        final Team team = Team.BLACK;
        final Position position = new Position(1,1);

        //when & then
        Assertions.assertDoesNotThrow(() -> new ChessPiece(team, position) {
        });
    }
}
