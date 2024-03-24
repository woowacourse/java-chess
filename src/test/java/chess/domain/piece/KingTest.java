package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {
    @ParameterizedTest
    @CsvSource({"3,3", "5,5",
            "3,5",
            "5,4", "3,4",
            "4,3", "4,5",})
    @DisplayName("킹은 도착 위치가 비어있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsEmpty(int file, int rank) {
        Piece piece = new King(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank), Map.of())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"3,3", "5,5",
            "3,5", "5,3",
            "5,4", "3,4",
            "4,3", "4,5",})
    @DisplayName("킹은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsOtherColor(int file, int rank) {
        Piece piece = new King(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank),
                Map.of(new Position(file, rank), new Queen(Color.BLACK)))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"3,3", "5,5",
            "3,5", "5,3",
            "5,4", "3,4",
            "4,3", "4,5",})
    @DisplayName("킹은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void canNotMoveWhenTargetIsSameColor(int rank, int file) {
        Piece piece = new King(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank),
                Map.of(new Position(file, rank), new Queen(Color.WHITE)))).isFalse();
    }
}
