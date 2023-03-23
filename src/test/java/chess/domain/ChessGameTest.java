package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ChessGameTest {

    @Test
    void 사용자가_말을_움직였을때_말의_위치를_이동할_수_있다() {
        Board board = BoardFactory.createBoard();
        ChessGame chessGame = new ChessGame(board);
        chessGame.movePiece(C_2, C_4);

        assertThat(board.findPiece(C_2)).isEqualTo(null);
        assertThat(board.findPiece(C_4)).isInstanceOf(Pawn.class);
    }

    @Test
    void 사용자가_말을_움직였을때_다른_팀의_말을_움직이는_경우_예외가_발생한다() {
        Board board = BoardFactory.createBoard();
        ChessGame chessGame = new ChessGame(board);

        assertThatThrownBy(() -> chessGame.movePiece(C_7, C_6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("본인의 말만 옮길 수 있습니다.");
    }
}
