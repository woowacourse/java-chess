package chess.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"1:1", "0:1", "1:0"}, delimiter = ':')
    @DisplayName("킹은 상하좌우 또는 대각선으로 한 칸 이동할 수 있다.")
    void isMovable(int rank, int file) {
        Piece king = new King();
        Position source = new Position(0, 0);
        Position target = new Position(rank, file);
        assertThat(king.isMovable(source, target)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2", "7:7", "0:4"}, delimiter = ':')
    @DisplayName("킹은 상하좌우 또는 대각선 한 칸을 제외한 곳으로 이동할 수 없다.")
    void isUnmovable(int rank, int file) {
        Piece king = new King();
        Position source = new Position(0, 0);
        Position target = new Position(rank, file);
        assertThat(king.isMovable(source, target)).isFalse();
    }

    @Test
    @DisplayName("타켓 위치로 갈 수 있는 모든 경로를 리스트에 담아서 반환한다.")
    void findPath() {
        Piece king = new King();
        Position source = new Position(0, 0);
        Position target = new Position(1, 1);
        assertThat(king.findPath(source, target)).containsExactly(new Position(1, 1));
    }
}
