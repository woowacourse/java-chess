package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @DisplayName("킹 이동 가능")
    @ParameterizedTest(name = "(4,4)에서 ({0},{1})로 이동할 수 있다.")
    @CsvSource({"4,5", "4,3", "5,4", "3,4", "5,5", "3,5", "5,3", "3,3"})
    void canMove(int file, int rank) {
        King king = new King(Color.WHITE);
        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);
        assertThat(king.canMove(source, target)).isTrue();
    }

    @DisplayName("킹 이동 불가")
    @ParameterizedTest(name = "(4,4)에서 ({0},{1})로 이동할 수 없다.")
    @CsvSource({"4,4", "2,4"})
    void cannotMove(int file, int rank) {
        King king = new King(Color.WHITE);
        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);
        assertThat(king.canMove(source, target)).isFalse();
    }
}
