package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightMoveStrategyTest {

    @DisplayName("대각선으로만 움직일 수 없습니다.")
    @Test
    public void diagonal() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        KnightMoveStrategy knightMoveStrategy = new KnightMoveStrategy();

        Command command = Command.from("move a2 a3");

        chessBoard.move(command);

        //when
        Position source = Position.of(File.B, Rank.ONE);
        Position target = Position.of(File.A, Rank.TWO);

        //then
        assertThatThrownBy(() -> knightMoveStrategy.isMovable(source, target, chessBoard));
    }

    @DisplayName("말은 기물을 뛰어넘을 수 있습니다.")
    @Test
    public void passPiece() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        KnightMoveStrategy knightMoveStrategy = new KnightMoveStrategy();

        //when
        Position source = Position.of(File.B, Rank.ONE);
        Position target = Position.of(File.A, Rank.THREE);

        //then
        Assertions.assertDoesNotThrow(() -> knightMoveStrategy.isMovable(source, target, chessBoard));
    }
}
