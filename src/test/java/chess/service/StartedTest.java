package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartedTest {

    private Started started;

    @BeforeEach
    void setUp() {
        started = new Started(new BoardFactory().createInitialBoard());
    }

    @Test
    @DisplayName("시작된 상태면 start 명령을 실행할 수 없습니다.")
    void test_start() {
        assertThatThrownBy(started::start)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("move메서드는 board.move를 실행시켜준다.")
    void move() {
        final Position from = new Position(2, 2);
        final Position to = new Position(2, 4);

        final State state = started.move(from, to);
        final Map<Position, Piece> pieceMap = started.getBoard();

        assertAll(
                () -> assertThat(state).isInstanceOf(Started.class),
                () -> assertFalse(pieceMap.containsKey(from)),
                () -> assertTrue(pieceMap.containsKey(to))
        );
    }

    @Test
    @DisplayName("end는 종료 상태를 반환해준다.")
    void end() {
        final State end = started.end();
        assertSame(end, End.getInstance());
    }
}
