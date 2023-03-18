package chess.domain.state;

import chess.TestPiecesGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class ChessStateTest {

    @Test
    @DisplayName("체스 상태 초기 생성시 ready 상태로 생성")
    void starting_chess_state_class_test() {
        final ChessState state = ChessState.start(new TestPiecesGenerator(Collections.emptyList()));

        assertThat(state).isInstanceOf(ChessReady.class);
    }

}
