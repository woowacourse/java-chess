package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnMoveStrategyTest {

    @DisplayName("폰은 처음에 2칸 이동가능합니다.")
    @Test
    public void moveTwoDistance() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy();

        //when
        Position source = Position.of(File.A, Rank.TWO);
        Position target = Position.of(File.A, Rank.FOUR);

        //then
        assertDoesNotThrow(() -> pawnMoveStrategy.isMovable(source, target, chessBoard));
    }

    @DisplayName("폰은 처음을 제외하곤 2칸 이동이 불가능합니다.")
    @Test
    public void notMoveTwoDistance() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy();

        Command command = Command.from("move a2 a3");

        chessBoard.move(command);

        //when
        Position source = Position.of(File.A, Rank.THREE);
        Position target = Position.of(File.A, Rank.FIVE);

        //then
        assertThatThrownBy(() -> pawnMoveStrategy.isMovable(source, target, chessBoard));
    }

    @DisplayName("상대 말을 잡는 경우에만 대각선으로 움직일 수 있다.")
    @Test
    public void moveDiagonal() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy();

        chessBoard.move(Command.from("move a2 a4"));
        chessBoard.move(Command.from("move b7 b5"));

        //when
        Position source = Position.of(File.A, Rank.FOUR);
        Position target = Position.of(File.B, Rank.FIVE);

        //then
        assertDoesNotThrow(() -> pawnMoveStrategy.isMovable(source, target, chessBoard));
    }
}
