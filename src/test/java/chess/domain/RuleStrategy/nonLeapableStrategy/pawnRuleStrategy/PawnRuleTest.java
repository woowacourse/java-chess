package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.chessPiece.pieceType.Pawn;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnRuleTest {

    @Test
    void PawnRuleStrategy_MovableAndCatchableDirections_GenerateInstance() {
        PawnRule pawnRuleStrategy = new BlackPawnRule();

        assertThat(pawnRuleStrategy).isInstanceOf(PawnRule.class);
    }

    @ParameterizedTest
    @NullSource
    void validate_NullSourcePosition_ExceptionThrown(Position sourcePosition) {
        PawnRule pawnRuleStrategy = new BlackPawnRule();
        Position targetPosition = Position.of("b1");

        assertThatThrownBy(() -> pawnRuleStrategy.canMoveToCatch(sourcePosition, targetPosition))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("소스 위치가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @NullSource
    void validate_NullTargetPosition_ExceptionThrown(Position targetPosition) {
        PawnRule pawnRuleStrategy = new BlackPawnRule();
        Position sourcePosition = Position.of("b1");

        assertThatThrownBy(() -> pawnRuleStrategy.canMoveToCatch(sourcePosition, targetPosition))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("타겟 위치가 존재하지 않습니다.");
    }

    @Test
    void canMove_InitialState_returnTrue() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        pawn.canMove(Position.of("b7"), Position.of("b5"));

        assertThat(pawn.canMove(Position.of("b5"), Position.of("b4")));
    }

    @Test
    void canMove_MovedState_ExceptionThrown() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        pawn.canMove(Position.of("b7"), Position.of("b5"));

        assertThat(pawn.canMove(Position.of("b5"), Position.of("b3"))).isFalse();
    }
}