package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BlackTurnTest {

    private ChessGame chessGame;
    private BlackTurn blackTurn;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Board(PieceFactory.createPieces()));
        blackTurn = new BlackTurn(chessGame);
    }

    @Test
    void start() {
        assertThatThrownBy(() -> blackTurn.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void move() {
        assertThatCode(() -> blackTurn.move(new Position(1, 0), new Position(2, 0)))
                .doesNotThrowAnyException();
    }

    @Test
    void end() {
        assertThatCode(() -> blackTurn.end())
                .doesNotThrowAnyException();
    }

    @Test
    void isFinished() {
        boolean actual = blackTurn.isFinished();
        assertThat(actual).isFalse();
    }

}