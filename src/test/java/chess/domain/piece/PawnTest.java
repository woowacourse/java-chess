package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
        Pawn pawn = new Pawn(Position.of('a', '7'), "P", Color.BLACK);

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
        Pawn pawn = new Pawn(Position.of('a', '7'), "P", Color.BLACK);

        pawn.move(Position.of('a', '5'), currentPieces);

        assertThat(pawn.getPosition()).isEqualTo(Position.of('a', '5'));
    }

    @DisplayName("Pawn 규칙에 따라 처음 Pawn을 움직이는 경우 예외 - 3칸 이동 ")
    @Test
    void pawn_처음으로_이동_3칸_예외() {
        Pawn pawn = new Pawn(Position.of('a', '7'), "P", Color.BLACK);

        assertThatThrownBy(() -> pawn.move(Position.of('a', '4'), currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Pawn 규칙에 따라 이미 움직인 Pawn 을 움직이는 경우 예외 - 2칸 이동 ")
    @Test
    void pawn_이미_이동_2칸_예외() {
        Pawn pawn = new Pawn(Position.of('a', '6'), "P", Color.BLACK);

        assertThatThrownBy(() -> pawn.move(Position.of('a', '4'), currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("뒤로 이동할 경우 예외")
    @Test
    void pawn_뒤로_이동() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of('a', '7'), "P", Color.BLACK),
                new Pawn(Position.of('a', '2'), "P", Color.WHITE));
        CurrentPieces currentPieces = new CurrentPieces(current);

        Piece pawn1 = currentPieces.findByPosition(Position.of('a', '7'));
        Piece pawn2 = currentPieces.findByPosition(Position.of('a', '2'));

        assertThatThrownBy(() -> pawn1.move(Position.of('a', '8'), currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> pawn2.move(Position.of('a', '1'), currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이동하는데_앞에_장애물이_있는_경우() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of('a', '7'), "P", Color.BLACK),
                new Pawn(Position.of('a', '6'), "P", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);

        Position source = Position.of('a', '7'); // 비숍 위치
        Position target = Position.of('a', '5'); // 옮기고자 하는 위치
        Piece pawn = currentPieces.findByPosition(source);

        assertThatThrownBy(() -> pawn.move(target, currentPieces))
        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 검은말이_상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of('a', '7'), "P", Color.BLACK),
                new Pawn(Position.of('b', '6'), "p", Color.WHITE));
        CurrentPieces currentPieces = new CurrentPieces(current);

        Position source = Position.of('a', '7'); // 비숍 위치
        Position target = Position.of('b', '6'); // 옮기고자 하는 위치
        Piece pawn = currentPieces.findByPosition(source);

        pawn.move(target, currentPieces);

        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(1);
    }

    @Test
    void 하얀말이_상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of('a', '2'), "p", Color.WHITE),
                new Pawn(Position.of('b', '3'), "P", Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);

        Position source = Position.of('a', '2'); // 비숍 위치
        Position target = Position.of('b', '3'); // 옮기고자 하는 위치
        Piece pawn = currentPieces.findByPosition(source);

        pawn.move(target, currentPieces);

        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(1);
    }
}
