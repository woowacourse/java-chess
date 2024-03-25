package domain.game;

import static domain.Fixture.Pieces.*;
import static domain.Fixture.Positions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessGameTest {
    @Test
    @DisplayName("게임은 흰색 팀의 차례로 시작한다.")
    void initialTeamEqualsWhiteTest() {
        // Given
        ChessGame chessGame = new ChessGame();

        // When
        TeamColor currentTeam = chessGame.currentPlayingTeam();

        // Then
        assertThat(currentTeam).isEqualTo(TeamColor.WHITE);
    }

    @Test
    @DisplayName("한쪽 팀이 기물을 이동하면 다음 팀으로 차례가 넘어간다.")
    void toggleTeamTest() {
        // Given
        ChessGame chessGame = new ChessGame();

        // When
        chessGame.move(A2, A4);

        // Then
        assertThat(chessGame.currentPlayingTeam()).isEqualTo(TeamColor.BLACK);
    }
}
