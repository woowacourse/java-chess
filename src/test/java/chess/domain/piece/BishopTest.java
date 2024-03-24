package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {
    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
            "7,1", "6,2", "3,5", "2,6", "1,7"})
    @DisplayName("비숍은 도착 위치가 비어있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsEmpty(int file, int rank) {
        Piece piece = new Bishop(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank), Map.of())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
            "7,1", "6,2", "3,5", "2,6", "1,7"})
    @DisplayName("비숍은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsOtherColor(int file, int rank) {
        Piece piece = new Bishop(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank),
                Map.of(new Position(file, rank), new Bishop(Color.BLACK)))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
            "7,1", "6,2", "3,5", "2,6", "1,7"})
    @DisplayName("비숍은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void canNotMoveWhenTargetIsSameColor(int file, int rank) {
        Piece piece = new Bishop(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank),
                Map.of(new Position(file, rank), new Bishop(Color.WHITE)))).isFalse();
    }

    @Test
    @DisplayName("비숍은 이동 경로에 말이 있는 경우 이동할 수 없다.")
    void canNotMoveWhenPieceExistIn() {
        Piece piece = new Bishop(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(8, 8),
                Map.of(new Position(6, 6), new Bishop(Color.WHITE)))).isFalse();
    }
}
