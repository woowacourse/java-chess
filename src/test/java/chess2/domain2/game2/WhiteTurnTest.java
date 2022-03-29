package chess2.domain2.game2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess2.domain2.board2.Board;
import chess2.dto2.MoveCommandDto;
import chess2.util2.BoardMapGeneratorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class WhiteTurnTest {

    private static final MoveCommandDto VALID_BLACK_MOVE = new MoveCommandDto("a7", "a6");
    private static final MoveCommandDto VALID_WHITE_MOVE = new MoveCommandDto("a2", "a3");

    private Game game;

    @BeforeEach
    void setUp() {
        Board board = new Board(BoardMapGeneratorUtil.initFullChessBoard());
        game = new WhiteTurn(board);
    }

    @Test
    void 백색_체스말_이동_후_흑색_턴_반환() {
        Game actual = game.moveChessmen(VALID_WHITE_MOVE);

        assertThat(actual).isInstanceOf(BlackTurn.class);
    }

    @Test
    void 백색_턴에서_흑색_체스말_이동시_예외발생() {
        assertThatThrownBy(() -> game.moveChessmen(VALID_BLACK_MOVE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("WHITE 진영이 움직일 차례입니다!");
    }

    @Test
    void 보드_정보가_동일한_백색_턴_인스턴스는_동일() {
        Board board = new Board(BoardMapGeneratorUtil.initFullChessBoard());
        Game game1 = new WhiteTurn(board);
        Game game2 = new WhiteTurn(board);

        assertThat(game1).isEqualTo(game2);
    }
}