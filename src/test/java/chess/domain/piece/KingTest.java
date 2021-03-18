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

class KingTest {
    private static ChessBoard chessBoard;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());
        chessBoard.put(King.from("k", E4));
        chessBoard.put(Rook.from("R", D5));
        chessBoard.put(Pawn.from("p", E5));
        chessBoard.put(Bishop.from("B", F3));
    }

    @DisplayName("target 위치로 이동한다.")
    @Test
    void moveTargetPosition() {
        Source king = Source.valueOf(E4, chessBoard);
        king.move(Target.valueOf(king, D4, chessBoard), chessBoard);
        assertThat(king.isSamePosition(D4)).isEqualTo(true);

        king.move(Target.valueOf(king, E4, chessBoard), chessBoard);
        king.move(Target.valueOf(king, D3, chessBoard), chessBoard);
        assertThat(king.isSamePosition(D3)).isEqualTo(true);

        king.move(Target.valueOf(king, E4, chessBoard), chessBoard);
        king.move(Target.valueOf(king, F3, chessBoard), chessBoard);
        assertThat(king.isSamePosition(F3)).isEqualTo(true);

        king.move(Target.valueOf(king, E4, chessBoard), chessBoard);
        king.move(Target.valueOf(king, F5, chessBoard), chessBoard);
        assertThat(king.isSamePosition(F5)).isEqualTo(true);
    }

    @DisplayName("이동할 수 없는 위치인 경우 예외가 발생한다.")
    @Test
    void cannotMoveException() {
        Source king = Source.valueOf(E4, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            king.move(Target.valueOf(king, C4, chessBoard), chessBoard);
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", C4);
        assertThat(king.isSamePosition(E4)).isEqualTo(true);
    }

    @DisplayName("같은 색깔의 기물 위치인 경우 예외가 발생한다.")
    @Test
    void sameColorException() {
        Source king = Source.valueOf(E4, chessBoard);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            king.move(Target.valueOf(king, E5, chessBoard), chessBoard);
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", E5);
        assertThat(king.isSamePosition(E4)).isEqualTo(true);
    }
}
