package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.PieceColor;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("기물")
class PieceTest {

    @Test
    @DisplayName("목적지로 향하는 경로를 생성한다.")
    void generatePath() {
        Piece bishop = new Bishop(PieceColor.BLACK);

        List<Position> path = bishop.generatePath(Position.from("c6"), Position.from("c1"));

        assertThat(path).isEqualTo(List.of(
                Position.from("c5"),
                Position.from("c4"),
                Position.from("c3"),
                Position.from("c2"),
                Position.from("c1")));
    }
}
