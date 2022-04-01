package chess.utils;

import chess.domain.board.Chessboard;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PossibleMoveLinePositionCheckerTest {

    @Test
    @DisplayName("이동가능한지 확인")
    void checkMove() {
        assertThat(PossibleMoveLinePositionChecker.isPossibleMovePosition(List.of(new Position(6, 0),
                        new Position(5, 0)), List.of(List.of(-1, 0)),
                Chessboard.create().getBoard()))
                .isTrue();
    }
}
