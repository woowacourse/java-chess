package chess.domain.piece;

import chess.domain.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightTest {
    @DisplayName("Knight 객체 생성 확인")
    @Test
    void 나이트_객체_생성() {
        Knight knight = new Knight(Position.of('b', '8'), Color.BLACK);

        assertThat(knight.getPosition()).isEqualTo(Position.of('b', '8'));
        assertThat(knight.getName()).isEqualTo("N");
    }

    @DisplayName("초기화된 Knight 객체들 생성 확인")
    @Test
    void 나이트_객체들_생성() {
        List<Knight> nights = Knight.initialKnights();

        assertThat(nights.size()).isEqualTo(4);
    }

    @DisplayName("나이트의 이동을 확인한다.")
    @Test
    void 나이트_이동() {
        List<Piece> current = Arrays.asList(
                new Knight(Position.of('b', '8'), Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('b', '8'); // 비숍 위치
        Position target = Position.of('c', '6'); // 옮기고자 하는 위치
        Piece knight = currentPieces.findByPosition(source);

        knight.move(target, currentPieces);

        assertThat(knight.getPosition()).isEqualTo(target);
    }

    @DisplayName("나이트 이동 규칙에 어긋나는 경우 - 예외.")
    @Test
    void 나이트_이동_규칙에_어긋나는_경우_예외() {
        List<Piece> current = Arrays.asList(
                new Knight(Position.of('b', '8'), Color.BLACK));
        CurrentPieces currentPieces = new CurrentPieces(current);
        Position source = Position.of('b', '8'); // 비숍 위치
        Position target = Position.of('b', '1'); // 옮기고자 하는 위치

        Piece knight = currentPieces.findByPosition(source);

        assertThatThrownBy(() -> knight.move(target, currentPieces))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
