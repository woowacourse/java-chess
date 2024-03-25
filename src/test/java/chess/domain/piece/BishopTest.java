package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @DisplayName("비숍 이동 가능")
    @ParameterizedTest(name = "(4,4)에서 ({0},{1})로 이동할 수 있다.")
    @CsvSource({"6,6", "2,2", "6,2", "2,6"})
    void canMove(int file, int rank) {
        Bishop bishop = new Bishop(Color.WHITE);
        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);
        assertThat(bishop.canMove(source, target)).isTrue();
    }

    @DisplayName("비숍 이동 불가")
    @ParameterizedTest(name = "(4,4)에서 ({0},{1})로 이동할 수 없다.")
    @CsvSource({"5,4", "4,4"})
    void cannotMove(int file, int rank) {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(file, rank);
        assertThat(bishop.canMove(position1, position2)).isFalse();
    }
}
