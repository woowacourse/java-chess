package chess.domain.game;

import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameTest {

    @Test
    @DisplayName("move 명령시 king이 죽으면 실행할 수 없는 상태로 변한다.")
    void isRunnable() {
        List<Point> arguments = List.of(Point.of("e1"), Point.of("e8"));
        Game game = new Game(BoardFixtures.create(Map.of(
                Point.of("e8"), new King(Color.BLACK),
                Point.of("e1"), new Queen(Color.WHITE),
                Point.of("a2"), new King(Color.WHITE)
        )), Color.WHITE);

        game.move(arguments);

        assertThat(game.isRunnable()).isFalse();
    }

    @Test
    @DisplayName("move 명령시 turnColor가 변한다.")
    void turnColorChangeWhenMove() {
        List<Point> arguments = List.of(Point.of("e1"), Point.of("e8"));
        Game game = new Game(BoardFixtures.create(Map.of(
                Point.of("e8"), new King(Color.BLACK),
                Point.of("e1"), new Queen(Color.WHITE),
                Point.of("a2"), new King(Color.WHITE)
        )), Color.WHITE);

        game.move(arguments);

        assertThat(game.getTurnColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("king이 죽어있으면 move명령시 예외가 발생한다.")
    void throwsExceptionWithTryingToMoveWhenKingDead() {
        List<Point> arguments = List.of(Point.of("e1"), Point.of("e8"));
        Game game = new Game(BoardFixtures.create(Map.of(
                Point.of("e8"), new King(Color.BLACK),
                Point.of("e1"), new Queen(Color.WHITE)
        )), Color.WHITE);

        assertThatThrownBy(() -> game.move(arguments))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("[ERROR]");
    }
}
