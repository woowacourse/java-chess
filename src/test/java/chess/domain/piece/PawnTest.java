package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("폰은 2칸 움직일 수 있다.")
    void firstMove() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = Position.of("d4");
        Map<Direction, List<Position>> positions = pawn.getMovablePositions(position);
        System.out.println(positions);
        assertThat(positions).containsEntry(
                Direction.NORTH, List.of(Position.of("d5"), Position.of("d6"))
        );
    }

    @Test
    @DisplayName("폰은 1점이다.")
    void getPoint() {
        Piece pawn = new Pawn(Color.BLACK);
        assertThat(pawn.getPoint()).isEqualTo(1);
    }
}
