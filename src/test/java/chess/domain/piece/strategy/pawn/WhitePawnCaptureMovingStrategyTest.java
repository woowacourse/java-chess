package chess.domain.piece.strategy.pawn;

import static chess.domain.BoardFixtures.generateEmptyChessBoard;
import static chess.domain.BoardFixtures.setPiece;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WhitePawnCaptureMovingStrategyTest {

    @DisplayName("White Pawn은 위 오른쪽과 왼쪽으로 capture 가능하다.")
    @ParameterizedTest
    @CsvSource({"b2,a3,true", "b2,c3,true", "b2,b3,false", "c2,a3,false", "c3,d4,true"})
    void white_pawn은_위_오른쪽_왼쪽_capture_가능하다(String source, String target, boolean expected) {
        PawnMovingStrategy movingStrategy = new WhitePawnCaptureMovingStrategy();
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();
        setPiece(board, source, new Pawn(Color.WHITE));
        setPiece(board, target, new Pawn(Color.BLACK));

        boolean result = movingStrategy.canMove(board, new Position(source), new Position(target));

        assertThat(result).isEqualTo(expected);
    }
}