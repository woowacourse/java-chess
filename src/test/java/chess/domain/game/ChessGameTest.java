package chess.domain.game;

import chess.boardstrategy.BoardStrategy;
import chess.boardstrategy.InitialBoardStrategy;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class ChessGameTest {

    private static final BoardStrategy boardStrategy = new InitialBoardStrategy();
    private ChessGame chessGame;

    @BeforeEach
    void setup() {
        chessGame = new ChessGame();
    }

    @Test
    void 이미_게임이_시작된_뒤에_재시작하면_예외() {
        chessGame.start(boardStrategy);

        assertThatThrownBy(() -> chessGame.start(boardStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("게임이 이미 시작되었습니다");
    }

    @Test
    void 게임이_시작된적_없으면_정상적으로_게임_실행() {
        assertDoesNotThrow(() -> chessGame.start(boardStrategy));
    }

    /**todo : 질문 6
     * 체스 게임의 move메서드는, 게임이 시작되었는지 여부에 따라 예외가 발생하기도 하지만,
     * 메서드 내부에서 호출하는 chessBoard.move도 예외를 발생시킵니다.

     * 이와같이, test하려는 기능 안에서 호출되는 메서드가
     * Runtime에러를 발생시킬 수 있다면 각 메서드를 따로 테스트하는 것으로 만족해야하나요
     * 아니면, 구조 설계가 잘못되었음을 알려주는 반증일까요?

     */

    @Test
    void 게임이_시작된적_없는데_move메서드_실행하면_예외() {

        assertThatThrownBy(()-> chessGame.move(List.of("move","b2", "c3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("게임이 시작되지 않았습니다");
    }

    @Test
    void 게임이_시작된_뒤에_move메서드_실행된다() {
        chessGame.start(boardStrategy);
        assertDoesNotThrow(()-> chessGame.move(List.of("move","b2", "b3")));
    }

    @Test
    void WHIGHT_턴인데_BLACK기물을_이동하면_예외() {
        chessGame.start(new InitialBoardStrategy());

        assertThatThrownBy(()->chessGame.move(List.of("move", "d7", "d6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편의 기물을 움직일 수 없습니다");
    }

    @Test
    void BLACK턴인데_WHIGHT기물을_이동하면_예외() {
        chessGame.start(new InitialBoardStrategy());
        chessGame.move(List.of("move", "d2", "d3"));
        Position startPosition = Position.of(Column.C, Rank.TWO);
        Position endPosition = Position.of(Column.C, Rank.THREE);

        assertThatThrownBy(()->chessGame.move(List.of("move", "c2", "c3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편의 기물을 움직일 수 없습니다");
    }

}