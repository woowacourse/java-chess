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

class BlackPawnStartingPointMovingStrategyTest {

    @DisplayName("Black Pawn은 시작점에서 1칸 혹은 2칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"a7,a6,true", "a7,a5,true", "a7,a4,false", "a7,a8,false", "a6,a5,false"})
    void pawn_시작점에서_1칸_2칸_이동_가능하다(String source, String target, boolean expected) {
        PawnMovingStrategy movingStrategy = new BlackPawnStartingPointMovingStrategy();
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();
        setPiece(board, source, new Pawn(Color.BLACK));

        boolean result = movingStrategy.canMove(board, new Position(source), new Position(target));

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("Black Pawn은 시작점에서 2칸 이동할 때 앞에 기물이 존재하면 이동할 수 없다.")
    @Test
    void pawn_시작점에서_2칸_이동할_때_기물이_존재하면_이동_불가능하다() {
        PawnMovingStrategy movingStrategy = new WhitePawnStartingPointMovingStrategy();
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();
        setPiece(board, "a7", new Pawn(Color.BLACK));
        setPiece(board, "a6", new Pawn(Color.BLACK));

        boolean result = movingStrategy.canMove(board, new Position("a7"), new Position("a6"));

        assertThat(result).isFalse();
    }
}