package chess.domain.piece.ordinary;

import chess.domain.Team;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BishopTest {

    @Test
    @DisplayName("Bishop이 정상적으로 생성된다.")
    void createBishop() {
        Bishop bishop = new Bishop(BLACK);

        assertThat(bishop.getPieceType()).isEqualTo(PieceType.BISHOP);
    }

    @ParameterizedTest(name="Bishop은 연속된 대각선으로 이동할 수 있다.")
    @CsvSource({"2,-2", "1,-1"})
    void validMove_success(int fileInterval, int rankInterval) {
        Piece whiteBishop = new Bishop(WHITE);
        Piece target = new Empty();

        assertThat(whiteBishop.isValidMove(fileInterval, rankInterval, target)).isTrue();
    }

    @ParameterizedTest(name="Bishop은 연속된 대각선으로만 이동할 수 있다. 이 외의 움직임은 예외가 발생한다.")
    @CsvSource({"1, 2", "1, 0"})
    void validMove_fail(int fileInterval, int rankInterval) {
        Piece whiteBishop = new Bishop(WHITE);
        Piece target = new Empty();

        assertThatThrownBy(() -> whiteBishop.isValidMove(fileInterval, rankInterval, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("말이 이동할 수 없는 규칙입니다.");
    }

    @ParameterizedTest(name="Bishop은 같은 팀 말이 있는 곳으로 이동할 수 없다.")
    @CsvSource({"WHITE", "BLACK"})
    void isValidTeam(Team team) {
        Piece bishop = new Bishop(team);
        Piece emptyTarget = new Empty();
        Piece sameTeamTarget = new Bishop(team);

        assertThat(bishop.isValidTeam(emptyTarget)).isTrue();
        assertThat(bishop.isValidTeam(sameTeamTarget)).isFalse();
    }
}
