package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PawnTest {
    private CurrentPieces currentPieces;
    @BeforeEach
    void setUp() {
        currentPieces = CurrentPieces.generate();
    }

    @DisplayName("Pawn 객체 생성 확인")
    @Test
    void 폰_객체_생성() {
        Pawn pawn = new Pawn(Position.of('a', '7'), "P");

        assertThat(pawn.getPosition()).isEqualTo(Position.of('a', '7'));
        assertThat(pawn.getName()).isEqualTo("P");
    }

    @DisplayName("초기화된 Pawn 객체들 생성 확인")
    @Test
    void 폰_객체들_생성() {
        List<Pawn> pawns = Pawn.generate();

        assertThat(pawns.size()).isEqualTo(16);
    }

    @DisplayName("Pawn 규칙에 따라 처음 Pawn을 움직이는 경우 - 2칸 이동")
    @Test
    void pawn_처음으로_이동_2칸() {
        Pawn pawn = new Pawn(Position.of('a', '7'), "P");

        pawn.move(Position.of('a', '5'), currentPieces);

        assertThat(pawn.getPosition()).isEqualTo(Position.of('a', '5'));
    }

    @DisplayName("Pawn 규칙에 따라 처음 Pawn을 움직이는 경우 예외 - 3칸 이동 ")
    @Test
    void pawn_처음으로_이동_3칸_예외() {
        Pawn pawn = new Pawn(Position.of('a', '7'), "P");

        assertThatThrownBy(() -> pawn.move(Position.of('a', '4'), currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Pawn 규칙에 따라 이미 움직인 Pawn을 움직이는 경우 예외 - 2칸 이동 ")
    @Test
    void pawn_이미_이동_2칸_예외() {
        Pawn pawn = new Pawn(Position.of('a', '6'), "P");

        assertThatThrownBy(() -> pawn.move(Position.of('a', '4'), currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
