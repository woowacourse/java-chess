import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

//    @DisplayName("White 진영의 Pawn이 위로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
//    @Test
//    void shouldHasNoPositionWhenGetPath() {
//        Pawn pawn = Pawn.createOfWhite();
//        List<Position> path = pawn.getPath(Position.of("b", "2"), Position.of("b", "3"));
//        assertThat(path).hasSize(0);
//    }

    @DisplayName("White 진영인 경우 - target position이 source position보다 rank가 1높고, target position이 비어있으면 true를 반환한다.")
    @Test
    void shouldReturnIfIsMovableToTargetPositionWhenPawnIsWhiteSide() {
        Pawn whitePawn = Pawn.createOfWhite();
        boolean movable = whitePawn.isMovable(new EmptyPiece(), Position.of("a", "2"), Position.of("a", "3"));
        assertThat(movable).isTrue();
    }

    @DisplayName("Black 진영인 경우 - target position이 source position보다 rank가 1낮고, target position이 비어있으면 true를 반환한다.")
    @Test
    void shouldReturnIfIsMovableToTargetPositionWhenPawnIsBlackSide() {
        Pawn blackPawn = Pawn.createOfBlack();
        boolean movable = blackPawn.isMovable(new EmptyPiece(), Position.of("a", "7"), Position.of("a", "6"));
        assertThat(movable).isTrue();
    }
}
