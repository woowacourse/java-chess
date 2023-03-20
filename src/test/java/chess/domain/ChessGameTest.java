package chess.domain;

import static chess.domain.Team.WHITE;
import static chess.domain.square.File.A;
import static chess.domain.square.Rank.THREE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.Piece;
import chess.domain.square.Square;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Board(), WHITE);
    }

    @Test
    @DisplayName("체스 말을 이동시킨다.")
    void movePiece_success() {
        chessGame.movePiece("a2", "a3");
        Board board = chessGame.getBoard();
        Map<Square, Piece> pieces = board.getBoard();

        assertThat(pieces.containsKey(Square.of(A, THREE)))
                .isTrue();
    }

    @Test
    @DisplayName("현재 차례가 아닌 팀의 말을 이동하려고 하면 예외가 발생한다.")
    void cannot_movePiece_By_Team() {
        chessGame.movePiece("a2", "a3");
        assertThatThrownBy(() -> chessGame.movePiece("a3", "a4"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
