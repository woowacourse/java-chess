package chess.domain.piece.pawn;

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

class InitialPawnTest {

    @Test
    @DisplayName("InitialPawn이 정상적으로 생성된다.")
    void createInitialPawn() {
        InitialPawn initialPawn = new InitialPawn(BLACK);

        assertThat(initialPawn.getPieceType()).isEqualTo(PieceType.PAWN);
    }

    @DisplayName("InitialPawn은 앞으로 2칸까지 이동할 수 있다. 상대팀이 위치하는 경우 대각선으로도 이동할 수 있다.")
    @ParameterizedTest(name="sqaure: ({0}, {1})")
    @CsvSource({"0,1", "0,2", "1,1"})
    void validMove_success(int fileInterval, int rankInterval) {
        Piece whiteInitialPawn = new InitialPawn(WHITE);
        Piece target = new InitialPawn(BLACK);

        assertThat(whiteInitialPawn.isValidMove(fileInterval, rankInterval, target)).isTrue();
    }

    @DisplayName("InitialPawn은 앞으로 2칸까지만 이동할 수 있다. 이 외의 움직임은 예외가 발생한다.")
    @ParameterizedTest(name="square: ({0}, {1})")
    @CsvSource({"1, 2", "-3, 3", "1, 0"})
    void validMove_fail(int fileInterval, int rankInterval) {
        Piece whiteInitialPawn = new InitialPawn(WHITE);
        Piece target = new Empty();

        assertThatThrownBy(() -> whiteInitialPawn.isValidMove(fileInterval, rankInterval, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("WhiteInitialPawn은 위로만, BlackInitialPawn은 아래로만 이동할 수 있다.")
    @ParameterizedTest(name="{0}: ({1}, {2})")
    @CsvSource({"WHITE, 0, 1", "BLACK, 0, -1", "WHITE, 0, 2", "BLACK, 0, -2"})
    void validMoveOfWhiteInitialPawn_success(Team team, int fileInterval, int rankInterval) {
        Piece whiteInitialPawn = new InitialPawn(team);
        Piece target = new Empty();

        assertThat(whiteInitialPawn.isValidMove(fileInterval, rankInterval, target)).isTrue();
    }

    @DisplayName("InitialPawn은 같은 팀 말이 있는 곳으로 이동할 수 없다.")
    @ParameterizedTest(name="Team: {0}")
    @CsvSource({"WHITE", "BLACK"})
    void isValidTeam(Team team) {
        Piece initialPawn = new InitialPawn(team);
        Piece emptyTarget = new Empty();
        Piece sameTeamTarget = new InitialPawn(team);

        assertThat(initialPawn.isValidTeam(emptyTarget)).isTrue();
        assertThat(initialPawn.isValidTeam(sameTeamTarget)).isFalse();
    }
}
