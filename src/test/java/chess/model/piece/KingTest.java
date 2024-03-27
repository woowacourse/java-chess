package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Movement;
import chess.model.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {
    @ParameterizedTest
    @CsvSource({"2,2,1,2", "2,2,2,1", "2,2,3,3", "2,2,3,1"})
    void 킹은_어느_방향이로든_한_칸_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(King.from(Color.BLACK).isValid(movement, Empty.getInstance())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"2,2,1,4", "2,2,2,4", "2,2,3,4", "2,2,4,1"})
    void 킹은_한_칸_이상_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(King.from(Color.BLACK).isValid(movement, Empty.getInstance())).isFalse();
    }
}
