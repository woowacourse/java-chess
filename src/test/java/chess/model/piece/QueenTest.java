package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Movement;
import chess.model.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {
    @ParameterizedTest
    @CsvSource({"1,1,2,2", "1,1,3,3", "2,2,3,3", "4,5,5,6"})
    void 퀸은_직선과_대각선으로_원하는_만큼_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Queen.from(Color.BLACK).isValid(movement, Empty.getInstance())).isTrue();
    }
}
