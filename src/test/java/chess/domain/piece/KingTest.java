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

public class KingTest {
    @DisplayName("King 객체 생성 확인")
    @Test
    void 킹_객체_생성() {
        King king = new King(Position.of("e8"), Color.BLACK);

        assertThat(king.getPosition()).isEqualTo(Position.of("e8"));
        assertThat(king.getName()).isEqualTo("K");
    }

    @DisplayName("킹의 이동을 확인한다.")
    @Test
    void 킹_이동_십자() {
        Position target = Position.of("e7");
        King king = new King(Position.of("e8"), Color.BLACK);

        king.move(target);

        assertThat(king.getPosition()).isEqualTo(target);
    }

    @DisplayName("킹의 이동 규칙에 어긋나는 경우 예외")
    @Test
    void 킹_이동_규칙에_어긋나는_경우_이동_규칙_예외() {
        List<Piece> current = Arrays.asList(
                new King(Position.of("e8"), Color.BLACK));
        Pieces pieces = new Pieces(current);
        Position source = Position.of("f8");
        Position target = Position.of("g6");
        Piece king = pieces.findByPosition(source);
        Piece targetPiece = pieces.findByPosition(target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);

        assertThatThrownBy((() ->
                king.checkMovable(targetPiece, direction)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
