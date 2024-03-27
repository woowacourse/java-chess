package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Movement;
import chess.model.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {
    @ParameterizedTest
    @CsvSource({"1,1,2,2", "2,2,3,1", "2,2,1,3", "4,5,5,4"})
    void 비숍은_대각선으로_원하는_만큼_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Bishop.from(Color.BLACK).isValid(movement, Empty.getInstance())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1,2,3", "1,1,3,2", "2,2,1,2", "4,5,5,5"})
    void 비숍은_대각선이_아닌_방향으로_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Bishop.from(Color.BLACK).isValid(movement, Empty.getInstance())).isFalse();
    }
}
