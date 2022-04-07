package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookMoveStrategyTest {

    @DisplayName("기물을 뛰어넘을 수 없습니다.")
    @Test
    public void passPiece() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        RookMoveStrategy rookMoveStrategy = new RookMoveStrategy();

        //when
        Position source = Position.of(File.A, Rank.ONE);
        Position target = Position.of(File.A, Rank.THREE);

        //then
        assertThatThrownBy(() -> rookMoveStrategy.isMovable(source, target, chessBoard));
    }

    @DisplayName("대각선으로 움직일 수 없습니다.")
    @Test
    public void diagonal() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        RookMoveStrategy rookMoveStrategy = new RookMoveStrategy();

        chessBoard.move(Command.from("move b2 b4"));

        //when
        Position source = Position.of(File.A, Rank.ONE);
        Position target = Position.of(File.C, Rank.THREE);

        //then
        assertThatThrownBy(() -> rookMoveStrategy.isMovable(source, target, chessBoard));
    }
}
