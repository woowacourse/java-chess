package chess.service;

import chess.domain.ChessGame;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameServiceTest {

    @Test
    void findPreviousGameById() {
        List<Integer> result = ChessGameService.findPreviousGamesById("andole");
        List<Integer> expect = Arrays.asList(1, 2, 3);
        assertThat(result).isEqualTo(expect);
    }
}