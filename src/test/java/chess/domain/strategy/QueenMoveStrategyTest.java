package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenMoveStrategyTest {

    @DisplayName("퀸은 기물을 뛰어 넘을 수 없습니다.")
    @Test
    public void passPiece() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        QueenMoveStrategy queenMoveStrategy = new QueenMoveStrategy();

        //when
        Position source = Position.of(File.D, Rank.ONE);
        Position target = Position.of(File.D, Rank.THREE);

        //then
        assertThatThrownBy(() -> queenMoveStrategy.isMovable(source, target, chessBoard));
    }

    @DisplayName("퀸은 나이트처럼 움직일 수 없습니다.")
    @Test
    public void moveLikeKnight() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        QueenMoveStrategy queenMoveStrategy = new QueenMoveStrategy();

        //when
        Position source = Position.of(File.D, Rank.ONE);
        Position target = Position.of(File.C, Rank.THREE);

        //then
        assertThatThrownBy(() -> queenMoveStrategy.isMovable(source, target, chessBoard));
    }
}
