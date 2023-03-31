package chess.domain.piece.pawn;

import chess.domain.Team;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.square.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PawnTest {

    @Test
    @DisplayName("Pawn이 정상적으로 생성된다.")
    void createPawn() {
        Pawn pawn = new Pawn(BLACK);

        assertThat(pawn.getPieceType()).isEqualTo(PieceType.PAWN);
    }

    @DisplayName("Pawn은 앞으로 1칸만 이동할 수 있다. pawn의 공격은 대각선으로만 가능하다.")
    @ParameterizedTest(name = "sqaure: ({0}, {1})")
    @CsvSource({"0,1,EMPTY", "1,1, BLACK"})
    void validMove_success(int fileInterval, int rankInterval, Team targetTeam) {
        Piece whitePawn = new Pawn(WHITE);
        Piece target = new Pawn(targetTeam);

        assertThat(whitePawn.isValidMove(fileInterval, rankInterval, target)).isTrue();
    }

    @DisplayName("Pawn은 앞으로 1칸만 이동할 수 있다. 이 외의 움직임은 예외가 발생한다.")
    @ParameterizedTest(name = "sqaure: ({0}, {1})")
    @CsvSource({"1, 2", "-3, 3", "1, 0"})
    void validMove_fail(int fileInterval, int rankInterval) {
        Piece whitePawn = new Pawn(WHITE);
        Piece target = new Empty();

        assertThatThrownBy(() -> whitePawn.isValidMove(fileInterval, rankInterval, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("WhitePawn은 위로만, BlackPawn은 아래로만 이동할 수 있다.")
    @ParameterizedTest(name = "{0}: ({1}, {2})")
    @CsvSource({"WHITE, 0, 1", "BLACK, 0, -1"})
    void validMoveOfWhitePawn_success(Team team, int fileInterval, int rankInterval) {
        Piece whitePawn = new Pawn(team);
        Piece target = new Empty();

        assertThat(whitePawn.isValidMove(fileInterval, rankInterval, target)).isTrue();
    }

    @DisplayName("초기 폰은 최대 2칸까지 이동할 수 있다.")
    @ParameterizedTest(name = "{0}, ({1}, {2})")
    @CsvSource({"SEVEN, 0, 2", "SEVEN, 0, 1", "TWO, 0, 2", "TWO, 0, -2"})
    void validMoveOfInitialPawn_success(Rank rank, int fileInterval, int rankInterval) {
        Piece whiteInitialPawn = new Pawn(WHITE);

        assertThat(whiteInitialPawn.isValidPawnMove(rank, fileInterval, rankInterval)).isTrue();
    }

    @DisplayName("폰은 최대 1칸까지 이동할 수 있다.")
    @ParameterizedTest(name = "{0}, ({1}, {2})")
    @CsvSource({"ONE, 0, 2", "THREE, 0, 2", "FOUR, 0, 2", "SIX, 0, -2"})
    void validMoveOfInitialPawn_fail(Rank rank, int fileInterval, int rankInterval) {
        Piece whiteInitialPawn = new Pawn(WHITE);

        assertThatThrownBy(() -> whiteInitialPawn.isValidPawnMove(rank, fileInterval, rankInterval))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Pawn은 같은 팀 말이 있는 곳으로 이동할 수 없다.")
    @ParameterizedTest(name = "Team: {0}")
    @CsvSource({"WHITE", "BLACK"})
    void isValidTeam(Team team) {
        Piece pawn = new Pawn(team);
        Piece emptyTarget = new Empty();
        Piece sameTeamTarget = new Pawn(team);

        assertThat(pawn.isValidTeam(emptyTarget)).isTrue();
        assertThat(pawn.isValidTeam(sameTeamTarget)).isFalse();
    }
}
