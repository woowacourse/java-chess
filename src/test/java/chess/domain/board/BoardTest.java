package chess.domain.board;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static chess.domain.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class BoardTest {

    @Test
    void Source_포지션을_받아_해당_위치의_말을_받을_수_있다() {
        Board board = BoardFactory.createBoard();

        Piece piece = board.findPiece(B_1);

        assertThat(piece).isInstanceOf(Knight.class);
    }

    @Test
    void 잘못된_위치를_입력하면_예외가_발생한다() {
        Board board = new Board(Map.of(B_1, new Bishop(Team.WHITE, B_1)));

        assertThatThrownBy(() -> board.findPiece(C_4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치를 입력했습니다.");
    }

    @Test
    void 경로를_입력했을_때_갈_수_있는_경로라면_true를_반환한다() {
        Board board = BoardFactory.createBoard();

        List<Position> paths = List.of(B_3, D_4, C_5);
        assertThat(board.isEmptyPosition(paths)).isTrue();
    }

    @Test
    void 경로를_입력했을_때_갈_수_없는_경로라면_false를_반환한다() {
        Board board = BoardFactory.createBoard();

        List<Position> paths = List.of(B_2, B_3, D_4, C_5);
        assertThat(board.isEmptyPosition(paths)).isFalse();
    }

    @Test
    void 말을_움직인다() {
        Board board = BoardFactory.createBoard();
        board.movePiece(C_2, C_4, Team.WHITE);

        assertThat(board.findPiece(C_2)).isInstanceOf(Empty.class);
        assertThat(board.findPiece(C_4)).isInstanceOf(Pawn.class);
    }

    @Test
    void 경로에_말이_포함되어있을_때_말을_움직일_수_없다() {
        Board board = BoardFactory.createBoard();
        assertThatThrownBy(() -> board.movePiece(B_1, B_3, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치를 입력했습니다.");
    }

    @Test
    void 경로에_아무것도_없다면_말을_움직일_수_있다() {
        Board board = BoardFactory.createBoard();
        board.movePiece(B_2, B_3, Team.WHITE);

        assertThat(board.findPiece(B_2)).isInstanceOf(Empty.class);
        assertThat(board.findPiece(B_3)).isInstanceOf(Pawn.class);
    }
}
