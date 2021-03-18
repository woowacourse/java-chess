package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.InstanceOfAssertFactories.comparable;

class PawnTest {
    private static ChessBoard chessBoard;

    @BeforeEach
    void init(){
        chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());
    }

    @DisplayName("target 위치로 이동한다.")
    @Test
    void moveTargetPosition() {
        Pawn pawn = Pawn.from("p", A2);
        pawn.move(A3, chessBoard);

        assertThat(pawn.getPosition()).isEqualTo(A3);
    }

    @DisplayName("source에서 선택한 말과 target의 말이 같은 색깔이면 예외가 발생한다.")
    @Test
    void samePieceChoiceException() {
        Piece pawn = chessBoard.findPiece(A2);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            pawn.move(B2, chessBoard);
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", B2);
    }
}
