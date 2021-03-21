package chess.domain.state;

import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import chess.domain.position.Source;
import chess.domain.position.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        State whiteAfterMove = white.move(source, target, black);

        assertThat(whiteAfterMove).isInstanceOf(Finished.class);
    }

    @DisplayName("말이 움직여지면, 턴이 변경되고, 그상태에서 움직이려 하면 에러가 발생한다.")
    @Test
    void turnOverMove() {
        Source source = Source.valueOf(A2, white);
        Target target = Target.valueOf(source, A3, white);
        State whiteAfterMove = white.move(source, target, black);
        assertThat(whiteAfterMove).isInstanceOf(Finished.class);

        Source secondSource = Source.valueOf(A3, white);
        Target secondTarget = Target.valueOf(source, A4, white);

        assertThatThrownBy(() ->
                whiteAfterMove.move(
                        secondSource, secondTarget, black
                )
        ).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("끝난 상태에서는 기물을 움직일 수 없습니다.");
    }

    @DisplayName("말이 움직여지면, 턴이 변경되고, 상대가 움직인다.")
    @Test
    void turnChange() {
        Source source = Source.valueOf(A2, white);
        Target target = Target.valueOf(source, A3, white);
        State whiteAfterMove = white.move(source, target, black);
        assertThat(whiteAfterMove).isInstanceOf(Finished.class);

        Source blackSource = Source.valueOf(A7, black);
        Target blackTarget = Target.valueOf(source, A5, black);

        black = black.toRunningState(whiteAfterMove);

        State blackState = black.move(blackSource, blackTarget, whiteAfterMove);
        assertThat(blackState.findPiece(A5)).isNotEmpty();
    }

    @DisplayName("자기 차례가 아닐 때 움직이려하면 예외가 발생한다.")
    @Test
    void turnNotOverException() {
        Source source = Source.valueOf(A7, black);
        Target target = Target.valueOf(source, A5, black);

        assertThatThrownBy(() ->
                black.move(
                        source, target, white
                )
        ).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("끝난 상태에서는 기물을 움직일 수 없습니다.");
    }
}
