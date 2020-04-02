package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.chessPiece.pieceType.Pawn;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnRuleStrategyTest {

    @Test
    void PawnRuleStrategy_MovableAndCatchableDirections_GenerateInstance() {
        PawnRuleStrategy pawnRuleStrategy = new BlackPawnRuleStrategy();

        assertThat(pawnRuleStrategy).isInstanceOf(PawnRuleStrategy.class);
    }

    @ParameterizedTest
    @NullSource
    void validate_NullSourcePosition_ExceptionThrown(Position sourcePosition) {
        PawnRuleStrategy pawnRuleStrategy = new BlackPawnRuleStrategy();
        Position targetPosition = Position.of("b1");

        assertThatThrownBy(() -> pawnRuleStrategy.canMoveToCatch(sourcePosition, targetPosition))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("소스 위치가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @NullSource
    void validate_NullTargetPosition_ExceptionThrown(Position targetPosition) {
        PawnRuleStrategy pawnRuleStrategy = new BlackPawnRuleStrategy();
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