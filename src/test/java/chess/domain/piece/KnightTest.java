package chess.domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @DisplayName("나이트 이동 가능")
    @ParameterizedTest(name = "(4,4)에서 ({0},{1})로 이동할 수 있다.")
    @CsvSource({"5,6", "3,6", "6,5", "6,3", "5,2", "3,2", "2,3", "2,5"})
    void canMove(int file, int rank) {
        Knight knight = new Knight(Color.WHITE);
        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);
        assertThat(knight.canMove(source, target)).isTrue();
    }

    @DisplayName("나이트 이동 불가")
    @ParameterizedTest(name = "(4,4)에서 ({0},{1})로 이동할 수 없다.")
    @CsvSource({"3,3", "4,4"})
    void cannotMove(int file, int rank) {
        Knight knight = new Knight(Color.WHITE);
        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);
        assertThat(knight.canMove(source, target)).isFalse();
    }
}
