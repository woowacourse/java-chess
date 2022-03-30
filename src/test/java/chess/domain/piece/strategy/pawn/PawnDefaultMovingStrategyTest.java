package chess.domain.piece.strategy.pawn;

import static chess.domain.BoardFixtures.generateEmptyChessBoard;
import static chess.domain.BoardFixtures.setPiece;
import static chess.domain.position.Direction.TOP;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnDefaultMovingStrategyTest {

    @DisplayName("Pawn은 보통의 경우 앞으로 1칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"a6,a5,true", "a5,a4,true", "a6,a4,false", "a6,b5,false", "a6,b4,false"})
    void pawn_1칸_이동_가능하다(String source, String target, boolean expected) {
        MovingStrategy movingStrategy = new PawnDefaultMovingStrategy(Direction.BOTTOM);
        ChessBoard chessBoard = generateEmptyChessBoard();
        setPiece(chessBoard, source, new Pawn(Color.BLACK));

        boolean result = movingStrategy.canMove(chessBoard.getBoard(), new Position(source), new Position(target));

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("Pawn은 앞에 기물이 존재하는 경우 이동할 수 없다.")
    @Test
    void pawn_이동할_때_기물이_존재하면_이동_불가능하다() {
        MovingStrategy movingStrategy = new PawnDefaultMovingStrategy(Direction.BOTTOM);
        ChessBoard chessBoard = generateEmptyChessBoard();

        setPiece(chessBoard, "a6", new Pawn(Color.BLACK));
        setPiece(chessBoard, "a5", new Pawn(Color.BLACK));

        boolean result = movingStrategy.canMove(chessBoard.getBoard(), new Position("a6"), new Position("a5"));

        assertThat(result).isFalse();
    }

    @DisplayName("Pawn은 보통의 경우 앞으로 1칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"a3,a4,true", "a4,a5,true", "a3,a5,false", "a3,b4,false", "a3,b5,false"})
    void white_pawn_1칸_이동_가능하다(String source, String target, boolean expected) {
        MovingStrategy movingStrategy = new PawnDefaultMovingStrategy(TOP);
        ChessBoard chessBoard = generateEmptyChessBoard();
        setPiece(chessBoard, source, new Pawn(Color.WHITE));

        boolean result = movingStrategy.canMove(chessBoard.getBoard(), new Position(source), new Position(target));

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("White Pawn은 앞에 기물이 존재하는 경우 이동할 수 없다.")
    @Test
    void white_pawn_이동할_때_기물이_존재하면_이동_불가능하다() {
        MovingStrategy movingStrategy = new PawnDefaultMovingStrategy(TOP);
        ChessBoard chessBoard = generateEmptyChessBoard();
        setPiece(chessBoard, "a3", new Pawn(Color.WHITE));
        setPiece(chessBoard, "a4", new Pawn(Color.WHITE));

        boolean result = movingStrategy.canMove(chessBoard.getBoard(), new Position("a3"), new Position("a4"));

        assertThat(result).isFalse();
    }
}