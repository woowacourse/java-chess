package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    Players players;

    @BeforeEach
    void setUp() {
        Pieces whitePieces = Pieces.createWhitePieces();
        Pieces blackPieces = Pieces.createBlackPieces(whitePieces);
        players = Players.from(Player.fromWhitePlayer(whitePieces), Player.fromBlackPlayer(blackPieces));
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

}
