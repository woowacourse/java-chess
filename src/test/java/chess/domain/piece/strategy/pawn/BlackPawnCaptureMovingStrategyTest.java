package chess.domain.piece.strategy.pawn;

import static chess.domain.BoardFixtures.generateEmptyChessBoard;
import static chess.domain.BoardFixtures.setPiece;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackPawnCaptureMovingStrategyTest {

    @DisplayName("Black Pawn은 아래 오른쪽과 왼쪽으로 capture 가능하다.")
    @ParameterizedTest
    @CsvSource({"b7,a6,true", "b7,c6,true", "b7,b6,false", "c7,a6,false", "d6,c5,true"})
    void black_pawn은_아래_오른쪽_왼쪽_capture_가능하다(String source, String target, boolean expected) {
        MovingStrategy movingStrategy = new BlackPawnCaptureMovingStrategy();
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();
        setPiece(board, source, new Pawn(Color.BLACK));
        setPiece(board, target, new Pawn(Color.WHITE));

        boolean result = movingStrategy.canMove(board, new Position(source), new Position(target));

        assertThat(result).isEqualTo(expected);
    }
}