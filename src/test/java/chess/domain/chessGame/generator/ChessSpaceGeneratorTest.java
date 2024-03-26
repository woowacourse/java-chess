package chess.domain.chessGame.generator;

import chess.domain.chessGame.Space;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessSpaceGeneratorTest {

    @Test
    @DisplayName("8x8 체스 공간을 생성할 수 있다")
    void should_generate_64_spaces() {
        SpaceGenerator spaceGenerator = new ChessSpaceGenerator();

        assertThat(spaceGenerator.generateSpaces()).hasSize(64);
    }

    @Test
    @DisplayName("생성된 체스 공간은 모두 다른 위치다")
    void should_be_unique_spaces() {
        SpaceGenerator spaceGenerator = new ChessSpaceGenerator();
        List<Space> spaces = spaceGenerator.generateSpaces();
        List<Position> positions = makeAllChessPosition();

        long distinctCount = positions.stream()
                .mapToLong(position -> spaces.stream()
                        .filter(space -> space.isSamePosition(position))
                        .count())
                .sum();

        assertThat(distinctCount).isEqualTo(64);
    }

    private List<Position> makeAllChessPosition() {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> new Position(file, rank)))
                .toList();

    }
}
