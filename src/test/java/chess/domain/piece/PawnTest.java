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

class PawnTest {
    private static ChessBoard chessBoard;

    @BeforeEach
    void init() {
        chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());
        chessBoard.initializeBoard();
    }

    @DisplayName("target 위치로 이동한다.")
    @Test
    void moveTargetPosition() {
        Source pawn = Source.valueOf(A2, chessBoard);
        pawn.move(Target.valueOf(pawn, A3, chessBoard), chessBoard);

        assertThat(pawn.isSamePosition(A3)).isEqualTo(true);
    }

    @DisplayName("target 위치로 이동한다.")
    @Test
    void moveTargetPositionException() {
        Source pawn = Source.valueOf(A2, chessBoard);
        pawn.move(Target.valueOf(pawn, A4, chessBoard), chessBoard);

        assertThat(pawn.isSamePosition(A4)).isEqualTo(true);
    }
}
