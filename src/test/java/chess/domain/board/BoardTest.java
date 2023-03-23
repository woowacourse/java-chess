package chess.domain.board;

import chess.domain.pieces.Position;
import chess.domain.pieces.King;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.create();
    }

    @Test
    @DisplayName("생성된 board의 맵의 개수는 64개 이다.")
    void create_test_width() {
        // given
        Map<Position, Piece> expected = board.getBoard();

        // then
        assertThat(expected).hasSize(64);
    }

    @Test
    @DisplayName("폰 두칸 이동 테스트")
    void movePieceTest_Pawn() {
        // given

        Position current = new Position(Rank.TWO, File.B);
        Position target = new Position(Rank.FOUR, File.B);

        board.movePiece(current, target);

        // then
        assertThat(board.getBoard().get(target)).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("폰은 처음 움직임이 아니면 두칸을 이동할 수 없다.")
    void movePieceTest_Pawn_error() {
        // given
        Position current = new Position(Rank.TWO, File.B);
        Position target = new Position(Rank.FOUR, File.B);

        board.movePiece(current, target);

        Position current2 = new Position(Rank.FOUR, File.B);
        Position target2 = new Position(Rank.SIX, File.B);

        assertThatThrownBy(() -> board.movePiece(current2, target2));
    }

    @Test
    @DisplayName("킹 이동 테스트 -> 킹 앞으로 한칸 이동")
    void movePieceTest_King() {
        Position current = new Position(Rank.TWO, File.E);
        Position target = new Position(Rank.FOUR, File.E);

        board.movePiece(current, target);

        Position current2 = new Position(Rank.ONE, File.E);
        Position target2 = new Position(Rank.TWO, File.E);

        board.movePiece(current2, target2);

        assertThat(board.getBoard().get(target2)).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("킹 이동 테스트 -> 킹은 두칸이동이 불가능 하다.")
    void movePieceTest_King_error() {
        Position current = new Position(Rank.TWO, File.E);
        Position target = new Position(Rank.FOUR, File.E);

        board.movePiece(current, target);

        Position current2 = new Position(Rank.FOUR, File.E);
        Position target2 = new Position(Rank.FIVE, File.E);

        board.movePiece(current2, target2);

        Position current3 = new Position(Rank.ONE, File.E);
        Position target3 = new Position(Rank.TREE, File.E);

        assertThatThrownBy(() -> board.movePiece(current3, target3));
    }
}
