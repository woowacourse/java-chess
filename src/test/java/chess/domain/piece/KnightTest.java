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

class KnightTest {
    private static ChessBoard chessBoard;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());
        chessBoard.put(Knight.from("n", D4));
        chessBoard.put(Pawn.from("p", F3));
        chessBoard.put(Bishop.from("b", F5));
        chessBoard.put(Bishop.from("B", C6));
    }

    @DisplayName("이동할 수 있는 방향의 target 위치로 정상 이동한다.")
    @Test
    void moveTargetPosition() {
        Source knight = Source.valueOf(D4, chessBoard);
        knight.move(Target.valueOf(knight, E6, chessBoard), chessBoard);
        assertThat(knight.isSamePosition(E6)).isEqualTo(true);

        knight.move(Target.valueOf(knight, D4, chessBoard), chessBoard);
        knight.move(Target.valueOf(knight, C6, chessBoard), chessBoard);
        assertThat(knight.isSamePosition(C6)).isEqualTo(true);

        knight.move(Target.valueOf(knight, D4, chessBoard), chessBoard);
        knight.move(Target.valueOf(knight, B5, chessBoard), chessBoard);
        assertThat(knight.isSamePosition(B5)).isEqualTo(true);

        knight.move(Target.valueOf(knight, D4, chessBoard), chessBoard);
        knight.move(Target.valueOf(knight, B3, chessBoard), chessBoard);
        assertThat(knight.isSamePosition(B3)).isEqualTo(true);

        knight.move(Target.valueOf(knight, D4, chessBoard), chessBoard);
        knight.move(Target.valueOf(knight, C2, chessBoard), chessBoard);
        assertThat(knight.isSamePosition(C2)).isEqualTo(true);

        knight.move(Target.valueOf(knight, D4, chessBoard), chessBoard);
        knight.move(Target.valueOf(knight, E2, chessBoard), chessBoard);
        assertThat(knight.isSamePosition(E2)).isEqualTo(true);
    }

    @DisplayName("이동할 수 없는 위치인 경우 예외가 발생한다.")
    @Test
    void cannotMoveException() {
        Source queen = Source.valueOf(D4, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            queen.move(Target.valueOf(queen, D6, chessBoard), chessBoard);
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", D6);
        assertThat(queen.isSamePosition(D4)).isEqualTo(true);
    }

    @DisplayName("같은 색깔의 기물 위치인 경우 예외가 발생한다.")
    @Test
    void sameColorException() {
        Source queen = Source.valueOf(D4, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            queen.move(Target.valueOf(queen, F5, chessBoard), chessBoard);
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", F5);
        assertThat(queen.isSamePosition(D4)).isEqualTo(true);
    }
}
