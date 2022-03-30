package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;

class EmptyTest {

    @Test
    @DisplayName("빈공간을 만든다.")
    void createEmpty() {
        Piece piece = Empty.getInstance();

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("빈공간은 움직일 수 없다.")
    void throwsExceptionWithTryToMove() {
        Piece piece = Empty.getInstance();
        Route route = Route.of(List.of("a3", "a4"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        assertThatExceptionOfType(UnsupportedOperationException.class)
            .isThrownBy(() -> piece.move(route, emptyPoints));
    }
}
