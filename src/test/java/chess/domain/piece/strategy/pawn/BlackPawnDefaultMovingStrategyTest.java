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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackPawnDefaultMovingStrategyTest {

    @DisplayName("Pawn은 보통의 경우 앞으로 1칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"a6,a5,true", "a5,a4,true", "a6,a4,false", "a6,b5,false", "a6,b4,false"})
    void pawn_1칸_이동_가능하다(String source, String target, boolean expected) {
        BlackPawnDefaultMovingStrategy movingStrategy = new BlackPawnDefaultMovingStrategy();
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();
        setPiece(board, source, new Pawn(Color.BLACK));

        boolean result = movingStrategy.canMove(board, new Position(source), new Position(target));

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("Pawn은 앞에 기물이 존재하는 경우 이동할 수 없다.")
    @Test
    void pawn_이동할_때_기물이_존재하면_이동_불가능하다() {
        BlackPawnDefaultMovingStrategy movingStrategy = new BlackPawnDefaultMovingStrategy();
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();
        setPiece(board, "a6", new Pawn(Color.BLACK));
        setPiece(board, "a5", new Pawn(Color.BLACK));

        boolean result = movingStrategy.canMove(board, new Position("a6"), new Position("a5"));

        assertThat(result).isFalse();
    }
}