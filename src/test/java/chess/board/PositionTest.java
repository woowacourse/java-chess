package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("포지션 좌표를 Enum 으로 받아 생성한다.")
    void constructor() {
        assertThat(new Position(Column.A, Row.ONE)).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("64개의 포지션을 생성한다.")
    void init() {
        assertThat(Position.init()).hasSize(64);
    }

    @Test
    @DisplayName("동일한 row에서 크기 비교에 성공한다.")
    void compareToSameRow() {
        Position position = new Position(Column.A, Row.EIGHT);
        assertThat(position).isGreaterThan(new Position(Column.C, Row.EIGHT));
    }

    @Test
    @DisplayName("크기 비교에 성공한다.")
    void compareTo() {
        Position position = new Position(Column.A, Row.EIGHT);
        assertThat(position).isLessThan(new Position(Column.A, Row.SEVEN));
    }
}
