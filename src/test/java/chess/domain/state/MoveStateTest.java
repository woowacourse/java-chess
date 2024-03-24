package chess.domain.state;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import chess.domain.color.Color;
import chess.domain.piece.nonsliding.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.TestBoardFactory;
import chess.domain.piece.pawn.WhiteFirstPawn;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class MoveStateTest {

    @TestFactory
    @DisplayName("선택된 말에 따라 전략을 선택한다.")
    Collection<DynamicTest> changeState() {
        Map<Position, Piece> board = new TestBoardFactory().getTestBoard(Map.of(
                new Position(4, 4), new King(new Position(4, 4), Color.WHITE),
                new Position(4, 3), new WhiteFirstPawn(new Position(4, 3))
        ));

        return List.of(
                dynamicTest("빈칸을 선택하면 BlankMoveState를 반환한다.", () -> {
                    MoveState moveState = new GeneralMoveState(board);
                    Assertions.assertThat(moveState.changeState(new Position(3, 3)))
                            .isInstanceOf(BlankMoveState.class);
                }),
                dynamicTest("폰을 제외한 기물을 선택하면 GeneralMoveState를 반환한다.", () -> {
                    MoveState moveState = new BlankMoveState(board);
                    Assertions.assertThat(moveState.changeState(new Position(4, 4)))
                            .isInstanceOf(GeneralMoveState.class);
                }),
                dynamicTest("폰을 선택하면 PawnMoveState를 반환한다.", () -> {
                    MoveState moveState = new BlankMoveState(board);
                    Assertions.assertThat(moveState.changeState(new Position(4, 3)))
                            .isInstanceOf(PawnMoveState.class);
                })
        );
    }
}
