package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopMoveStrategyTest {

    @DisplayName("비숍은 대각선으로만 움직일 수 있다.")
    @Test
    public void straight() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        BishopMoveStrategy bishopMoveStrategy = new BishopMoveStrategy();

        Command command = Command.from("move c2 c3");

        chessBoard.move(command);

        //when
        Position source = Position.of(File.C, Rank.ONE);
        Position target = Position.of(File.C, Rank.TWO);

        //then
        assertThatThrownBy(() -> bishopMoveStrategy.isMovable(source, target, chessBoard));
    }

    @DisplayName("비숍은 말을 뛰어 넘어 움직일 수 없다.")
    @Test
    public void passPiece() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        BishopMoveStrategy bishopMoveStrategy = new BishopMoveStrategy();

        //when
        Position source = Position.of(File.C, Rank.ONE);
        Position target = Position.of(File.A, Rank.THREE);

        //then
        assertThatThrownBy(() -> bishopMoveStrategy.isMovable(source, target, chessBoard));
    }

    @DisplayName("비숍은 수평으로 움직일 수 없다.")
    @Test
    public void side() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        BishopMoveStrategy bishopMoveStrategy = new BishopMoveStrategy();

        chessBoard.move(Command.from("move b2 b4"));
        chessBoard.move(Command.from("move c1 a3"));

        //when
        Position source = Position.of(File.A, Rank.THREE);
        Position target = Position.of(File.B, Rank.THREE);

        //then
        assertThatThrownBy(() -> bishopMoveStrategy.isMovable(source, target, chessBoard));
    }
}
