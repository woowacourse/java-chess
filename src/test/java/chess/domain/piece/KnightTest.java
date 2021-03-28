package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.position.Direction;
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
        Knight knight = new Knight(Position.of("b8"), Color.BLACK);

        assertThat(knight.isSamePosition(Position.of("b8"))).isTrue();
        assertThat(knight.getName()).isEqualTo("N");
    }

    @DisplayName("나이트의 이동을 확인한다.")
    @Test
    void 나이트_이동() {
        Knight knight = new Knight(Position.of("b8"), Color.BLACK);
        Position target = Position.of("c6");

        knight.move(target);

        assertThat(knight.isSamePosition(target)).isTrue();
    }

    @DisplayName("나이트 이동 규칙에 어긋나는 경우 - 예외.")
    @Test
    void 나이트_이동_규칙에_어긋나는_경우_예외() {
        List<Piece> current = Arrays.asList(
                new Knight(Position.of("b8"), Color.BLACK));
        Pieces pieces = new Pieces(current);
        Position source = Position.of("b8");
        Position target = Position.of("b1");
        Piece knight = pieces.findByPosition(source);
        Piece targetPiece = pieces.findByPosition(target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);

        assertThatThrownBy((() ->
                knight.checkMovable(targetPiece, direction)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
