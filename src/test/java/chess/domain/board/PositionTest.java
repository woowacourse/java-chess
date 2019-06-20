package chess.domain.board;

import chess.domain.piece.DirectionType;
import chess.exception.NotFoundPositionException;
import javafx.geometry.Pos;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionTest {
    @Test
    public void create() {
        Position position = Position.of("11");
        assertThat(position == Position.of("11")).isTrue();
    }

    @Test
    public void 존재하지않는_좌표를_입력했을_때_예외처리() {
        assertThrows(NotFoundPositionException.class, () -> {
            Position.of("123");
        });
    }

    @Test
    public void 해당_방향으로_한_칸_이동한_위치를_계산하는지_테스트() {
        Position position = Position.of("11");
        assertThat(position.hopNextPosition(DirectionType.UP)).isEqualTo(Position.of("01"));
    }
}
