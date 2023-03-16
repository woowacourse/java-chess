package chess.domain.board;

import static chess.domain.PositionFixture.B_1;
import static chess.domain.PositionFixture.C_4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;
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
        Board board = new Board(Map.of(B_1, new Bishop(Color.WHITE)));

        assertThatThrownBy(() -> board.findPiece(C_4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치를 입력했습니다");
    }

}
