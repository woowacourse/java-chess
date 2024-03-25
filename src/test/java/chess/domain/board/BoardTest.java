package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("보드에서 체스 말을 이동시킬 수 있다.")
    void movePiece() {
        Board board = BoardCreator.create();
        board.move(new Position(1, 2), new Position(1, 4));
        Map<Position, Piece> boardPieces = board.getBoard();
        assertAll(
                () -> assertThat(boardPieces.get(new Position(1, 2)))
                        .isEqualTo(new Empty()),
                () -> assertThat(boardPieces.get(new Position(1, 4)))
                        .isEqualTo(new Pawn(Color.WHITE)));
    }

    @Test
    @DisplayName("보드에서 체스 말을 이동할 수 없는 경우 예외가 발생한다.")
    void movePieceThrowException() {
        Board board = BoardCreator.create();
        assertThatThrownBy(() -> board.move(new Position(1, 1), new Position(1, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능한 위치입니다.");
    }

    @Test
    @DisplayName("보드에서 상대 팀 체스 말을 이동할 경우 예외가 발생한다.")
    void movePieceOppositeColorThrowException() {
        Board board = BoardCreator.create();
        assertThatThrownBy(() -> board.move(new Position(1, 7), new Position(1, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대 팀의 기물을 이동시킬 수 없습니다.");
    }
}
