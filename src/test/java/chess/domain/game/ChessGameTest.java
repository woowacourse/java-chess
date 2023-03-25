package chess.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.pieces.Empty;
import chess.domain.pieces.Team;
import chess.factory.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("White팀 black팀의 King 모두가 살아있으면 false를 반환한다.")
    void return_false_when_all_of_king_is_alive() {
        // given
        Board board = BoardFactory.createBoard();
        ChessGame chessGame = new ChessGame(board);

        // when
        boolean result = chessGame.isGameOver();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("두 팀의 King 중 하나가 죽으면 true를 반환한다.")
    void return_true_when_one_of_king_die() {
        // given
        Board board = BoardFactory.createBoard();
        board.getBoard().put(Position.from("e1"), new Empty(Team.EMPTY));
        ChessGame chessGame = new ChessGame(board);

        // when
        boolean result = chessGame.isGameOver();

        // then
        assertThat(result).isTrue();
    }
}