package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionsTest {

    @Test
    @DisplayName("'a2 a4'를 'new Position(1, 2), new Position(1, 4)로 만든다.")
    void createPositionsByRawPositions() {
        List<String> rawPositions = List.of("a2", "a4");
        Positions positions = new Positions(rawPositions);

        assertAll(
                () -> assertThat(positions.from()).isEqualTo(new Position(1, 2)),
                () -> assertThat(positions.to()).isEqualTo(new Position(1, 4))
        );

    }
}
