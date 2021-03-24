package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    Board board = new BoardFactory().create();

    @DisplayName("보드가 초기화 되었을 때 체스말이 없는 곳은 null 값을 가진다.")
    @Test
    void nullPositionTest() {
        assertThat(board.findPieceFromPosition(Position.of(Horizontal.C, Vertical.SIX))).isNull();
    }

    @DisplayName("보드가 생성되고 체스말의 위치가 올바른지 확인한다.")
    @Test
    void boardCreateTest() {
        assertThat(board.findPieceFromPosition(Position.of(Horizontal.C, Vertical.EIGHT))).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("기물 이동 수행")
    void movePieceTest() {
        board.movePiece(Position.of(Horizontal.A, Vertical.TWO),
                Position.of(Horizontal.A, Vertical.FOUR), Team.WHITE);
        assertThat(board.getBoard().get(Position.of(Horizontal.A, Vertical.TWO))).isNull();
        assertThat(board.getBoard().get(Position.of(Horizontal.A, Vertical.FOUR))).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("이동 범위 외 기물 이동 수행")
    void movePieceExceptionTest() {
        assertThatThrownBy(() -> {
            board.movePiece(Position.of(Horizontal.A, Vertical.TWO),
                    Position.of(Horizontal.A, Vertical.FIVE), Team.WHITE);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("기본 체스판의 점수는 각 38점이다")
    void calculateScore() {
        assertThat(board.calculateScore(Team.BLACK)).isEqualTo(38);
    }

    @Test
    @DisplayName("기물을 잃었을 때와 한 줄에 2개의 폰인 경우 점수 확인")
    void calculateScoreWhenPieceIsDead() {
        board.movePiece(Position.of(Horizontal.B, Vertical.TWO), Position.of(Horizontal.B, Vertical.FOUR), Team.WHITE);
        board.movePiece(Position.of(Horizontal.B, Vertical.FOUR), Position.of(Horizontal.B, Vertical.FIVE), Team.WHITE);
        board.movePiece(Position.of(Horizontal.B, Vertical.FIVE), Position.of(Horizontal.B, Vertical.SIX), Team.WHITE);
        board.movePiece(Position.of(Horizontal.B, Vertical.SIX), Position.of(Horizontal.C, Vertical.SEVEN), Team.WHITE);

        assertThat(board.calculateScore(Team.WHITE)).isEqualTo(37);
        assertThat(board.calculateScore(Team.BLACK)).isEqualTo(37);
    }

    @Test
    @DisplayName("본인 팀의 순서가 아닐 때에 말을 옮기는 경우 확인")
    void notTurnOwnerPlay() {
        assertThatThrownBy(() -> {
            board.movePiece(Position.of(Horizontal.B, Vertical.TWO),
                    Position.of(Horizontal.B, Vertical.FOUR), Team.BLACK);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
