import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.board.Board;
import chess.PieceOperator;
import chess.board.Point;
import chess.board.State;
import chess.board.Team;
import chess.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceOperatorTest {

    private Board board;
    private PieceOperator pieceOperator;

    @BeforeEach
    @DisplayName("보드의 초기 설정")
    void setUp() {
        board = new Board();
        pieceOperator = new PieceOperator(board);

        pieceOperator.initialize();
    }

    @Test
    @DisplayName("킹 이동 테스트(이동 위치에 아군 말이 있는 경우 예외처리)")
    void kingWithInvalidMove() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            pieceOperator.move(Point.of("e1"), Point.of("e2")))
            .withMessage("해당 위치로는 이동할 수 없는 말입니다.");
    }

    @Test
    @DisplayName("킹 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void kingsMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            pieceOperator.move(Point.of("e1"), Point.of("e3")))
            .withMessage("해당 위치로는 이동할 수 없는 말입니다.");
    }

    @Test
    @DisplayName("킹을 유효한 위치로 이동 테스트")
    void kingWithValidMove() {
        board.move(Point.of("e2"), Point.of("e3")); // 폰 이동
        board.move(Point.of("e1"), Point.of("e2"));

        assertThat(board.getState(Point.of("e2"))).isEqualTo(State.of(Piece.KING, Team.WHITE));
    }

    @Test
    @DisplayName("퀸을 유효한 위치로 이동 테스트")
    void queenWithValidMove() {
        board.move(Point.of("d2"), Point.of("d3"));
        pieceOperator.move(Point.of("d1"), Point.of("d2"));

        assertThat(board.getState(Point.of("d2"))).isEqualTo(State.of(Piece.QUEEN, Team.WHITE));
    }

    @Test
    @DisplayName("퀸 이동 테스트(이동 위치에 아군 말이 있는 경우 예외처리)")
    void queensMoveToInvalidMove() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            pieceOperator.move(Point.of("d1"), Point.of("d2"))
        ).withMessage("해당 위치로는 이동할 수 없는 말입니다.");
    }

    @Test
    @DisplayName("퀸 이동 테스트(해당 위치로 가는 길이 막힌 경우 예외처리)")
    void queensMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            pieceOperator.move(Point.of("d1"), Point.of("d3"))
        ).withMessage("해당 위치로는 이동할 수 없는 말입니다.");
    }

    @Test
    @DisplayName("비숍을 유효한 위치로 이동 테스트")
    void bishopWithValidMove() {
        board.move(Point.of("d2"), Point.of("d3"));
        pieceOperator.move(Point.of("c1"), Point.of("h6"));
        assertThat(board.getState(Point.of("h6"))).isEqualTo(State.of(Piece.BISHOP, Team.WHITE));
    }

    @Test
    @DisplayName("비숍 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void bishopMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(()->
            pieceOperator.move(Point.of("c1"), Point.of("c4"))
            ).withMessage("해당 위치로는 이동할 수 없는 말입니다.");
    }

    @Test
    @DisplayName("룩을 유효한 위치로 이동 테스트")
    void rookWithValidMove() {
        board.move(Point.of("a2"), Point.of("a6"));
        pieceOperator.move(Point.of("a1"), Point.of("a5"));
        assertThat(board.getState(Point.of("a5"))).isEqualTo(State.of(Piece.ROOK, Team.WHITE));
    }

    @Test
    @DisplayName("룩 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void rookMoveToInvalidPoint() {
        board.move(Point.of("b2"), Point.of("b3"));
        assertThatIllegalArgumentException().isThrownBy(()->
            pieceOperator.move(Point.of("a1"), Point.of("f6"))
        ).withMessage("해당 위치로는 이동할 수 없는 말입니다.");
    }
}
