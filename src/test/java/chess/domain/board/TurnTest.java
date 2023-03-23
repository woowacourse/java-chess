package chess.domain.board;

import static chess.domain.board.Turn.INVALID_EMPTY_PIECE_MOVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.Team;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import chess.domain.pieces.pawn.BlackPawn;
import chess.domain.pieces.pawn.WhitePawn;
import chess.domain.pieces.sliding.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {

    private Turn turn;

    @BeforeEach
    void setUp() {
        turn = new Turn(Team.WHITE);
    }

    @Test
    @DisplayName("EmptyPiece를 움직인 경우 예외가 발생한다.")
    void EmptyPiece를_움직인_경우_예외가_발생한다() {
        Piece currentPositionPiece = new EmptyPiece();

        assertThatIllegalArgumentException().isThrownBy(
                () -> turn.validateTurn(currentPositionPiece)
        ).withMessage(INVALID_EMPTY_PIECE_MOVE);
    }

    @Test
    @DisplayName("White팀일 때 White팀의 기물만 움직일 수 있다.")
    void White팀일_때_White팀의_기물만_움직일_수_있다() {
        Piece piece = new Rook(Team.WHITE);

        assertThatNoException().isThrownBy(
                () -> turn.validateTurn(piece)
        );
    }

    @Test
    @DisplayName("Black팀일 때 Black팀의 기물만 움직일 수 있다.")
    void Black팀일_때_Black팀의_기물만_움직일_수_있다() {
        Turn turn = new Turn(Team.BLACK);
        Piece piece = new Rook(Team.BLACK);

        assertThatNoException().isThrownBy(
                () -> turn.validateTurn(piece)
        );
    }

    @Test
    @DisplayName("White팀일 때 Black팀의 기물을 움직일 수 없다.")
    void White팀일_때_Black팀의_기물을_움직일_수_없다() {
        var blackPawn = new BlackPawn();

        assertThatIllegalArgumentException().isThrownBy(
                () -> turn.validateTurn(blackPawn)
        );
    }

    @Test
    @DisplayName("Black팀일 때 White팀의 기물을 움직일 수 없다.")
    void Black팀일_때_White팀의_기물을_움직일_수_없다() {
        Turn turn = new Turn(Team.BLACK);
        var whitePawn = new WhitePawn();

        assertThatIllegalArgumentException().isThrownBy(
                () -> turn.validateTurn(whitePawn)
        );
    }

    @Test
    @DisplayName("턴을 바꿔준다.")
    void 턴을_바꿔준다() {
        assertThat(turn.getTurn()).isEqualTo(Team.WHITE);
        Turn changedTurn = turn.change();
        assertThat(changedTurn.getTurn()).isEqualTo(Team.BLACK);
    }
}
