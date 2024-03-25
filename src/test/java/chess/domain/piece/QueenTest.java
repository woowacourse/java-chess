package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @DisplayName("퀸 이동 가능")
    @ParameterizedTest(name = "(4,4)에서 ({0},{1})로 이동할 수 있다.")
    @CsvSource({"6,6", "2,2", "6,2", "2,6", "4,6", "4,2", "6,4", "2,4"})
    void canMove(int file, int rank) {
        Queen queen = new Queen(Color.WHITE);
        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);
        assertThat(queen.canMove(source, target)).isTrue();
    }

    @DisplayName("퀸 이동 가능")
    @ParameterizedTest(name = "(4,4)에서 ({0},{1})로 이동할 수 없다.")
    @CsvSource({"4,4", "5,6"})
    void cannotMove(int file, int rank) {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(file, rank);
        assertThat(queen.canMove(position1, position2)).isFalse();
    }
}
