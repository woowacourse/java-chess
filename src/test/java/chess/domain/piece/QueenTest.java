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

class QueenTest {
    private static ChessBoard chessBoard;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());
        chessBoard.put(Queen.from("q", D6));
        chessBoard.put(Pawn.from("p", C5));
        chessBoard.put(Bishop.from("B", F4));
        chessBoard.put(Bishop.from("B", D1));
        chessBoard.put(Rook.from("r", H6));
    }

    @DisplayName("이동할 수 있는 방향의 target 위치로 정상 이동한다.")
    @Test
    void moveTargetPosition() {
        Source queen = Source.valueOf(D6, chessBoard);
        queen.move(Target.valueOf(queen, C7, chessBoard), chessBoard);
        assertThat(queen.isSamePosition(C7)).isEqualTo(true);

        queen.move(Target.valueOf(queen, D6, chessBoard), chessBoard);
        queen.move(Target.valueOf(queen, D8, chessBoard), chessBoard);
        assertThat(queen.isSamePosition(D8)).isEqualTo(true);

        queen.move(Target.valueOf(queen, D6, chessBoard), chessBoard);
        queen.move(Target.valueOf(queen, E7, chessBoard), chessBoard);
        assertThat(queen.isSamePosition(E7)).isEqualTo(true);

        queen.move(Target.valueOf(queen, D6, chessBoard), chessBoard);
        queen.move(Target.valueOf(queen, E5, chessBoard), chessBoard);
        assertThat(queen.isSamePosition(E5)).isEqualTo(true);

        queen.move(Target.valueOf(queen, D6, chessBoard), chessBoard);
        queen.move(Target.valueOf(queen, F4, chessBoard), chessBoard);
        assertThat(queen.isSamePosition(F4)).isEqualTo(true);

        queen.move(Target.valueOf(queen, D6, chessBoard), chessBoard);
        queen.move(Target.valueOf(queen, D2, chessBoard), chessBoard);
        assertThat(queen.isSamePosition(D2)).isEqualTo(true);
    }

    @DisplayName("이동할 수 없는 위치인 경우 예외가 발생한다.")
    @Test
    void cannotMoveException() {
        Source queen = Source.valueOf(D6, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            queen.move(Target.valueOf(queen, G5, chessBoard), chessBoard);
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", G5);
        assertThat(queen.isSamePosition(D6)).isEqualTo(true);
    }

    @DisplayName("같은 색깔의 기물 위치인 경우 예외가 발생한다.")
    @Test
    void sameColorException() {
        Source queen = Source.valueOf(D6, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            queen.move(Target.valueOf(queen, H6, chessBoard), chessBoard);
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", H6);
        assertThat(queen.isSamePosition(D6)).isEqualTo(true);
    }

    @DisplayName("장애물이 없는 경우 맵의 오른쪽 위 끝으로 갈 수 있다.")
    @Test
    void moveTargetUpRightPositionException() {
        Source bishop = Source.valueOf(D6, chessBoard);
        bishop.move(Target.valueOf(bishop, F8, chessBoard), chessBoard);
        assertThat(bishop.isSamePosition(F8)).isEqualTo(true);
    }
}
