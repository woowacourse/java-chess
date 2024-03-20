package chess.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import chess.domain.color.Color;
import chess.domain.piece.Position;
import chess.domain.piece.sliding.Rook;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class ChessGameTest {

    @TestFactory
    @DisplayName("턴은 번갈아 가며 진행된다.")
    Collection<DynamicTest> changeTurnColor() {
        ChessGame chessGame = new ChessGame(new TestBoardFactory().getTestBoard(Map.of(
                new Position(1, 1), new Rook(new Position(1,1), Color.WHITE),
                new Position(8, 8), new Rook(new Position(8,8), Color.BLACK)
        )));

        return List.of(
                dynamicTest("첫 턴에 블랙 말을 움직이면 예외가 발생한다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(() -> chessGame.move(new Position(8, 8), new Position(8, 7)))
                            .withMessage("상대 말은 이동할 수 없습니다.");
                }),
                dynamicTest("첫 턴은 화이트다.", () -> {
                    assertDoesNotThrow(() -> chessGame.move(new Position(1, 1), new Position(1, 2)));
                }),
                dynamicTest("두번째 턴에 화이트 말을 움직이면 예외가 발생한다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(() -> chessGame.move(new Position(1, 2), new Position(1, 3)))
                            .withMessage("상대 말은 이동할 수 없습니다.");
                }),
                dynamicTest("첫 턴이 이상 없이 진행 됐다면 두 번째 턴은 블랙으로 변경된다.", () -> {
                    assertDoesNotThrow(() -> chessGame.move(new Position(8, 8), new Position(8, 7)));
                })
        );
    }
}