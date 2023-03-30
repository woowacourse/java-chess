package chess.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.piece.Pieces;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    Pieces whitePieces;
    Players players;

    @BeforeEach
    void setUp() {
        whitePieces = Pieces.createWhitePieces();
        Pieces blackPieces = Pieces.createBlackPieces();
        Player whitePlayer = Player.fromPlayerWithColorAndPieces(Color.WHITE, whitePieces);
        Player blackPlayer = Player.fromPlayerWithColorAndPieces(Color.BLACK, blackPieces);
        players = Players.of(whitePlayer, blackPlayer, Color.WHITE);
    }

    @Test
    @DisplayName("입력받은 색에 해당하는 pieces를 반환한다.")
    void getPiecesByColor() {
        // when, then
        Assertions.assertThat(players.getPiecesByColor(Color.WHITE).size())
                .isEqualTo(whitePieces.getPieces().size());
    }

    @Test
    @DisplayName("모든 킹이 살아 있지 않다면 false를 반환한다.")
    void everyKingAlive() {
        // when, then
        Assertions.assertThat(players.notEveryKingAlive())
                .isFalse();
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
