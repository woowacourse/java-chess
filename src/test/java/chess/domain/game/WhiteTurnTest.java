package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WhiteTurnTest {

    private ChessGame chessGame;
    private WhiteTurn whiteTurn;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Board(PieceFactory.createPieces()));
        whiteTurn = new WhiteTurn(chessGame);
    }

    @Test
    void start() {
        assertThatThrownBy(() -> whiteTurn.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void end() {
        assertThatCode(() -> whiteTurn.end())
                .doesNotThrowAnyException();
    }

    @Test
    void move() {
        assertThatCode(() -> whiteTurn.move(new Position(6, 0), new Position(5, 0)))
                .doesNotThrowAnyException();
    }

    @Test
    void isFinished() {
        boolean actual = whiteTurn.isFinished();
        assertThat(actual).isFalse();
    }

}