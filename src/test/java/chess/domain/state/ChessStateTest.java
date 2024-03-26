package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import chess.domain.BlankBoard;
import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.nonsliding.King;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.sliding.Queen;
import chess.domain.position.Position;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

public class ChessStateTest {

    @TestFactory
    @DisplayName("선택된 말에 따라 전략을 선택한다.")
    Collection<DynamicTest> changeStrategy() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 4), new King(Color.WHITE),
                new Position(4, 3), new WhiteFirstPawn()
        ));

        return List.of(
                dynamicTest("빈칸을 선택하면 BlankChessStrategy를 반환한다.", () -> {
                    ChessState chessState = new GeneralChessState(board);
                    assertThat(chessState.changeStrategy(new Position(3, 3)))
                            .isInstanceOf(BlankChessState.class);
                }),
                dynamicTest("폰을 제외한 기물을 선택하면 GeneralChessStrategy를 반환한다.", () -> {
                    ChessState chessState = new BlankChessState(board);
                    assertThat(chessState.changeStrategy(new Position(4, 4)))
                            .isInstanceOf(GeneralChessState.class);
                }),
                dynamicTest("폰을 선택하면 PawnChessStrategy를 반환한다.", () -> {
                    ChessState chessState = new BlankChessState(board);
                    assertThat(chessState.changeStrategy(new Position(4, 3)))
                            .isInstanceOf(PawnChessState.class);
                })
        );
    }

    @Test
    @DisplayName("현재 보드에 왕이 2개가 아니면 왕이 잡혔다고 알린다.")
    void isKingCaptured() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(1, 5), new King(Color.WHITE)
        ));
        GeneralChessState chessState = new GeneralChessState(board);

        assertThat(chessState.isKingCaptured()).isTrue();
    }
}
