package chess.domain.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingMoveStrategyTest {

    @DisplayName("킹은 2칸 움직일 수 없습니다.")
    @Test
    public void twoDistance() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        KingMoveStrategy kingMoveStrategy = new KingMoveStrategy();

        Command command = Command.from("move e2 e4");

        chessBoard.move(command);

        //when
        Position source = Position.of(File.E, Rank.ONE);
        Position target = Position.of(File.E, Rank.THREE);

        //then
        assertThatThrownBy(() -> kingMoveStrategy.isMovable(source, target, chessBoard));
    }
}
