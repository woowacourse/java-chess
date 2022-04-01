package chess.domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("흑색 폰을 생성한다.")
    void constructPawn() {
        final var piece = new BlackPawn();

        assertThat(piece).isInstanceOf(BlackPawn.class);
    }

    @Test
    @DisplayName("폰은 2칸 움직일 수 있다.")
    void firstMove() {
        final Piece pawn = new WhitePawn();
        final Position position = Position.of("d4");
        final Map<Direction, List<Position>> positions = pawn.getMovablePositions(position);

        assertThat(positions).containsEntry(
                Direction.NORTH, List.of(Position.of("d5"), Position.of("d6"))
        );
    }

    @Test
    @DisplayName("폰은 1점이다.")
    void getPoint() {
        final Piece pawn = new BlackPawn();
        final double point = pawn.getPoint();

        assertThat(point).isEqualTo(1);
    }

    @Test
    @DisplayName("검은색 폰의 방향은 SOUTH이다.")
    void getBlackPawnDirection() {
        final Piece pawn = new BlackPawn();
        final Direction actual = pawn.getPawnDirection();

        assertThat(actual).isEqualTo(Direction.SOUTH);
    }

    @Test
    @DisplayName("흰색 폰의 방향은 SOUTH이다.")
    void getWhitePawnDirection() {
        final Piece pawn = new WhitePawn();
        final Direction actual = pawn.getPawnDirection();

        assertThat(actual).isEqualTo(Direction.NORTH);
    }
}
