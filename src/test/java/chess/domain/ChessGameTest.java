package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import chess.domain.color.Color;
import chess.domain.piece.nonsliding.King;
import chess.domain.piece.nonsliding.Knight;
import chess.domain.piece.pawn.BlackFirstPawn;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.sliding.Bishop;
import chess.domain.piece.sliding.Queen;
import chess.domain.piece.sliding.Rook;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.score.Score;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class ChessGameTest {

    @TestFactory
    @DisplayName("턴은 번갈아 가며 진행된다.")
    Collection<DynamicTest> changeTurnColor() {
        ChessGame chessGame = new ChessGame(new BlankBoard().fillWith(Map.of(
                new Position(1, 1), new Rook(Color.WHITE),
                new Position(8, 8), new Rook(Color.BLACK)
        )));

        return List.of(
                dynamicTest("첫 턴에 블랙 말을 움직이면 예외가 발생한다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(() -> chessGame.move(new Positions(new Position(8, 8), new Position(8, 7))))
                            .withMessage("상대 말은 이동할 수 없습니다.");
                }),
                dynamicTest("첫 턴은 화이트다.", () -> {
                    assertDoesNotThrow(() -> chessGame.move(new Positions(new Position(1, 1), new Position(1, 2))));
                }),
                dynamicTest("두번째 턴에 화이트 말을 움직이면 예외가 발생한다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(() -> chessGame.move(new Positions(new Position(1, 2), new Position(1, 3))))
                            .withMessage("상대 말은 이동할 수 없습니다.");
                }),
                dynamicTest("첫 턴이 이상 없이 진행 됐다면 두 번째 턴은 블랙으로 변경된다.", () -> {
                    assertDoesNotThrow(() -> chessGame.move(new Positions(new Position(8, 8), new Position(8, 7))));
                })
        );
    }

    @Test
    @DisplayName("각 색깔별 말의 총 점수를 구한다.")
    void calculateScores() {
        ChessGame chessGame = new ChessGame(new BlankBoard().fillWith(Map.of(
                // White
                new Position(1, 1), new Rook(Color.WHITE), // 5점
                new Position(2, 1), new Knight(Color.WHITE), // 2.5점
                new Position(3, 1), new Bishop(Color.WHITE), // 3점
                new Position(1, 2), new WhiteFirstPawn(), // 1점
                // Black
                new Position(4, 8), new Queen(Color.BLACK), // 9점
                new Position(5, 8), new King(Color.BLACK), // 0점
                new Position(1, 7), new BlackFirstPawn() // 1점
        )));

        assertAll(
                () -> assertThat(chessGame.calculateScores().getWhiteScore()).isEqualTo(new Score(11.5)),
                () -> assertThat(chessGame.calculateScores().getBlackScore()).isEqualTo(new Score(10))
        );
    }
}
