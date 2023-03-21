package chess.domain.game;

import static chess.domain.PositionFixture.C_2;
import static chess.domain.PositionFixture.C_4;
import static chess.domain.PositionFixture.C_6;
import static chess.domain.PositionFixture.C_7;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ChessGameTest {

    @Test
    void 사용자가_말을_움직였을때_말의_위치를_이동할_수_있다() {
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard());

        chessGame.movePieceTo(C_2, C_4);

        Board board = chessGame.getBoard();
        assertThat(board.findPiece(C_2)).isInstanceOf(Empty.class);
        assertThat(board.findPiece(C_4)).isInstanceOf(Pawn.class);
    }

    @Test
    void 사용자가_말을_움직였을때_다른_팀의_말을_움직이는_경우_예외가_발생한다() {
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard());

        assertThatThrownBy(() -> chessGame.movePieceTo(C_7, C_6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대 팀의 말을 옮길 수 없습니다.");
    }
}
