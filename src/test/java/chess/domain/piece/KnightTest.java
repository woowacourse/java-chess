package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.position.Position;
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

    @DisplayName("나이트의 이동을 확인한다.")
    @Test
    void 나이트_이동() {
        List<Piece> current = Arrays.asList(
                new Knight(Position.of('b', '8'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        Position source = Position.of('b', '8'); // 비숍 위치
        Position target = Position.of('c', '6'); // 옮기고자 하는 위치
        Piece knight = pieces.findByPosition(source);

        knight.move(target, pieces);

        assertThat(knight.getPosition()).isEqualTo(target);
    }

    @DisplayName("나이트 이동 규칙에 어긋나는 경우 - 예외.")
    @Test
    void 나이트_이동_규칙에_어긋나는_경우_예외() {
        List<Piece> current = Arrays.asList(
                new Knight(Position.of('b', '8'), Color.BLACK));
        Pieces pieces = new Pieces(current);
        Position source = Position.of('b', '8'); // 비숍 위치
        Position target = Position.of('b', '1'); // 옮기고자 하는 위치

        Piece knight = pieces.findByPosition(source);

        assertThatThrownBy(() -> knight.move(target, pieces))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
