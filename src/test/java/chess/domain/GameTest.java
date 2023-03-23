package chess.domain;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.game.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("White의 말을 한 번 움직이면 Black의 차례로 넘어간다.")
    @Test
    void Should_NextTurn_When_Move() {
        final Game game = new Game();

        game.move(new Square(File.B, Rank.TWO), new Square(File.B, Rank.THREE));

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> game.move(new Square(File.B, Rank.THREE), new Square(File.B, Rank.FOUR))
        );
    }
}
