package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    Board board = new Board();

    @ParameterizedTest
    @CsvSource(value = {"a1:Rook", "b1:Knight", "c8:Bishop", "d1:Queen", "e1:King", "a2:Pawn", "a3:Blank"}, delimiter = ':')
    void findPiece(String position, String symbol) {
        assertThat(board.getPiece(Position.from(position)).getClass().getSimpleName()).isEqualTo(symbol);
    }

    @Test
    void movePiece() {
        board.movePiece(Position.from("f2"), Position.from("f4"), Team.WHITE);
        assertThat(board.getPiece(Position.from("f4")).isPawn()).isTrue();
        assertThat(board.getPiece(Position.from("f2")).isBlank()).isTrue();
    }

    @Test
    @DisplayName("같은 팀 말 공격을 시도할 시, 예외가 발생한다.")
    void killSameTeam() {
        Assertions.assertThatThrownBy(() -> board.movePiece(Position.from("e1"), Position.from("f1"), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("같은 팀은 kill 할 수 없습니다.");
    }

    @Test
    @DisplayName("다른 팀 말을 움직일 시, 예외가 발생한다.")
    void otherTeamPieceMove() {
        Assertions.assertThatThrownBy(() -> board.movePiece(Position.from("e8"), Position.from("e7"), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 팀 말을 옮길 수 없습니다.");
    }
}
