package chess.domain.strategy;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import chess.domain.color.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.TestBoardFactory;
import chess.domain.piece.WhiteFirstPawn;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class MoveStrategyTest {

    @TestFactory
    @DisplayName("선택된 말에 따라 전략을 선택한다.")
    Collection<DynamicTest> zz() {
        Map<Position, Piece> board = new TestBoardFactory().getTestBoard(Map.of(
                new Position(4, 4), new King(new Position(4, 4), Color.WHITE),
                new Position(4, 3), new WhiteFirstPawn(new Position(4, 3))
        ));

        return List.of(
                dynamicTest("빈칸을 선택하면 BlankMoveStrategy를 반환한다.", () -> {
                    MoveStrategy moveStrategy = new GeneralMoveStrategy(board);
                    Assertions.assertThat(moveStrategy.changeStrategy(new Position(3, 3)))
                            .isInstanceOf(BlankMoveStrategy.class);
                }),
                dynamicTest("폰을 제외한 기물을 선택하면 GeneralMoveStrategy를 반환한다.", () -> {
                    MoveStrategy moveStrategy = new BlankMoveStrategy(board);
                    Assertions.assertThat(moveStrategy.changeStrategy(new Position(4, 4)))
                            .isInstanceOf(GeneralMoveStrategy.class);
                }),
                dynamicTest("폰을 선택하면 PawnMoveStrategy를 반환한다.", () -> {
                    MoveStrategy moveStrategy = new BlankMoveStrategy(board);
                    Assertions.assertThat(moveStrategy.changeStrategy(new Position(4, 3)))
                            .isInstanceOf(PawnMoveStrategy.class);
                })
        );
    }
}
