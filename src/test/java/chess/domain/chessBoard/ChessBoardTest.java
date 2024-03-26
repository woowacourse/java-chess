package chess.domain.chessBoard;

import chess.domain.chessBoard.generator.ChessSpaceGenerator;
import chess.domain.piece.Color;
import chess.domain.position.Coordinate;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardTest {

    private ChessBoard chessBoard;
    private final Position from = Position.fromCoordinate(Coordinate.of("b2"));
    private final Position to = Position.fromCoordinate(Coordinate.of("b3"));

    @BeforeEach
    void beforeEach() {
        chessBoard = new ChessBoard(new ChessSpaceGenerator());
    }

    @Test
    @DisplayName("게임 중인 상태가 아니면 피스를 움직일 수 없다")
    void should_not_move_when_not_playing_game() {
        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 활성화되지 않았습니다");

    }

    @Test
    @DisplayName("해당 색의 턴이 아니면 피스를 움직일 수 없다")
    void should_not_move_when_not_own_color_turn() {
        Position blackFrom = Position.fromCoordinate(Coordinate.of("b7"));
        Position blackTo = Position.fromCoordinate(Coordinate.of("b6"));
        chessBoard.startGame();

        assertThatThrownBy(() -> chessBoard.move(blackFrom, blackTo))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("상대 플레이어의 차례입니다");

    }

    @Test
    @DisplayName("게임을 시작하고 해당 색의 턴이면 피스를 움직일 수 있다")
    void should_move_when_start_and_own_color_turn() {
        chessBoard.startGame();

        assertThatCode(() -> chessBoard.move(from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임을 끝내면 더이상 피스를 움직일 수 없다")
    void should_not_move_when_end() {
        chessBoard.startGame();
        chessBoard.endGame();

        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 활성화되지 않았습니다");
    }

    @Test
    @DisplayName("게임을 끝내면 다시 시작할 수 없다")
    void should_not_restart_when_end() {
        chessBoard.startGame();
        chessBoard.endGame();

        assertThatThrownBy(() -> chessBoard.startGame())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 시작할 수 없는 상태입니다");
    }

    @Test
    @DisplayName("해당 색의 총 점수를 계산할 수 있다(초기 상태)")
    void should_sum_piece_scores_initial_board() {
        assertThat(chessBoard.calculateScore(Color.WHITE).asDouble())
                .isEqualTo(38.0);
    }

    /*
    Pp......
    Pp......
    .p......
    ........
    ........
    ........
    ........
    ........
     */
    @Test
    @DisplayName("해당 색의 총 점수를 계산할 수 있다(2개 폰이 같은 file에 있을 때)")
    void should_sum_piece_scores_two_pawns_in_column() {
        ChessBoard pawnChessBoard = new ChessBoard(new TestSpaceGenerator());

        assertThat(pawnChessBoard.calculateScore(Color.BLACK).asDouble())
                .isEqualTo(1.0);
    }

    @Test
    @DisplayName("해당 색의 총 점수를 계산할 수 있다(3개 폰이 같은 file에 있을 때)")
    void should_sum_piece_scores_three_pawns_in_column() {
        ChessBoard pawnChessBoard = new ChessBoard(new TestSpaceGenerator());

        assertThat(pawnChessBoard.calculateScore(Color.WHITE).asDouble())
                .isEqualTo(1.5);
    }

    /*
    RNBQKBNR
    rnbqkbnr
    ........
    ........
    ........
    ........
    ........
    ........
     */
    @Test
    @DisplayName("왕이 죽으면 보드가 비활성화 된다(움직일 수 없다)")
    void should_inactive_when_king_die() {
        ChessBoard stickChessBoard = new ChessBoard(new StickSpaceGenerator());
        stickChessBoard.startGame();

        stickChessBoard.move(
                Position.fromCoordinate(Coordinate.of("e7")),
                Position.fromCoordinate(Coordinate.of("e8"))
        );

        assertThat(stickChessBoard.isActive()).isFalse();
    }
}
