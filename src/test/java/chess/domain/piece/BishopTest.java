package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Position;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @CsvSource(value = {"3:3", "5:5", "3:5"}, delimiter = ':')
    @DisplayName("비숍은 대각선으로 움직일 수 있다.")
    void isMovable(int rank, int file) {
        Piece bishop = new Bishop();
        Position source = new Position(4, 4);
        Position target = new Position(rank, file);
        assertThat(bishop.isMovable(source, target)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:3", "4:5", "3:6"}, delimiter = ':')
    @DisplayName("비숍은 대각선을 제외한 곳으로 움직일 수 없다.")
    void isUnmovable(int rank, int file) {
        Piece bishop = new Bishop();
        Position source = new Position(4, 4);
        Position target = new Position(rank, file);
        assertThat(bishop.isMovable(source, target)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("findPathProvider")
    @DisplayName("타켓 위치로 갈 수 있는 모든 경로를 리스트에 담아서 반환한다.")
    void findPath(Position source, Position target, List<Position> expectedPath) {
        Piece bishop = new Bishop();
        assertThat(bishop.findPath(source, target)).containsAll(expectedPath);
    }

    static Stream<Arguments> findPathProvider() {
        return Stream.of(
                Arguments.of(
                        new Position(4, 4),
                        new Position(6, 6),
                        List.of(new Position(5, 5), new Position(6, 6))),
                Arguments.of(
                        new Position(4, 4),
                        new Position(2, 6),
                        List.of(new Position(3, 5), new Position(2, 6)))
        );
    }
}
