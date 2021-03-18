package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.position.Source;
import chess.domain.position.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RookTest {
    private static ChessBoard chessBoard;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());
        chessBoard.put(Rook.from("r", B3));
        chessBoard.put(Pawn.from("P", B6));
        chessBoard.put(Bishop.from("b", E3));
    }

    @DisplayName("target 위치로 이동한다.")
    @Test
    void moveTargetPosition() {
        Source rook = Source.valueOf(B3, chessBoard);
        rook.move(Target.valueOf(rook, D3, chessBoard), chessBoard);
        assertThat(rook.isSamePosition(D3)).isEqualTo(true);

        rook.move(Target.valueOf(rook, B3, chessBoard), chessBoard);
        rook.move(Target.valueOf(rook, A3, chessBoard), chessBoard);
        assertThat(rook.isSamePosition(A3)).isEqualTo(true);

        rook.move(Target.valueOf(rook, B3, chessBoard), chessBoard);
        rook.move(Target.valueOf(rook, B5, chessBoard), chessBoard);
        assertThat(rook.isSamePosition(B5)).isEqualTo(true);

        rook.move(Target.valueOf(rook, B3, chessBoard), chessBoard);
        rook.move(Target.valueOf(rook, B1, chessBoard), chessBoard);
        assertThat(rook.isSamePosition(B1)).isEqualTo(true);
    }

    @DisplayName("이동할 수 없는 위치인 경우 예외가 발생한다.")
    @Test
    void cannotMoveException() {
        Source rook = Source.valueOf(B3, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            rook.move(Target.valueOf(rook, B7, chessBoard), chessBoard);
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", B7);
        assertThat(rook.isSamePosition(B3)).isEqualTo(true);
    }

    @DisplayName("같은 색깔의 기물 위치인 경우 예외가 발생한다.")
    @Test
    void sameColorException() {
        Source rook = Source.valueOf(B3, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            rook.move(Target.valueOf(rook, E3, chessBoard), chessBoard);
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", E3);
        assertThat(rook.isSamePosition(B3)).isEqualTo(true);
    }

    @DisplayName("장애물이 없는 경우 맵의 아래쪽 끝으로 갈 수 있다.")
    @Test
    void moveTargetDownPositionException() {
        Source rook = Source.valueOf(B3, chessBoard);
        rook.move(Target.valueOf(rook, B1, chessBoard), chessBoard);
        assertThat(rook.isSamePosition(B1)).isEqualTo(true);
    }
}
