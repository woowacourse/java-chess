package chess.domain.board;

import static chess.domain.board.Board.INVALID_TARGET_POSITION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Team;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Score;
import chess.domain.pieces.pawn.WhitePawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.create();
    }

    @Test
    @DisplayName("생성된 Board는 64개의 Position과 Piece를 가진다.")
    void 생성된_Board는_64개의_Position과_Piece를_가진다() {
        assertThat(board.getBoard().size()).isEqualTo(64);
    }

    @Test
    @DisplayName("기물을 이동한다.")
    void 기물을_이동한다() {
        Position currentPosition = Position.of(6, 0);
        Position targetPosition = Position.of(4, 0);

        board.movePiece(currentPosition, targetPosition);

        Map<Position, Piece> values = board.getBoard();

        assertAll(
                () -> assertThat(values.get(currentPosition)).isInstanceOf(EmptyPiece.class),
                () -> assertThat(values.get(targetPosition)).isInstanceOf(WhitePawn.class)
        );
    }

    @Test
    @DisplayName("적절한 이동이 아닌 경우 예외가 발생한다.")
    void 적절한_이동이_아닌_경우_예외가_발생한다() {
        Position currentPosition = Position.of(1, 0);
        Position targetPosition = Position.of(7, 0);

        assertThatIllegalArgumentException().isThrownBy(
                () -> board.movePiece(currentPosition, targetPosition)
        );
    }

    @Test
    @DisplayName("동일한 좌표로 이동할 수 없다.")
    void 동일한_좌표로_이동할_수_없다() {
        Position currentPosition = Position.of(1, 0);
        Position targetPosition = Position.of(1, 0);

        assertThatIllegalArgumentException().isThrownBy(
                () -> board.movePiece(currentPosition, targetPosition)
        ).withMessage(INVALID_TARGET_POSITION);
    }

    @Test
    @DisplayName("킹이 모두 살아 있으면 true를 반환한다.")
    void 킹이_모두_살아_있으면_false를_반환한다() {
        assertThat(board.isKingAlive()).isTrue();
    }

    @Test
    @DisplayName("킹이 죽으면 false를 반환한다")
    void 킹이_죽으면_false를_반환한다() {
        Position e2 = Position.toPosition("e2");
        Position e4 = Position.toPosition("e4");
        board.movePiece(e2, e4);

        Position f7 = Position.toPosition("f7");
        Position f5 = Position.toPosition("f5");
        board.movePiece(f7, f5);

        Position e1 = Position.toPosition("e1");
        board.movePiece(e1, e2);

        Position f4 = Position.toPosition("f4");
        board.movePiece(f5, f4);

        Position e3 = Position.toPosition("e3");
        board.movePiece(e2, e3);

        board.movePiece(f4, e3);

        assertThat(board.isKingAlive()).isFalse();
    }

    @Test
    @DisplayName("검은색 팀의 점수를 계산한다")
    void 검은색_팀의_점수를_계산한다() {
        Score scoreOfBlackTeam = board.scores().get(Team.BLACK);

        assertThat(scoreOfBlackTeam).isEqualTo(new Score(38.0));
    }

    @Test
    @DisplayName("흰색 팀의 점수를 계산한다")
    void 흰색_팀의_점수를_계산한다() {
        Score scoreOfWhiteTeam = board.scores().get(Team.WHITE);

        assertThat(scoreOfWhiteTeam).isEqualTo(new Score(38.0));
    }

    /**
     * RNBQKBNR
     * PPPPPPP. 검은색 팀 점수: 37.0
     * ........
     * .......p
     * ........
     * ........
     * pppppp.p 흰색 팀 점수: 37.0
     * rnbqkbnr
     */
    @Test
    @DisplayName("폰이 같은 열에 있는 경우 각 폰은 0.5점으로 계산한다")
    void 폰이_같은_열에_있는_경우_각_폰은_0_5점_으로_계산한다_case1() {
        board.movePiece(Position.toPosition("g2"), Position.toPosition("g4"));
        board.movePiece(Position.toPosition("h7"), Position.toPosition("h5"));
        board.movePiece(Position.toPosition("g4"), Position.toPosition("h5"));

        Map<Team, Score> scores = board.scores();

        assertAll(
                () -> assertThat(scores.get(Team.WHITE)).isEqualTo(new Score(37.0)),
                () -> assertThat(scores.get(Team.BLACK)).isEqualTo(new Score(37.0))
        );
    }
}
