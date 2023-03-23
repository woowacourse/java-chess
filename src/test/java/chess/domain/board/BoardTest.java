package chess.domain.board;

import static chess.domain.PositionFixture.A_1;
import static chess.domain.PositionFixture.A_2;
import static chess.domain.PositionFixture.A_3;
import static chess.domain.PositionFixture.B_1;
import static chess.domain.PositionFixture.C_2;
import static chess.domain.PositionFixture.C_4;
import static chess.domain.PositionFixture.C_5;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

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
        Board board = new Board(Map.of(B_1, new Bishop(WHITE)));

        assertThatThrownBy(() -> board.findPiece(C_4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치를 입력했습니다");
    }

    @Test
    void 경로를_입력했을_때_빈_공간을_입력하면_예외가_발생한다() {
        Board board = BoardFactory.createBoard();

        assertThatThrownBy(() -> board.movePiece(C_4, C_5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 없습니다.");
    }

    @Test
    void 경로를_입력했을_때_말이_갈_수_없는_경로라면_예외가_발생한다() {
        Board board = BoardFactory.createBoard();

        assertThatThrownBy(() -> board.movePiece(C_2, C_5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치를 지정하셨습니다.");
    }

    @Test
    void 경로를_입력했을_때_경로에_다른_말이_있다면_예외가_발생한다() {
        Board board = BoardFactory.createBoard();

        assertThatThrownBy(() -> board.movePiece(A_1, A_3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 다른 말이 있습니다.");
    }

    @Test
    void 말을_움직인다() {
        Board board = BoardFactory.createBoard();
        board.movePiece(C_2, C_4);
        assertAll(
                () -> assertThat(board.findPiece(C_2)).isInstanceOf(Empty.class),
                () -> assertThat(board.findPiece(C_4)).isInstanceOf(Pawn.class)
        );
    }

    @Test
    void 초기점수를_계산한다() {
        Board board = BoardFactory.createBoard();

        assertThat(board.calculateScore(WHITE)).isEqualTo(38.0);
    }

    @Test
    void 폰이_같은_File에_있을_때의_점수를_계산한다() {
        Board board = new Board(Map.of(A_1, new Pawn(WHITE), A_2, new Pawn(WHITE)));

        assertThat(board.calculateScore(WHITE)).isEqualTo(1.0);
    }
}
