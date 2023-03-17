package chess.domain;

import chess.domain.strategy.rook.RookStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    Players players;
    RookStrategy rookStrategy;

    @BeforeEach
    void setUp() {
        Pieces whitePieces = Pieces.createWhitePieces();
        Pieces blackPieces = Pieces.createBlackPieces(whitePieces);
        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);
        players = Players.from(whitePlayer, blackPlayer);
        rookStrategy = new RookStrategy();
    }

    @Test
    @DisplayName("white 플레이어와 black 플레이어의 모든 position 반환한다.")
    void getAllPosition() {
        // when, then
        assertThat(players.getAllPosition()).hasSize(32);
    }

    @DisplayName("입력받은 위치의 포지션에 기물이 존재하는지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"b2,true","b3,false"}, delimiter = ',')
    void isPieceExistsInputPosition(String movablePiece, boolean expected) {
        // given
        char file = movablePiece.charAt(0);
        int rank = Integer.parseInt(String.valueOf(movablePiece.charAt(1)));

        // when, then
        assertThat(players.isPieceExistsInputPosition(file, rank - 1))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("이동 경로에 다른 기물이 존재하면 예외가 발생한다.")
    void validateAlreadyExistPieceMovingRoute() {
        // when, then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> players.validateAlreadyExistPieceMovingRoute(
                        Position.from(0, 'a'),
                        Position.from(2, 'a')
                )
        );
    }

}
