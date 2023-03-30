package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest
    @CsvSource({"3,1", "3,2", "3,4", "3,5", "1,3", "2,3", "4,3", "5,3", "4,4", "5,5", "2,2", "1,1", "6,6", "0,0"})
    @DisplayName("퀸의 이동 조건과 일치하는 경우 true가 반환되어야 한다.")
    void canMove_Success1(int x, int y) {
        // given
        Piece piece = Queen.of(Team.BLACK);
        Position sourcePosition = Position.of(3, 3);
        Position targetPosition = Position.of(x, y);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition))
                .isTrue();
    }

    @ParameterizedTest
    @CsvSource({"4,5", "5,4", "2,5", "1,4", "1,2", "2,1", "4,1", "5,2"})
    @DisplayName("퀸의 이동 조건과 다를경우 false가 반환되어야 한다.")
    void canMove_Fail(int x, int y) {
        // given
        Piece piece = Queen.of(Team.BLACK);
        Position sourcePosition = Position.of(3, 3);
        Position targetPosition = Position.of(x, y);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition))
                .isFalse();
    }
}
