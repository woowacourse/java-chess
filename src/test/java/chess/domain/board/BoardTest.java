package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Position;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.King;
import chess.domain.pieces.Pawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new BoardMaker());
    }

    @Test
    @DisplayName("생성된 board의 세로 길이는 8개 이다.")
    void create_test_height() {
        assertThat(board.getBoard()).hasSize(8);
    }

    @Test
    @DisplayName("생성된 board의 가로 길이는 8개 이다.")
    void create_test_width() {
        // given
        List<Rank> ranks = board.getBoard();

        // then
        assertThat(ranks.get(0).getRank()).hasSize(8);
    }

    @Test
    @DisplayName("폰 이동 테스트")
    void movePieceTest_Pawn() {
        // given
        int targetX = 3;
        int targetY = 4;

        Position current = new Position(1, 4);
        Position target = new Position(targetX, targetY);

        board.movePiece(current, target);

        // then
        assertThat(board.getBoard().get(targetX).getRank().get(targetY).getPiece()).isInstanceOf(Pawn.class);

        board.movePiece(new Position(3, 4), new Position(4, 4));
        assertThat(board.getBoard().get(3).getRank().get(4).getPiece()).isInstanceOf(EmptyPiece.class);
        assertThat(board.getBoard().get(4).getRank().get(4).getPiece()).isInstanceOf(Pawn.class);

        assertThatThrownBy(
                () -> board.movePiece(new Position(4, 4), new Position(6, 4))
        );

        assertThatThrownBy(
                () -> board.movePiece(new Position(4, 4), new Position(3, 4))
        );
    }

    @Test
    @DisplayName("다른 말 이동 테스트")
    void movePieceTest() {
        // given
        int targetX = 3;
        int targetY = 4;

        Position current = new Position(1, 4);
        Position target = new Position(targetX, targetY);

        board.movePiece(current, target);
    }

    @Test
    @DisplayName("킹 이동 테스트 -> 킹 앞으로 한칸 이동")
    void movePieceTest_King() {
        board.movePiece(new Position(6,4), new Position(4,4));
        // given

        //int targetX = 6;
        //int targetY = 4;

        Position current = new Position(7, 4);
        Position target = new Position(6, 4);

        board.movePiece(current, target);
        Assertions.assertThat(board.getBoard().get(6).getRank().get(4).getPiece()).isInstanceOf(King.class);
        board.movePiece(new Position(6,4), new Position(5,4));
        Assertions.assertThat(board.getBoard().get(5).getRank().get(4).getPiece()).isInstanceOf(King.class);
        board.movePiece(new Position(5,4), new Position(5,3));
        Assertions.assertThat(board.getBoard().get(5).getRank().get(3).getPiece()).isInstanceOf(King.class);

        assertThatThrownBy(
                () -> board.movePiece(new Position(5,3), new Position(3,3))
        );
    }


}
