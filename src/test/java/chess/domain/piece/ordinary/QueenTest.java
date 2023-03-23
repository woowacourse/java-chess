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

class QueenTest {
    @Test
    @DisplayName("Queen이 정상적으로 생성된다.")
    void createQueen() {
        Queen queen = new Queen(BLACK);

        assertThat(queen.getPieceType()).isEqualTo(PieceType.QUEEN);
    }

    @ParameterizedTest(name="Queen은 연속된 모든 방향으로 이동할 수 있다.")
    @CsvSource({"2,-2", "1,1", "3, 0"})
    void validMove_success(int fileInterval, int rankInterval) {
        Piece whiteQueen = new Queen(WHITE);
        Piece target = new Empty();

        assertThat(whiteQueen.isValidMove(fileInterval, rankInterval, target)).isTrue();
    }

    @ParameterizedTest(name="Queen은 연속된 모든 방향으로 이동할 수 있다. 이 외의 움직임은 예외가 발생한다.")
    @CsvSource({"1, 2", "-2, 1"})
    void validMove_fail(int fileInterval, int rankInterval) {
        Piece whiteQueen = new Queen(WHITE);
        Piece target = new Empty();

        assertThatThrownBy(() -> whiteQueen.isValidMove(fileInterval, rankInterval, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("말이 이동할 수 없는 규칙입니다.");
    }

    @ParameterizedTest(name="Queen은 같은 팀 말이 있는 곳으로 이동할 수 없다.")
    @CsvSource({"WHITE", "BLACK"})
    void isValidTeam(Team team) {
        Piece queen = new Queen(team);
        Piece emptyTarget = new Empty();
        Piece sameTeamTarget = new Queen(team);

        assertThat(queen.isValidTeam(emptyTarget)).isTrue();
        assertThat(queen.isValidTeam(sameTeamTarget)).isFalse();
    }
}
