package chess.piece;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    @DisplayName("룩의 진행 방향이 맞는지 확인하는 테스트")
    void correctMove() {
        Rook rook = new Rook(new Position(File.A, Rank.ONE), Team.WHITE);

        assertDoesNotThrow(
                () -> rook.move(new Position(File.F, Rank.ONE))
        );
    }

    @Test
    @DisplayName("룩의 진행 방향이 다르면 예외가 발생하는 테스트")
    void unCorrectMove() {
        Rook rook = new Rook(new Position(File.A, Rank.ONE), Team.WHITE);

        assertThatThrownBy(
                () -> rook.move(new Position(File.F, Rank.TWO))
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

}
