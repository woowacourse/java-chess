package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.position.Source;
import chess.domain.position.Target;
import chess.domain.state.State;
import chess.domain.state.StateFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RookTest {
    private static State white;
    private static State black;

    @BeforeEach
    void init() {
        white = StateFactory.initialization(new Pieces(
                Arrays.asList(
                        Rook.from("r", B3),
                        Bishop.from("b", E3))));
        black = StateFactory.initialization(new Pieces(
                Collections.singletonList(
                        Pawn.from("P", B6))));
    }

    @DisplayName("target 위치로 이동한다.")
    @Test
    void moveTargetPosition() {
        Source rook = Source.valueOf(B3, white);
        rook.move2(Target.valueOf(rook, D3, white), white.pieces(), black.pieces());
        assertThat(rook.isSamePosition(D3)).isEqualTo(true);

        rook.move2(Target.valueOf(rook, B3, white), white.pieces(), black.pieces());
        rook.move2(Target.valueOf(rook, A3, white), white.pieces(), black.pieces());
        assertThat(rook.isSamePosition(A3)).isEqualTo(true);

        rook.move2(Target.valueOf(rook, B3, white), white.pieces(), black.pieces());
        rook.move2(Target.valueOf(rook, B5, white), white.pieces(), black.pieces());
        assertThat(rook.isSamePosition(B5)).isEqualTo(true);

        rook.move2(Target.valueOf(rook, B3, white), white.pieces(), black.pieces());
        rook.move2(Target.valueOf(rook, B1, white), white.pieces(), black.pieces());
        assertThat(rook.isSamePosition(B1)).isEqualTo(true);
    }

    @DisplayName("이동할 수 없는 위치인 경우 예외가 발생한다.")
    @Test
    void cannotMoveException() {
        Source rook = Source.valueOf(B3, white);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            rook.move2(Target.valueOf(rook, F3, white), white.pieces(), black.pieces());
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", F3);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            rook.move2(Target.valueOf(rook, B7, white), white.pieces(), black.pieces());
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", B7);
        assertThat(rook.isSamePosition(B3)).isEqualTo(true);
    }

    @DisplayName("같은 색깔의 기물 위치인 경우 예외가 발생한다.")
    @Test
    void sameColorException() {
        Source rook = Source.valueOf(B3, white);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            rook.move2(Target.valueOf(rook, E3, white), white.pieces(), black.pieces());
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", E3);
        assertThat(rook.isSamePosition(B3)).isEqualTo(true);
    }

    @DisplayName("장애물이 없는 경우 맵의 아래쪽 끝으로 갈 수 있다.")
    @Test
    void moveTargetDownPositionException() {
        Source rook = Source.valueOf(B3, white);
        rook.move2(Target.valueOf(rook, B1, white), white.pieces(), black.pieces());
        assertThat(rook.isSamePosition(B1)).isEqualTo(true);
    }
}
