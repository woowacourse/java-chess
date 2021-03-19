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

class BishopTest {
    private static ChessBoard chessBoard;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());
        chessBoard.put(Bishop.from("b", D6));
        chessBoard.put(Pawn.from("p", C5));
        chessBoard.put(Queen.from("Q", F4));
    }

    @DisplayName("이동할 수 있는 방향의 target 위치로 정상 이동한다.")
    @Test
    void moveTargetPosition() {
        Source bishop = Source.valueOf(D6, chessBoard);
        bishop.move(Target.valueOf(bishop, C7, chessBoard), chessBoard);
        assertThat(bishop.isSamePosition(C7)).isEqualTo(true);

        bishop.move(Target.valueOf(bishop, D6, chessBoard), chessBoard);
        bishop.move(Target.valueOf(bishop, E7, chessBoard), chessBoard);
        assertThat(bishop.isSamePosition(E7)).isEqualTo(true);

        bishop.move(Target.valueOf(bishop, D6, chessBoard), chessBoard);
        bishop.move(Target.valueOf(bishop, E5, chessBoard), chessBoard);
        assertThat(bishop.isSamePosition(E5)).isEqualTo(true);
    }

    @DisplayName("이동할 수 없는 위치인 경우 예외가 발생한다.")
    @Test
    void cannotMoveException() {
        Source bishop = Source.valueOf(D6, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            bishop.move(Target.valueOf(bishop, B7, chessBoard), chessBoard);
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", B7);
        assertThat(bishop.isSamePosition(D6)).isEqualTo(true);
    }

    @DisplayName("같은 색깔의 기물 위치인 경우 예외가 발생한다.")
    @Test
    void sameColorException() {
        Source bishop = Source.valueOf(D6, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            bishop.move(Target.valueOf(bishop, C5, chessBoard), chessBoard);
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", C5);
        assertThat(bishop.isSamePosition(D6)).isEqualTo(true);
    }

    @DisplayName("장애물이 없는 경우 맵의 오른쪽 위 끝으로 갈 수 있다.")
    @Test
    void moveTargetDownPositionException() {
        Source bishop = Source.valueOf(D6, chessBoard);
        bishop.move(Target.valueOf(bishop, F8, chessBoard), chessBoard);
        assertThat(bishop.isSamePosition(F8)).isEqualTo(true);
    }
}