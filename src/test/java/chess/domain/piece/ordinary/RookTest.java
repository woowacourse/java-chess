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

class RookTest {
    @Test
    @DisplayName("Rook이 정상적으로 생성된다.")
    void createRook() {
        Rook rook = new Rook(BLACK);

        assertThat(rook.getPieceType()).isEqualTo(PieceType.ROOK);
    }

    @DisplayName("Rook은 연속된 직선으로 이동할 수 있다.")
    @ParameterizedTest(name="square: ({0}, {1})")
    @CsvSource({"3,0", "0, -1"})
    void validMove_success(int fileInterval, int rankInterval) {
        Piece whiteRook = new Rook(WHITE);
        Piece target = new Empty();

        assertThat(whiteRook.isValidMove(fileInterval, rankInterval, target)).isTrue();
    }
@DisplayName("Rook은 연속된 직선으로만 이동할 수 있다. 이 외의 움직임은 예외가 발생한다.")
    @ParameterizedTest(name="square: ({0}, {1})")
    @CsvSource({"1, 2", "-3, 3"})
    void validMove_fail(int fileInterval, int rankInterval) {
        Piece whiteRook = new Rook(WHITE);
        Piece target = new Empty();

        assertThatThrownBy(() -> whiteRook.isValidMove(fileInterval, rankInterval, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("말이 이동할 수 없는 규칙입니다.");
    }
@DisplayName("Rook은 같은 팀 말이 있는 곳으로 이동할 수 없다.")
    @ParameterizedTest(name="{0}")
    @CsvSource({"WHITE", "BLACK"})
    void isValidTeam(Team team) {
        Piece rook = new Rook(team);
        Piece emptyTarget = new Empty();
        Piece sameTeamTarget = new Rook(team);

        assertThat(rook.isValidTeam(emptyTarget)).isTrue();
        assertThat(rook.isValidTeam(sameTeamTarget)).isFalse();
    }
}
