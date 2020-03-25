import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    void test() {
        Position position = Positions.of(Row.A, Column.ONE);


    }
}
