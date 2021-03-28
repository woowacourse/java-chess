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

public class RookTest {
    @DisplayName("Rook 객체 생성 확인")
    @Test
    void 룩_객체_생성() {
        Rook rook = new Rook(Position.of("a8"), Color.BLACK);

        assertThat(rook.getPosition()).isEqualTo(Position.of("a8"));
        assertThat(rook.getName()).isEqualTo("R");
    }

    @DisplayName("룩의 이동을 확인한다.")
    @Test
    void 룩_이동() {
        Position target = Position.of("a1");
        Piece rook = new Rook(Position.of("a8"), Color.BLACK);

        rook.move(target);

        assertThat(rook.getPosition()).isEqualTo(target);
    }

    @DisplayName("룩의 이동 규칙에 어긋나는 경우 - 예외")
    @Test
    void 룩_이동_규칙에_어긋나는_경우_예외() {
        List<Piece> current = Arrays.asList(
                new Rook(Position.of("a8"), Color.BLACK));
        Pieces pieces = new Pieces(current);
        Position source = Position.of("a8");
        Position target = Position.of("b7");
        Piece rook = pieces.findByPosition(source);
        Piece targetPiece = pieces.findByPosition(target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);

        assertThatThrownBy((() ->
                rook.checkMovable(targetPiece, direction)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
