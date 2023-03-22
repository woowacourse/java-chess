package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest
    @CsvSource({"3,4", "4,4", "4,3", "4,2", "3,2", "2,2", "2,3", "2,4"})
    @DisplayName("킹의 이동 조건과 일치하는 경우 true가 반환되어야 한다.")
    void canMove_Success(int x, int y) {
        // given
        Piece piece = King.of(Team.BLACK);
        Position sourcePosition = Position.of(3, 3);
        Position targetPosition = Position.of(x, y);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition))
                .isTrue();
    }

    @ParameterizedTest
    @CsvSource({"4,5", "5,4", "2,5", "1,4", "1,2", "2,1", "4,1", "5,2"})
    @DisplayName("킹의 이동 조건과 다를경우 false가 반환되어야 한다.")
    void canMove_Fail(int x, int y) {
        // given
        Piece piece = King.of(Team.BLACK);
        Position sourcePosition = Position.of(3, 3);
        Position targetPosition = Position.of(x, y);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition))
                .isFalse();
    }

    @Test
    @DisplayName("킹이 움직일 수 있는 위치가 정확히 반환되어야 한다.")
    void getKingMovablePosition_Success() {
        // given
        King king = King.of(Team.WHITE);

        // when
        List<Position> kingMovablePositions = king.getKingMovablePositions(Position.of(0, 0));

        // then
        assertThat(kingMovablePositions)
                .containsExactly(
                        Position.of(0, 1),
                        Position.of(1, 1),
                        Position.of(1, 0)
                );
    }
}
