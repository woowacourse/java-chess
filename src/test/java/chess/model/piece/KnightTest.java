package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Movement;
import chess.model.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {
    @ParameterizedTest
    @CsvSource({"1,1,2,3", "1,1,3,2", "2,2,1,4", "4,5,3,3"})
    void 나이트는_옆으로_한칸_앞뒤로_두칸_움직일_수_있다(
            int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Knight.from(Color.BLACK).isValid(movement, Empty.getInstance())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1,1,2", "1,1,1,3", "2,2,2,3", "4,5,4,6"})
    void 나이트는_옆으로_한칸_앞뒤로_두칸_꼴이_아니게_움직일_수_없다(
            int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Knight.from(Color.BLACK).isValid(movement, Empty.getInstance())).isFalse();
    }
}
