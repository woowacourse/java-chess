package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    Players players;

    @BeforeEach
    void setUp() {
        Pieces whitePieces = Pieces.createWhitePieces();
        Pieces blackPieces = Pieces.createBlackPieces(whitePieces);
        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);
        players = Players.of(whitePlayer, blackPlayer);
    }

    @Test
    @DisplayName("입력받은 색에 해당하는 pieces를 반환한다.")
    void getPiecesByColor() {
        // when, then
        Assertions.assertThat(players.getPiecesByColor(Color.WHITE).size())
                .isEqualTo(Pieces.createWhitePieces().getPieces().size());
    }

    @Test
    @DisplayName("모든 킹이 살아 있다면 true를 반환한다.")
    void everyKingAlive() {
        // when, then
        Assertions.assertThat(players.everyKingAlive())
                .isTrue();
    }

    @Test
    @DisplayName("킹이 모두 살아있는 상태면 예외를 발생시킨다.")
    void getWinnerColorName() {
        // when, then
        assertThrows(IllegalArgumentException.class, () ->
                players.getWinnerColorName());
    }

    @Test
    @DisplayName("현재 플레이어의 점수를 계산한다.")
    void calculateScore() {
        // when, then
        Assertions.assertThat(players.calculateScore())
                .isEqualTo(38);
    }
}
