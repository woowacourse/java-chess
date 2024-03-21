package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("Rank가 같으면 룩 이동 가능")
    void canMove_SameRank() {
        Rook rook = new Rook(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(8, 1);
        assertThat(rook.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("File이 같으면 룩 이동 가능")
    void canMove_SameFile() {
        Rook rook = new Rook(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 8);
        assertThat(rook.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("Rank와 File이 모두 다르면 룩 이동 불가")
    void cannotMove_DifferentFileDifferentRank() {
        Rook rook = new Rook(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(8, 8);
        assertThat(rook.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("같은 위치로는 이동 불가")
    void cannotMove_SamePosition() {
        Rook rook = new Rook(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 1);
        assertThat(rook.canMove(position1, position2)).isFalse();
    }
}
