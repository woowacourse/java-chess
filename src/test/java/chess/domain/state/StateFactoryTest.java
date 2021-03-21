package chess.domain.state;

import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateFactoryTest {
    @DisplayName("하얀색 피스, 검은색 피스에 따라 상태가 초기화 된다.")
    @Test
    void initialization() {
        Pieces whitePieces = PieceFactory.whitePieces();
        Pieces blackPieces = PieceFactory.blackPieces();

        State white = StateFactory.initialization(whitePieces);
        State black = StateFactory.initialization(blackPieces);

        assertThat(white).isInstanceOf(Running.class);
        assertThat(black).isInstanceOf(Finished.class);
    }
}