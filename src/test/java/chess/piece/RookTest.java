package chess.piece;

import chess.Position;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Test
    @DisplayName("룩의 진행 방향이 맞는다면 true 반환")
    void correctMove() {
        Rook rook = new Rook(Position.of('a', '1'), Team.WHITE);

        assertThat(rook.isMovable(Position.of('f', '1'))).isTrue();
    }

    @Test
    @DisplayName("source와 target사이의 position들을 얻는다.")
    void getIntervalPositionTest(){
        Piece rook = new Rook(Position.of('h','8'),Team.BLACK);
        Piece king = new King(Position.of('e','8'),Team.BLACK);
        List<Position> intervalPosition = rook.getIntervalPosition(king);

        assertThat(intervalPosition.contains(Position.of('f','8'))).isTrue();
        assertThat(intervalPosition.contains(Position.of('g','8'))).isTrue();
    }
}
