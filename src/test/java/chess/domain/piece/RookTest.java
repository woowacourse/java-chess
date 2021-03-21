package chess.domain.piece;

import chess.domain.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {
    @DisplayName("Rook 객체 생성 확인")
    @Test
    void 룩_객체_생성() {
        Rook rook = new Rook(Position.of('a', '8'), Color.BLACK);

        assertThat(rook.getPosition()).isEqualTo(Position.of('a', '8'));
        assertThat(rook.getName()).isEqualTo("R");
    }

    @DisplayName("초기화된 룩 객체들 생성 확인")
    @Test
    void 룩_객체들_생성() {
        List<Rook> rooks = Rook.initialRooks();

        assertThat(rooks.size()).isEqualTo(4);
    }

    @DisplayName("룩의 이동을 확인한다.")
    @Test
    void 룩_이동() {
        List<Piece> current = Arrays.asList(
                new Rook(Position.of('a', '8'), Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('a', '8'); // 비숍 위치
        Position target = Position.of('a', '1'); // 옮기고자 하는 위치
        Piece rook = currentPieces.findByPosition(source);

        rook.move(target, currentPieces);

        assertThat(rook.getPosition()).isEqualTo(target);
    }

    @DisplayName("룩의 이동 규칙에 어긋나는 경우 - 예외")
    @Test
    void 룩_이동_규칙에_어긋나는_경우_예외() {
        List<Piece> current = Arrays.asList(
                new Rook(Position.of('a', '8'), Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('a', '8'); // 비숍 위치
        Position target = Position.of('b', '1'); // 옮기고자 하는 위치

        Piece rook = currentPieces.findByPosition(source);

        assertThatThrownBy(() -> rook.move(target, currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
