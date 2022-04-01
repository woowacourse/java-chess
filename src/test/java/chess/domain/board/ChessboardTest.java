package chess.domain.board;

import chess.domain.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessboardTest {

    @Test
    @DisplayName("현재 위치와 이동하려는 위치가 같은 경우 예외 발생")
    void checkSamePosition() {
        Chessboard chessboard = Chessboard.create();

        assertThatThrownBy(() -> chessboard.movePiece(new Position(0, 0),
                new Position(0, 0), new Turn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("현재 위치와 같은 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("이동하려는 위치에 기물이 없는 경우 예외 발생")
    void checkBlankTarget() {
        Chessboard chessboard = Chessboard.create();

        assertThatThrownBy(() -> chessboard.movePiece(new Position(2, 0),
                new Position(3, 0), new Turn()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동하려는 위치에 기물이 없습니다.");
    }

    @Test
    @DisplayName("같은편의 기물을 공격하는 경우")
    void checkWrongTurn() {
        Chessboard chessboard = Chessboard.create();
        Turn turn = new Turn();

        assertThatThrownBy(() -> chessboard.movePiece(new Position(1, 0),
                new Position(1, 1), turn))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("같은편의 기물을 공격할 수 없습니다.");
    }

    @Test
    @DisplayName("움직일 수 있는 위치인지 확인")
    void checkMovablePosition() {
        Chessboard chessboard = Chessboard.create();

        assertThat(chessboard.isMovablePosition(new Position(6, 0),
                new Position(5, 0))).isTrue();
    }

    @Test
    @DisplayName("기물이 king인지 확인")
    void isKing() {
        Chessboard chessboard = Chessboard.create();

        assertThat(chessboard.isKing(new Position(7, 4))).isTrue();
    }

    @Test
    @DisplayName("존재하는 위치인 경우(Blank가 아닌지)")
    void isExistPosition() {
        Chessboard chessboard = Chessboard.create();

        assertThat(chessboard.isExistKey(7, 0)).isTrue();
    }

    @Test
    @DisplayName("존재하는 위치가 아닌경우")
    void isNotExistPosition() {
        Chessboard chessboard = Chessboard.create();

        assertThat(chessboard.isExistKey(5, 0)).isFalse();
    }

    @Test
    @DisplayName("점수 계산 확인")
    void checkScoreSum() {
        Chessboard chessboard = Chessboard.create();

        assertThat(chessboard.computeScore(Color.WHITE)).isEqualTo(38);
    }

    @Test
    @DisplayName("원하는 Position get 확인")
    void checkGetPosition() {
        Chessboard chessboard = Chessboard.create();

        assertThat(chessboard.getPiece(0, 0).getClass()).isEqualTo(Rook.class);
    }
}
