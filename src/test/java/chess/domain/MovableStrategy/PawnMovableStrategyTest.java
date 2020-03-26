package chess.domain.MovableStrategy;

import chess.domain.chessPiece.PieceColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnMovableStrategyTest {
    @Test
    void PawnMovableStrategy_PieceColor_GenerateInstance() {
        assertThat(new PawnMovableStrategy(PieceColor.BLACK)).isInstanceOf(PawnMovableStrategy.class);
    }

    @ParameterizedTest
    @NullSource
    void PawnMovableStrategy_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
        assertThatThrownBy(() -> new PawnMovableStrategy(pieceColor))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("체스 피스 색깔이 존재하지 않습니다.");
    }

    @Test
    void canMove_CanMovableSourceAndTarget_ReturnTrue() {
        MovableStrategy movableStrategy = new PawnMovableStrategy(PieceColor.BLACK);
        Position source = Position.of("b3");
        Position target = Position.of("b2");

        assertThat(movableStrategy.canMove(source, target)).isTrue();
    }
}