package chess.domain.game;

import org.junit.jupiter.api.BeforeEach;


class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        Board board = BoardFactory.create();
        game = new Game(board);
    }

}