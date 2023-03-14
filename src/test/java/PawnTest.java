import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("위로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldHasNoPositionWhenGetPath() {
        Pawn pawn = new Pawn();

//        List<Position> path = pawn.getPath(Position.of("b", "2"), Position.of("b", "3"));

//        assertThat(path).hasSize(0);
    }
}
