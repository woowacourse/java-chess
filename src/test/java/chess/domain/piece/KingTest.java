package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"2:2", "1:2", "2:1"}, delimiter = ':')
    @DisplayName("킹은 상하좌우 또는 대각선으로 한 칸 이동할 수 있다.")
    void isMovable(int rank, int file) {
        Piece king = new King(Team.WHITE);
        Position source = new Position(1, 1);
        Position target = new Position(rank, file);
        assertThat(king.isMovable(source, target)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:3", "8:8", "1:5"}, delimiter = ':')
    @DisplayName("킹은 상하좌우 또는 대각선 한 칸을 제외한 곳으로 이동할 수 없다.")
    void isUnmovable(int rank, int file) {
        Piece king = new King(Team.WHITE);
        Position source = new Position(1, 1);
        Position target = new Position(rank, file);
        assertThat(king.isMovable(source, target)).isFalse();
    }

    @Test
    @DisplayName("타켓 위치로 갈 수 있는 모든 경로를 리스트에 담아서 반환한다.")
    void findPath() {
        Piece king = new King(Team.WHITE);
        Position source = new Position(1, 1);
        Position target = new Position(2, 2);
        assertThat(king.findPath(source, target)).containsExactly(new Position(2, 2));
    }
}
