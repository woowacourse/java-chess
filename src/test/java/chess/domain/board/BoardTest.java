package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.boardGenerator.CheckmateBoardGenerator;
import chess.domain.board.boardGenerator.NotCheckmateBoardGenerator;
import chess.domain.board.boardGenerator.TestBoardGenerator;
import chess.domain.board.boardGenerator.WhiteCheckBoardGenerator;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    @DisplayName("체스판이 비어있는지 확인한다.")
    @Test
    void empty_board() {
        // then
        assertThat(board.isEmpty()).isTrue();
    }

    @DisplayName("체스판이 초기화 되었는지 확인한다.")
    @Test
    void empty_board_false() {
        // given
        board.initBoard(new BasicBoardGenerator());

        // then
        assertThat(board.isEmpty()).isFalse();
    }

    @DisplayName("무사히 이동됨")
    @Test
    void move() {
        // given
        board.initBoard(new BasicBoardGenerator());

        Position from = Position.of(Column.E, Row.TWO);
        Position to = Position.of(Column.E, Row.THREE);

        // then
        assertThatNoException()
                .isThrownBy(() -> board.move(from, to));
    }

    @DisplayName("이동 경로에 말이 있으면 이동 할 수 없다.")
    @Test
    void valid_path() {
        // given
        board.initBoard(new BasicBoardGenerator());

        Position from = Position.of(Column.D, Row.ONE);
        Position to = Position.of(Column.F, Row.THREE);

        // then
        assertThatThrownBy(() -> board.move(from, to))
                .hasMessage("이동 경로에 말이 있습니다.");
    }

    @DisplayName("도착 지점에 같은 팀의 말이 있는 경우")
    @Test
    void valid_arrive() {
        // given
        board.initBoard(new BasicBoardGenerator());

        Position from = Position.of(Column.D, Row.ONE);
        Position to = Position.of(Column.E, Row.TWO);

        // then
        assertThatThrownBy(() -> board.move(from, to))
                .hasMessage("도착 지점에 아군 말이 있어 이동이 불가능합니다.");
    }

    @DisplayName("자신의 차례인지 검증")
    @Test
    void valid_turn() {
        // given
        board.initBoard(new BasicBoardGenerator());

        Position from = Position.of(Column.A, Row.SEVEN);
        Position to = Position.of(Column.A, Row.SIX);

        // then
        assertThatThrownBy(() -> board.move(from, to))
                .hasMessage("현재 차례는 WHITE 입니다.");
    }

    @DisplayName("체크인 상황")
    @Test
    void valid_black_check() {
        // given
        board.initBoard(new WhiteCheckBoardGenerator());

        // then
        assertThat(board.check()).isTrue();
    }

    @DisplayName("폰이 이동할 수 없는 대각선 위치에 왕이 존재하는 경우 check 아님")
    @Test
    void valid_pawn_move_fail() {
        // given
        Position blackPawnPosition = Position.of("a1");
        Position whiteKingPosition = Position.of("b2");

        TestBoardGenerator boardGenerator = new TestBoardGenerator();
        boardGenerator.put(blackPawnPosition, new Pawn(Team.BLACK));
        boardGenerator.put(whiteKingPosition, new King(Team.WHITE));

        board.initBoard(boardGenerator);

        // then
        assertThat(board.check()).isFalse();
    }

    @DisplayName("폰이 이동 가능한 위치에 왕이 존재하는 경우 check")
    @Test
    void valid_pawn_move_ok() {
        // given
        Position blackPawnPosition = Position.of("a3");
        Position whiteKingPosition = Position.of("b2");

        TestBoardGenerator boardGenerator = new TestBoardGenerator();
        boardGenerator.put(blackPawnPosition, new Pawn(Team.BLACK));
        boardGenerator.put(whiteKingPosition, new King(Team.WHITE));

        board.initBoard(boardGenerator);

        // then
        assertThat(board.check()).isTrue();
    }

    @DisplayName("체크메이트")
    @Test
    void checkmate() {
        // given
        board.initBoard(new CheckmateBoardGenerator());

        // then
        assertThat(board.checkmate()).isTrue();
    }

    @DisplayName("체크메이트 아닌 경우")
    @Test
    void checkmate_failed() {
        // given
        board.initBoard(new NotCheckmateBoardGenerator());

        // then
        assertThat(board.checkmate()).isFalse();
    }

    @DisplayName("체크인 상황에서 벗어나지 않을 경우")
    @Test
    void valid_black_check_move() {
        // given
        board.initBoard(new WhiteCheckBoardGenerator());

        // then
        assertThatThrownBy(() -> board.move(Position.of("a8"), Position.of("a7")))
                .hasMessage("체크 상황을 벗어나야 합니다.");
    }

    @DisplayName("체크인 상황에서 벗어남")
    @Test
    void valid_black_check_move_false() {
        // given
        board.initBoard(new WhiteCheckBoardGenerator());

        // then
        assertThatNoException()
                .isThrownBy(() -> board.move(Position.of("d8"), Position.of("d7")));
    }
}
