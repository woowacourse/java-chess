package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.board.Square;
import domain.piece.Camp;

class KingTest {
    @Test
    @DisplayName("King이 이동할 수 있는 칸의 좌표를 반환한다.")
    void kingMoveTest() {
        King king = new King(Camp.WHITE);
        assertThat(king.fetchMovableCoordinate(new Square(0, 3), new Square(1,4))).contains(
                new Square(1,4)
        );
    }
}
