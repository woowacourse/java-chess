package chess.domain.player;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.PieceColor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    Players validPlayers;
    Players invalidPlayers;
    List<Player> playerListWithAllColors;
    List<Player> playerListWithOnlyWhite;

    @BeforeEach
    void setUp() {
        playerListWithAllColors = Arrays.asList(
            Player.of(PieceColor.WHITE),
            Player.of(PieceColor.BLACK)
        );
        playerListWithOnlyWhite = Collections.singletonList(
            Player.of(PieceColor.WHITE)
        );
        validPlayers = Players.of(playerListWithAllColors);
        invalidPlayers = Players.of(playerListWithOnlyWhite);
    }

    @DisplayName("Players 객체 생성 : 성공")
    @Test
    void create() {
        assertThatCode(() -> Players.of(playerListWithAllColors)).doesNotThrowAnyException();
    }

    // todo: 여기 테스트 살짝 아쉬움. assertTrue 말고 다른 거 없을까?
    @DisplayName("Players 객체에 주어진 색을 가진 플레이어가 있는 경우 반환한다.")
    @Test
    void currentPlayer_success() {
        assertTrue(validPlayers.currentPlayer(PieceColor.BLACK).isColor(PieceColor.BLACK));
    }

    @DisplayName("Players 객체에 주어진 색을 가진 플레이어가 없는 경우 예외를 발생시킨다.")
    @Test
    void currentPlayer_throwsException() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> invalidPlayers.currentPlayer(PieceColor.BLACK))
            .withMessage("해당 색을 가진 플레이어가 존재하지 않습니다.");
    }
}