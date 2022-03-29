package chess.domain.piece.strategy;

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

class WhitePawnDefaultMovingStrategyTest {

    @DisplayName("Pawn은 보통의 경우 앞으로 1칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"a3,a4,true", "a4,a5,true", "a3,a5,false", "a3,b4,false", "a3,b5,false"})
    void pawn_1칸_이동_가능하다(String source, String target, boolean expected) {
        WhitePawnDefaultMovingStrategy movingStrategy = new WhitePawnDefaultMovingStrategy();
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();
        setPiece(board, source, new Pawn(Color.WHITE));

        boolean result = movingStrategy.canMove(board, new Position(source), new Position(target));

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("Pawn은 앞에 기물이 존재하는 경우 이동할 수 없다.")
    @Test
    void pawn_이동할_때_기물이_존재하면_이동_불가능하다() {
        WhitePawnDefaultMovingStrategy movingStrategy = new WhitePawnDefaultMovingStrategy();
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();
        setPiece(board, "a3", new Pawn(Color.WHITE));
        setPiece(board, "a4", new Pawn(Color.WHITE));

        boolean result = movingStrategy.canMove(board, new Position("a3"), new Position("a4"));

        assertThat(result).isFalse();
    }
}