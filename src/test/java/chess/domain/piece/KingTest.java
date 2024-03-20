package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {
    //TODO: 다른 테스트 csv 인자화 테스트 가능한지 확인해보기
    @DisplayName("성공 : 킹은 상하,좌우 방향으로 한칸 이동할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"3,2", "3,4", "4,3", "2,3"})
    void should_CanMoveOneCellToStraight(int destRow, int destCol) {
        Position start = Position.of(3, 3);
        Position dest = Position.of(destRow, destCol);
        King king = new King(Team.BLACK);

        assertThat(king.canMove(start, dest)).isTrue();
    }

    @DisplayName("성공 : 킹은 대각선 방향으로 한칸 이동할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"4,4", "2,4", "2,2", "4,2"})
    void should_CanMoveOneCellToDiagonal(int destRow, int destCol) {
        Position start = Position.of(3, 3);
        Position dest = Position.of(destRow, destCol);
        King king = new King(Team.BLACK);

        assertThat(king.canMove(start, dest)).isTrue();
    }

    @DisplayName("실패 : 킹이 행마법을 벗어난 목적지로 이동할 수 없다")
    @ParameterizedTest
    @CsvSource(value = {"7,4", "5,4", "6,4", "1,1"})
    void should_CanNotMoveToWrongDestination(int destRow, int destCol) {
        Position start = Position.of(3, 3);
        Position dest = Position.of(destRow, destCol);
        King king = new King(Team.BLACK);

        assertThat(king.canMove(start, dest)).isFalse();
    }
}
