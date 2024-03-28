package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import chess.domain.BlankBoard;
import chess.domain.BoardFactory;
import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.piece.pawn.BlackFirstPawn;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.position.Positions;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class PawnChessStateTest {

    @Test
    @DisplayName("현재 말의 게임 턴이 아니면 예외가 발생한다.")
    void moveByNotTurnPiece() {
        PawnChessState pawnChessStrategy = new PawnChessState(new BoardFactory().getInitialBoard());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> pawnChessStrategy.move(Color.BLACK, new Positions(new Position(2, 2), new Position(2, 3))))
                .withMessage("상대 말은 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("처음에는 두 칸 이동할 수 있다.")
    void moveByUpUp() {
        PawnChessState pawnChessStrategy = new PawnChessState(new BoardFactory().getInitialBoard());
        pawnChessStrategy.move(Color.WHITE, new Positions(new Position(2, 2), new Position(2, 4)));

        assertAll(
                () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(2, 4))).isEqualTo(
                        PieceType.WHITE_PAWN),
                () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(2, 2))).isEqualTo(PieceType.BLANK)
        );
    }

    @Test
    @DisplayName("폰은 대각선에 상대말이 없으면 이동할 수 없다.")
    void moveByRightUpAndLeftUpBlank() {
        PawnChessState pawnChessStrategy = new PawnChessState(new BoardFactory().getInitialBoard());
        assertAll(
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawnChessStrategy.move(Color.WHITE, new Positions(new Position(2, 2), new Position(3, 3))))
                        .withMessage("이동 할 수 없는 위치입니다."),
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawnChessStrategy.move(Color.WHITE, new Positions(new Position(2, 2), new Position(1, 3))))
                        .withMessage("이동 할 수 없는 위치입니다.")
        );
    }

    /*
    . . . P . . . .
    . . . P . . . .
    . . P . . . . .
    . . . . . . . .
    . . . . . . . .
    . . . . . . . .
    . . . p . . . .
    . . . . . . . .
    */
    @TestFactory
    @DisplayName("흰색폰 이동 테스트")
    Collection<DynamicTest> moveWhitePawn() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 2), new WhiteFirstPawn(),
                new Position(3, 6), new BlackPawn(),
                new Position(4, 7), new BlackPawn(),
                new Position(4, 8), new BlackPawn()
        ));
        PawnChessState pawnChessStrategy = new PawnChessState(board);

        return List.of(
                /*
                . . . P . . . .
                . . . P . . . .
                . . P . . . . .
                . . . . . . . .
                . . . p . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("폰은 가로막혀 있지 않으면 처음에 앞으로 두 칸 갈 수 있다.", () -> {
                    pawnChessStrategy.move(Color.WHITE, new Positions(new Position(4, 2), new Position(4, 4)));
                    Assertions.assertAll(
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(
                                    PieceType.WHITE_PAWN),
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 2))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . P . . . .
                . . . P . . . .
                . . P . . . . .
                . . . . . . . .
                . . . p . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("한번이라도 움직인 폰은 앞으로 두 칸 갈 수 없다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(
                                    () -> pawnChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(4, 6))))
                            .withMessage("이동할 수 없습니다.");
                }),
                /*
                . . . P . . . .
                . . . P . . . .
                . . P . . . . .
                . . . p . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("폰은 가로막혀 있지 않으면 앞으로 한 칸 갈 수 있다.", () -> {
                    pawnChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(4, 5)));
                    Assertions.assertAll(
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 5))).isEqualTo(
                                    PieceType.WHITE_PAWN),
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . P . . . .
                . . . P . . . .
                . . P . . . . .
                . . . p . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("폰은 오른쪽 위 대각선에 상대 말이 없으면 이동할 수 없다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(
                                    () -> pawnChessStrategy.move(Color.WHITE, new Positions(new Position(4, 5), new Position(5, 6))))
                            .withMessage("이동 할 수 없는 위치입니다.");
                }),
                /*
                . . . P . . . .
                . . . P . . . .
                . . p . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("폰은 왼쪽 위에 상대 말을 잡고 이동할 수 있다.", () -> {
                    pawnChessStrategy.move(Color.WHITE, new Positions(new Position(4, 5), new Position(3, 6)));
                    Assertions.assertAll(
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(3, 6))).isEqualTo(
                                    PieceType.WHITE_PAWN),
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 5))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . P . . . .
                . . . P . . . .
                . . p . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("폰은 오른쪽 위 대각선에 상대 말이 없으면 이동할 수 없다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(
                                    () -> pawnChessStrategy.move(Color.WHITE, new Positions(new Position(3, 6), new Position(2, 7))))
                            .withMessage("이동 할 수 없는 위치입니다.");
                }),
                /*
                . . . P . . . .
                . . . p . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("폰은 오른쪽 위에 상대 말을 잡고 이동할 수 있다.", () -> {
                    pawnChessStrategy.move(Color.WHITE, new Positions(new Position(3, 6), new Position(4, 7)));
                    Assertions.assertAll(
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 7))).isEqualTo(
                                    PieceType.WHITE_PAWN),
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(3, 6))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . P . . . .
                . . . p . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("폰은 한 칸 앞에 상대 말이 있으면 이동할 수 없다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(
                                    () -> pawnChessStrategy.move(Color.WHITE, new Positions(new Position(4, 7), new Position(4, 8))))
                            .withMessage("이동 할 수 없는 위치입니다.");
                })
        );
    }

    /*
    . . . . . . . .
    . . . P . . . .
    . . . . . . . .
    . . . . . . . .
    . . . . . . . .
    . . p . . . . .
    . . . p . . . .
    . . . p . . . .
    */
    @TestFactory
    @DisplayName("검은색폰 이동 테스트")
    Collection<DynamicTest> moveBlackPawn() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 7), new BlackFirstPawn(),
                new Position(3, 3), new WhitePawn(),
                new Position(4, 2), new WhitePawn(),
                new Position(4, 1), new WhitePawn()
        ));
        PawnChessState pawnChessStrategy = new PawnChessState(board);

        return List.of(
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . P . . . .
                . . . . . . . .
                . . p . . . . .
                . . . p . . . .
                . . . p . . . .
                */
                dynamicTest("폰은 가로막혀 있지 않으면 처음에 아래로 두 칸 갈 수 있다.", () -> {
                    pawnChessStrategy.move(Color.BLACK, new Positions(new Position(4, 7), new Position(4, 5)));
                    Assertions.assertAll(
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 5))).isEqualTo(
                                    PieceType.BLACK_PAWN),
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 7))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . P . . . .
                . . . . . . . .
                . . p . . . . .
                . . . p . . . .
                . . . p . . . .
                */
                dynamicTest("한번이라도 움직인 폰은 아래로 두 칸 갈 수 없다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(
                                    () -> pawnChessStrategy.move(Color.BLACK, new Positions(new Position(4, 5), new Position(4, 3))))
                            .withMessage("이동할 수 없습니다.");
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . P . . . .
                . . p . . . . .
                . . . p . . . .
                . . . p . . . .
                */
                dynamicTest("폰은 가로막혀 있지 않으면 아래로 한 칸 갈 수 있다.", () -> {
                    pawnChessStrategy.move(Color.BLACK, new Positions(new Position(4, 5), new Position(4, 4)));
                    Assertions.assertAll(
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(
                                    PieceType.BLACK_PAWN),
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 5))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . P . . . .
                . . p . . . . .
                . . . p . . . .
                . . . p . . . .
                */
                dynamicTest("폰은 오른쪽 아래 대각선에 상대 말이 없으면 이동할 수 없다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(
                                    () -> pawnChessStrategy.move(Color.BLACK, new Positions(new Position(4, 4), new Position(5, 3))))
                            .withMessage("이동 할 수 없는 위치입니다.");
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . P . . . . .
                . . . p . . . .
                . . . p . . . .
                */
                dynamicTest("폰은 왼쪽 아래에 상대 말을 잡고 이동할 수 있다.", () -> {
                    pawnChessStrategy.move(Color.BLACK, new Positions(new Position(4, 4), new Position(3, 3)));
                    Assertions.assertAll(
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(3, 3))).isEqualTo(
                                    PieceType.BLACK_PAWN),
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . P . . . . .
                . . . p . . . .
                . . . p . . . .
                */
                dynamicTest("폰은 왼쪽 아래 대각선에 상대 말이 없으면 이동할 수 없다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(
                                    () -> pawnChessStrategy.move(Color.BLACK, new Positions(new Position(3, 3), new Position(2, 2))))
                            .withMessage("이동 할 수 없는 위치입니다.");
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . P . . . .
                . . . p . . . .
                */
                dynamicTest("폰은 오른쪽 아래에 상대 말을 잡고 이동할 수 있다.", () -> {
                    pawnChessStrategy.move(Color.BLACK, new Positions(new Position(3, 3), new Position(4, 2)));
                    Assertions.assertAll(
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(4, 2))).isEqualTo(
                                    PieceType.BLACK_PAWN),
                            () -> assertThat(pawnChessStrategy.collectBoard().get(new Position(3, 3))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . P . . . .
                . . . p . . . .
                */
                dynamicTest("폰은 한 칸 앞에 상대 말이 있으면 이동할 수 없다.", () -> {
                    assertThatIllegalArgumentException()
                            .isThrownBy(
                                    () -> pawnChessStrategy.move(Color.BLACK, new Positions(new Position(4, 2), new Position(4, 1))))
                            .withMessage("이동 할 수 없는 위치입니다.");
                })
        );
    }
}
