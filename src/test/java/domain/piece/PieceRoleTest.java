package domain.piece;

import domain.game.PositionFixture;
import domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceRoleTest {

    @DisplayName("킹이 (1,1)에서 (2,2)로 이동하지 못한다.")
    @Test
    void canKingMove() {
        Position sourcePosition = PositionFixture.generateSourcePosition();
        Position targetPosition = PositionFixture.generateTargetPosition();

        PieceRole king = new King();

        Assertions.assertThat(king.canMove(sourcePosition, targetPosition)).isFalse();
    }

}
