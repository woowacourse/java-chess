package chess.domain.game;

import chess.domain.location.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {
    Calculator calculator = new Calculator();
    Board board = BoardFactory.create();

    @Test
    void totalScore() {
        assertThat(calculator.score(Color.BLACK, board.allPieces())).isEqualTo(38);
    }

    @Test
    void lowerPawn() {
        Map<Position, Piece> maps = EmptyBoardMap.create();
        maps.put(Position.from("a2"), new Pawn(Color.BLACK));
        maps.put(Position.from("a3"), new Pawn(Color.BLACK));
        maps.put(Position.from("a4"), new Pawn(Color.BLACK));

        assertThat(calculator.lowerPawnScore(Color.BLACK, maps)).isEqualTo(1.5);
    }
}