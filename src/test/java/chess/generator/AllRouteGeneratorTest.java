package chess.generator;

import chess.domain.chesspiece.Queen;
import chess.domain.move.Coordinate;
import chess.domain.move.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.game.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

public class AllRouteGeneratorTest {
    @Test
    @DisplayName("getAllRoute 테스트")
    void getAllRoute() {
        Queen queen = new Queen(WHITE);
        Position position = Position.of(Coordinate.of(4), Coordinate.of(4));

        assertThat(AllRouteGenerator.getAllRoute(queen, position)).isInstanceOf(List.class);
    }
}
