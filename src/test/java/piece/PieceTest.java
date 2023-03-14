package piece;

import coordinate.Coordinate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @Test
    void 팀과_좌표를_받아_생성한다() {
        Team team = Team.WHITE;
        Piece piece = new Piece(team,2,'a');
        Assertions.assertThat(piece).isNotNull();
    }
}
