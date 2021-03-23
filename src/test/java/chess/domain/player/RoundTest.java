package chess.domain.player;

import chess.domain.command.CommandFactory;
import chess.domain.piece.PieceFactory;
import chess.domain.state.StateFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoundTest {
    private Round round;

    @BeforeEach
    void setUp() {
        round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
                StateFactory.initialization(PieceFactory.blackPieces()),
                CommandFactory.initialCommand("start"));
    }


    @DisplayName("Round 객체를 생성한다.")
    @Test
    void createRound() {
        assertThat(round).isInstanceOf(Round.class);
    }
}
