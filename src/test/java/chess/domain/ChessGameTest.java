package chess.domain;

import static chess.domain.PositionFixture.C_2;
import static chess.domain.PositionFixture.C_4;
import static org.assertj.core.api.Assertions.assertThat;

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
        Board board = BoardFactory.createBoard();
        ChessGame chessGame = new ChessGame(board);
        chessGame.movePiece(C_2, C_4);

        assertThat(board.findPiece(C_2)).isInstanceOf(Empty.class);
        assertThat(board.findPiece(C_4)).isInstanceOf(Pawn.class);
    }
}
