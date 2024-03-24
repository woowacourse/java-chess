package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {
    @ParameterizedTest
    @CsvSource({"8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
            "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
    @DisplayName("룩은 도착 위치가 비어있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsEmpty(int file, int rank) {
        Piece piece = new Rook(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank), Map.of())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
            "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
    @DisplayName("룩은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsOtherColor(int file, int rank) {
        Piece piece = new Rook(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank),
                Map.of(new Position(file, rank), new Queen(Color.BLACK)))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
            "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
    @DisplayName("룩은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void canNotMoveWhenTargetIsSameColor(int file, int rank) {
        Piece piece = new Rook(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(file, rank),
                Map.of(new Position(file, rank), new Queen(Color.WHITE)))).isFalse();
    }

    @Test
    @DisplayName("룩은 이동 경로에 말이 있는 경우 이동할 수 없다.")
    void canNotMoveWhenPieceExistIn() {
        Piece piece = new Rook(Color.WHITE);
        assertThat(piece.canMove(new Position(4, 4), new Position(8, 4),
                Map.of(new Position(5, 4), new Queen(Color.WHITE)))).isFalse();
    }
}
