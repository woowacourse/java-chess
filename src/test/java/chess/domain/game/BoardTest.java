package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;
    private Board board2;

    @BeforeEach
    void setUp() {
        board = BoardFactory.create();
        board2 = BoardFactory.create();
    }

    @Test
    void score() {
        System.out.println(board2.score(Color.BLACK));
        System.out.println(board2.score(Color.WHITE));
    }
}