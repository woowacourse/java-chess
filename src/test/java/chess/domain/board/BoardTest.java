package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardInitializer.initiateBoard();
    }

    @DisplayName("올바른 보드 생성된다.")
    @Test
    void createTest() {
        assertThat(board.of(Vertical.A, Horizontal.ONE)).isInstanceOf(Rook.class);
    }

    @DisplayName("입력한 위치의 기물을 가져온다.")
    @Test
    void of() {
        final Piece piece = board.of(Vertical.B, Horizontal.TWO);
        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(piece).isEqualTo(Pawn.getInstanceOf(Owner.WHITE));
    }

    @DisplayName("입력한 위치로 이동된다.")
    @Test
    void moveTest() {
        final Position source = new Position(Vertical.B, Horizontal.TWO);
        final Position target = new Position(Vertical.B, Horizontal.THREE);

        board.movePiece(source, target);

        assertThat(board.of(source)).isInstanceOf(Empty.class);
        assertThat(board.of(target)).isInstanceOf(Pawn.class);
    }

    @DisplayName("기물은 진행 방향에 자신의 팀 기물이 위치할 시 전진할 수 없다.")
    @Test
    void validateReachable(){
        assertThatThrownBy(
                () -> board.movePiece(new Position("a1"), new Position("a3")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰은 첫 움직임에서 앞으로 두 칸을 이동할 수 있다.")
    @Test
    void validatePawnMovement1(){
        board.movePiece(new Position("a2"), new Position("a4"));
        assertThat(board.of(new Position("a4"))).isEqualTo(Pawn.getInstanceOf(Owner.WHITE));
    }

    @DisplayName("폰은 첫 움직임에서 한 칸만을 이동할 수 있다.")
    @Test
    void validatePawnMovement2(){
        board.movePiece(new Position("a2"), new Position("a3"));
        assertThat(board.of(new Position("a3"))).isEqualTo(Pawn.getInstanceOf(Owner.WHITE));
    }

    @DisplayName("폰의 전진은 적 기물을 잡을 수 없다.")
    @Test
    void validatePawnMovement3(){
        board.movePiece(new Position("a2"), new Position("a4"));
        board.movePiece(new Position("a7"), new Position("a5"));
        assertThatThrownBy(()->board.movePiece(new Position("a4"), new Position("a5"))
        ).isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("폰은 대각선으로 적이 있다면 한칸 움직여 잡을 수 있다.")
    @Test
    void validatePawnMovement4(){
        board.movePiece(new Position("a7"), new Position("a5"));
        board.movePiece(new Position("a5"), new Position("a4"));
        board.movePiece(new Position("a4"), new Position("a3"));
        board.movePiece(new Position("b2"), new Position("a3"));
        assertThat(board.of(new Position("a3"))).isEqualTo(Pawn.getInstanceOf(Owner.WHITE));
    }
}
