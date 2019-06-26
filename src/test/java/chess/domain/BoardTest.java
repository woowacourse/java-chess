package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @Test
    void 폰_움직임() {
        Map<Spot, Piece> spots = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            spots.put(Spot.valueOf(i), Empty.getInstance());
        }

        Spot startSpot = Spot.valueOf(1, 1);
        Spot endSpot = Spot.valueOf(1, 2);
        spots.replace(startSpot, new Pawn(Team.BLACK));
        Board board = new Board(spots);
        spots.replace(startSpot, Empty.getInstance());
        spots.replace(endSpot, new Pawn(Team.BLACK));
        Board movedBoard = board.move(startSpot, endSpot);

        assertThat(movedBoard).isEqualTo(new Board(spots));
    }

    @Test
    void 안_움직임() {
        Map<Spot, Piece> spots = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            spots.put(Spot.valueOf(i), Empty.getInstance());
        }

        Spot startSpot = Spot.valueOf(1, 1);
        Spot endSpot = Spot.valueOf(1, 5);
        spots.replace(startSpot, new Pawn(Team.BLACK));
        Board board = new Board(spots);
        Board movedBoard = board.move(startSpot, endSpot);

        assertThat(movedBoard).isEqualTo(new Board(spots));
    }
}