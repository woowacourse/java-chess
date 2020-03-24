package chess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PositionTest {
    @Test
    void Position_FileAndRank_GenerateInstance() {
        assertThat(new Position(ChessFile.from('a'), ChessRank.from(2))).isInstanceOf(Position.class);
    }

    @Test
    void toString_Position_JoinFileAndRank() {
        Position position = new Position(ChessFile.from('a'), ChessRank.from(2));

        String expected = "a2";
        assertThat(position.toString()).isEqualTo(expected);
    }
}
