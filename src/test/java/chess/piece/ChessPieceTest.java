package chess.piece;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceTest {

    @Test
    @DisplayName("체스말은 팀을 입력받아 생성한다.")
    void test_Constructor() {
        //given
        final Team team = Team.BLACK;

        //when & then
        Assertions.assertDoesNotThrow(() -> new ChessPiece(team) {});
    }
}
