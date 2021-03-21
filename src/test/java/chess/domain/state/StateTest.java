package chess.domain.state;

import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import chess.domain.position.Source;
import chess.domain.position.Target;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.*;

class StateTest {
    private static State white;
    private static State black;

    @BeforeEach
    void init() {
        Pieces whitePieces = PieceFactory.whitePieces();
        Pieces blackPieces = PieceFactory.blackPieces();

        white = StateFactory.initialization(whitePieces);
        black = StateFactory.initialization(blackPieces);
    }

    @DisplayName("말이 움직여지면, 턴이 변경된다.")
    @Test
    void move() {
        Source source = Source.valueOf(A2, white);
        Target target = Target.valueOf(source, A3, white);

        State whiteAfterMove = white.move(source, target);

        assertThat(whiteAfterMove).isInstanceOf(Finished.class);
    }
}
