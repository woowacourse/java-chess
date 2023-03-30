package chess.domain.game;

import chess.boardstrategy.BoardStrategy;
import chess.boardstrategy.InitialBoardStrategy;
import chess.domain.board.ChessBoard;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class ChessGameTest {

    private static final BoardStrategy boardStrategy = new InitialBoardStrategy();
    private ChessGame chessGame;

    @BeforeEach
    void setup() {
        chessGame = new ChessGame(new ChessBoard(boardStrategy.generate()));
    }

    @Test
    void WHIGHT_턴인데_BLACK기물을_이동하면_예외() {
        Position startPosition = Position.of(Column.D, Rank.SEVEN);
        Position endPosition = Position.of(Column.D, Rank.SIX);

        assertThatThrownBy(()->chessGame.move(startPosition,endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편의 기물을 움직일 수 없습니다");
    }

    @Test
    void BLACK턴인데_WHIGHT기물을_이동하면_예외() {
        Position startPosition = Position.of(Column.C, Rank.TWO);
        Position endPosition = Position.of(Column.C, Rank.THREE);

        assertThatThrownBy(()-> chessGame.move(startPosition,endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편의 기물을 움직일 수 없습니다");
    }

}