package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import chess.domain.BlankBoard;
import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.piece.nonsliding.King;
import chess.domain.piece.nonsliding.Knight;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.sliding.Bishop;
import chess.domain.piece.sliding.Queen;
import chess.domain.piece.sliding.Rook;
import chess.domain.position.Positions;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GeneralChessStateTest {

    @ParameterizedTest(name = "[{index}] 퀸이 (4, 4) 위치에 있을 때 ({0}, {1})로 이동할 수 있다.")
    @CsvSource(value = {"4,8", "8,4", "4,1", "1,4", "1,1", "8,8", "1,7", "7,1"})
    @DisplayName("퀸은 여덟 방향으로 모두 이동할 수 있다.")
    void queenMove(int x, int y) {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 4), new Queen(Color.WHITE)
        ));
        GeneralChessState generalChessStrategy = new GeneralChessState(board);
        generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(x, y)));

        assertThat(generalChessStrategy.collectBoard().get(new Position(x, y)))
                .isEqualTo(PieceType.WHITE_QUEEN);
    }

    @ParameterizedTest(name = "[{index}] 비숍이 (4, 4) 위치에 있을 때 ({0}, {1})로 이동할 수 있다.")
    @CsvSource(value = {"1,1", "8,8", "1,7", "7,1"})
    @DisplayName("비숍은 대각선 네방향으로 모두 이동할 수 있다.")
    void bishopMove(int x, int y) {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 4), new Bishop(Color.WHITE)
        ));
        GeneralChessState generalChessStrategy = new GeneralChessState(board);
        generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(x, y)));

        assertThat(generalChessStrategy.collectBoard().get(new Position(x, y)))
                .isEqualTo(PieceType.WHITE_BISHOP);
    }

    @ParameterizedTest(name = "[{index}] 룩이 (4, 4) 위치에 있을 때 ({0}, {1})로 이동할 수 있다.")
    @CsvSource(value = {"4,8", "8,4", "4,1", "1,4"})
    @DisplayName("룩은 네방향으로 모두 이동할 수 있다.")
    void rookMove(int x, int y) {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 4), new Rook(Color.WHITE)
        ));
        GeneralChessState generalChessStrategy = new GeneralChessState(board);
        generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(x, y)));

        assertThat(generalChessStrategy.collectBoard().get(new Position(x, y)))
                .isEqualTo(PieceType.WHITE_ROOK);
    }

    @ParameterizedTest(name = "[{index}] 나이트가 (4, 4) 위치에 있을 때 ({0}, {1})로 이동할 수 있다.")
    @CsvSource(value = {"5,6", "6,5", "3,6", "6,3", "2,3", "3,2", "2,5", "5,2"})
    @DisplayName("나이트 이동")
    void knightMove(int x, int y) {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 4), new Knight(Color.WHITE)
        ));
        GeneralChessState generalChessStrategy = new GeneralChessState(board);
        generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(x, y)));

        assertThat(generalChessStrategy.collectBoard().get(new Position(x, y)))
                .isEqualTo(PieceType.WHITE_KNIGHT);
    }

    @ParameterizedTest(name = "[{index}] 킹이 (4, 4) 위치에 있을 때 ({0}, {1})로 이동할 수 있다.")
    @CsvSource(value = {"3,3", "3,4", "3,5", "4,5", "5,5", "5,4", "5,3", "4,3"})
    @DisplayName("킹은 여덟 방향으로 한 칸씩만 이동할 수 있다.")
    void kingMove(int x, int y) {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 4), new King(Color.WHITE)
        ));
        GeneralChessState generalChessStrategy = new GeneralChessState(board);
        generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(x, y)));

        assertThat(generalChessStrategy.collectBoard().get(new Position(x, y)))
                .isEqualTo(PieceType.WHITE_KING);
    }

    @Test
    @DisplayName("이동 경로에 다른 말이 있으면 이동할 수 없다.")
    void movePieceWhenHasOtherPiece() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 4), new Queen(Color.WHITE),
                new Position(4, 3), new Queen(Color.BLACK))
        );
        GeneralChessState generalChessStrategy = new GeneralChessState(board);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(4, 1))))
                .withMessage("이동할 수 없는 경로 입니다.");
    }

    @Test
    @DisplayName("도착지에 같은색 말이 있으면 이동할 수 없다.")
    void movePieceWhenHasSameColorPieceInDestination() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 4), new Queen(Color.WHITE),
                new Position(4, 1), new King(Color.WHITE))
        );
        GeneralChessState generalChessStrategy = new GeneralChessState(board);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(4, 1))))
                .withMessage("이동할 수 없는 경로 입니다.");
    }

    @Test
    @DisplayName("도착지에 다른색 말이 있으면 상대 말을 잡고 해당 위치로 이동할 수 있다.")
    void movePieceWhenHasOtherColorPieceInDestination() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 4), new Queen(Color.WHITE),
                new Position(4, 1), new King(Color.BLACK))
        );
        GeneralChessState generalChessStrategy = new GeneralChessState(board);
        generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(4, 1)));

        Assertions.assertAll(
                () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 1))).isEqualTo(
                        PieceType.WHITE_QUEEN),
                () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(PieceType.BLANK),
                () -> assertThat(generalChessStrategy.collectBoard()).doesNotContainValue(PieceType.BLACK_KING)
        );
    }

    /*
    . . . . . . . .
    B . . . . . . .
    . . . . . . . .
    . . . . . . . .
    . . . P . . . .
    . . . . . . . .
    . . . . . . . .
    b . . . . . . .
    */
    @TestFactory
    @DisplayName("비숍 이동 테스트")
    Collection<DynamicTest> moveBishop() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(1, 1), new Bishop(Color.WHITE),
                new Position(4, 4), new BlackPawn(),
                new Position(1, 7), new Bishop(Color.BLACK)
        ));
        GeneralChessState generalChessStrategy = new GeneralChessState(board);

        return List.of(
                /*
                . . . . . . . .
                B . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . b . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("비숍은 오른쪽 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(1, 1), new Position(4, 4)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(
                                    PieceType.WHITE_BISHOP),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(1, 1))).isEqualTo(
                                    PieceType.BLANK)
                    );

                /*
                . . . . . . . .
                b . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                }), dynamicTest("비숍은 왼쪽 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(1, 7)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(1, 7))).isEqualTo(
                                    PieceType.WHITE_BISHOP),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(
                                    PieceType.BLANK)
                    );
                })
        );
    }

    /*
    P . . . . . . P
    . . . . . . . .
    . . . . . . . .
    . . . . . . . .
    . . . . . . . .
    . . . . . . . .
    . . . . . . . .
    r P . . . . . P
    */
    @TestFactory
    @DisplayName("룩 이동 테스트")
    Collection<DynamicTest> moveRook() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(1, 1), new Rook(Color.WHITE),
                new Position(1, 8), new BlackPawn(),
                new Position(8, 8), new BlackPawn(),
                new Position(8, 1), new BlackPawn(),
                new Position(2, 1), new BlackPawn())
        );
        GeneralChessState generalChessStrategy = new GeneralChessState(board);

        return List.of(
                /*
                r . . . . . . P
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . P . . . . . P
                */
                dynamicTest("룩은 위쪽 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(1, 1), new Position(1, 8)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(1, 8))).isEqualTo(
                                    PieceType.WHITE_ROOK),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(1, 1))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . r
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . P . . . . . P
                */
                dynamicTest("룩은 오른쪽 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(1, 8), new Position(8, 8)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(8, 8))).isEqualTo(
                                    PieceType.WHITE_ROOK),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(1, 8))).isEqualTo(
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
                . . . . . . . .
                . P . . . . . r
                */
                dynamicTest("룩은 아래쪽 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(8, 8), new Position(8, 1)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(8, 1))).isEqualTo(
                                    PieceType.WHITE_ROOK),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(8, 8))).isEqualTo(
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
                . . . . . . . .
                . r . . . . . .
                */
                dynamicTest("룩은 왼쪽 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(8, 1), new Position(2, 1)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(2, 1))).isEqualTo(
                                    PieceType.WHITE_ROOK),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(8, 1))).isEqualTo(
                                    PieceType.BLANK)
                    );
                })
        );
    }

    /*
    . . . . . . . .
    . . . . . . . .
    . . . . P P . .
    . . . P . . P .
    . . . P . . P .
    . . . . P . P .
    . . . . q . . .
    . . . . . . . .
    */
    @TestFactory
    @DisplayName("퀸 이동 테스트")
    Collection<DynamicTest> moveQueen() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(5, 2), new Queen(Color.WHITE),
                new Position(7, 4), new BlackPawn(),
                new Position(7, 5), new BlackPawn(),
                new Position(6, 6), new BlackPawn(),
                new Position(5, 6), new BlackPawn(),
                new Position(4, 5), new BlackPawn(),
                new Position(4, 4), new BlackPawn(),
                new Position(5, 3), new BlackPawn(),
                new Position(7, 3), new BlackPawn()
        ));
        GeneralChessState generalChessStrategy = new GeneralChessState(board);

        return List.of(
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . P P . .
                . . . P . . P .
                . . . P . . q .
                . . . . P . P .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("퀸은 오른쪽 대각선 위 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(5, 2), new Position(7, 4)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(7, 4))).isEqualTo(
                                    PieceType.WHITE_QUEEN),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 2))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . P P . .
                . . . P . . q .
                . . . P . . . .
                . . . . P . P .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("퀸은 위 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(7, 4), new Position(7, 5)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(7, 5))).isEqualTo(
                                    PieceType.WHITE_QUEEN),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(7, 4))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . P q . .
                . . . P . . . .
                . . . P . . . .
                . . . . P . P .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("퀸은 왼쪽 대각선 위 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(7, 5), new Position(6, 6)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(6, 6))).isEqualTo(
                                    PieceType.WHITE_QUEEN),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(7, 5))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . q . . .
                . . . P . . . .
                . . . P . . . .
                . . . . P . P .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("퀸은 왼쪽 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(6, 6), new Position(5, 6)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 6))).isEqualTo(
                                    PieceType.WHITE_QUEEN),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(6, 6))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . q . . . .
                . . . P . . . .
                . . . . P . P .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("퀸은 왼쪽 대각선 아래 상대 말을 잡고 이동할 수 있다.", () -> {

                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(5, 6), new Position(4, 5)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 5))).isEqualTo(
                                    PieceType.WHITE_QUEEN),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 6))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . q . . . .
                . . . . P . P .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("퀸은 아래쪽 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 5), new Position(4, 4)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(
                                    PieceType.WHITE_QUEEN),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 5))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . q . P .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("퀸은 오른쪽 대각선 아래쪽 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(5, 3)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 3))).isEqualTo(
                                    PieceType.WHITE_QUEEN),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . q .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("퀸은 오른쪽 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(5, 3), new Position(7, 3)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(7, 3))).isEqualTo(
                                    PieceType.WHITE_QUEEN),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 3))).isEqualTo(
                                    PieceType.BLANK)
                    );
                })
        );
    }

    /*
    . . . n P . . .
    . P . . . . . .
    . . . . . . . .
    P . . . . . . P
    . . . . . . P .
    . P . . . . P .
    . . . P n . . .
    . . . . . . . .
    */
    @TestFactory
    @DisplayName("나이트 이동 테스트")
    Collection<DynamicTest> moveKnight() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(5, 2), new Knight(Color.WHITE),
                new Position(7, 3), new BlackPawn(),
                new Position(8, 5), new BlackPawn(),
                new Position(7, 7), new BlackPawn(),
                new Position(5, 8), new BlackPawn(),
                new Position(4, 8), new Knight(Color.WHITE),
                new Position(1, 5), new BlackPawn(),
                new Position(2, 7), new BlackPawn(),
                new Position(2, 3), new BlackPawn(),
                new Position(4, 2), new BlackPawn()
        ));
        GeneralChessState generalChessStrategy = new GeneralChessState(board);

        return List.of(
                /*
                . . . n P . . .
                . P . . . . P .
                . . . . . . . .
                P . . . . . . P
                . . . . . . . .
                . P . . . . n .
                . . . P . . . .
                . . . . . . . .
                */
                dynamicTest("나이트는 오른쪽으로 한 번 이동하고 오른쪽 위 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(5, 2), new Position(7, 3)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(7, 3))).isEqualTo(
                                    PieceType.WHITE_KNIGHT),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 2))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . n P . . .
                . P . . . . P .
                . . . . . . . .
                P . . . . . . n
                . . . . . . . .
                . P . . . . . .
                . . . P . . . .
                . . . . . . . .
                */
                dynamicTest("나이트는 위쪽으로 한 번 이동하고 오른쪽 위 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(7, 3), new Position(8, 5)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(8, 5))).isEqualTo(
                                    PieceType.WHITE_KNIGHT),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(7, 3))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . n P . . .
                . P . . . . n .
                . . . . . . . .
                P . . . . . . .
                . . . . . . . .
                . P . . . . . .
                . . . P . . . .
                . . . . . . . .
                */
                dynamicTest("나이트는 위쪽으로 한 번 이동하고 왼쪽 위 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(8, 5), new Position(7, 7)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(7, 7))).isEqualTo(
                                    PieceType.WHITE_KNIGHT),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(8, 5))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . n n . . .
                . P . . . . . .
                . . . . . . . .
                P . . . . . . .
                . . . . . . . .
                . P . . . . . .
                . . . P . . . .
                . . . . . . . .
                */
                dynamicTest("나이트는 왼쪽으로 한 번 이동하고 왼쪽 위 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(7, 7), new Position(5, 8)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 8))).isEqualTo(
                                    PieceType.WHITE_KNIGHT),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(7, 7))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . n . . . .
                . n . . . . . .
                . . . . . . . .
                P . . . . . . .
                . . . . . . . .
                . P . . . . . .
                . . . P . . . .
                . . . . . . . .
                */
                dynamicTest("나이트는 왼쪽으로 한 번 이동하고 왼쪽 아래 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 8), new Position(2, 7)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(2, 7))).isEqualTo(
                                    PieceType.WHITE_KNIGHT),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 8))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . n . . . .
                . . . . . . . .
                . . . . . . . .
                n . . . . . . .
                . . . . . . . .
                . P . . . . . .
                . . . P . . . .
                . . . . . . . .
                */
                dynamicTest("나이트는 아래쪽으로 한 번 이동하고 왼쪽 아래 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(2, 7), new Position(1, 5)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(1, 5))).isEqualTo(
                                    PieceType.WHITE_KNIGHT),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(2, 7))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . n . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . n . . . . . .
                . . . P . . . .
                . . . . . . . .
                */
                dynamicTest("나이트는 아래로 한 번 이동하고 오른쪽 아래 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(1, 5), new Position(2, 3)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(2, 3))).isEqualTo(
                                    PieceType.WHITE_KNIGHT),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(1, 5))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . n . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . n . . . .
                . . . . . . . .
                */
                dynamicTest("나이트는 오른쪽로 한 번 이동하고 오른쪽 아래 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(2, 3), new Position(4, 2)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 2))).isEqualTo(
                                    PieceType.WHITE_KNIGHT),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(2, 3))).isEqualTo(
                                    PieceType.BLANK)
                    );
                })
        );
    }

    /*
    . . . . . . . .
    . . . . . . . .
    . . . P P . . .
    . . P . . P . .
    . . P k . P . .
    . . . . P . . .
    . . . . . . . .
    . . . . . . . .
    */
    @TestFactory
    @DisplayName("킹 이동 테스트")
    Collection<DynamicTest> moveKing() {
        Map<Position, Piece> board = new BlankBoard().fillWith(Map.of(
                new Position(4, 4), new King(Color.WHITE),
                new Position(5, 3), new BlackPawn(),
                new Position(6, 4), new BlackPawn(),
                new Position(6, 5), new BlackPawn(),
                new Position(5, 6), new BlackPawn(),
                new Position(4, 6), new BlackPawn(),
                new Position(3, 5), new BlackPawn(),
                new Position(3, 4), new BlackPawn()
        ));
        GeneralChessState generalChessStrategy = new GeneralChessState(board);

        return List.of(
                /*
                . . . . . . . .
                . . . . . . . .
                . . . P P . . .
                . . P . . P . .
                . . P . . P . .
                . . . . k . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("킹은 오른쪽 아래 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 4), new Position(5, 3)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 3))).isEqualTo(
                                    PieceType.WHITE_KING),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 4))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . P P . . .
                . . P . . P . .
                . . P . . k . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("킹은 오른쪽 위 대각선의 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(5, 3), new Position(6, 4)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(6, 4))).isEqualTo(
                                    PieceType.WHITE_KING),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 3))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . P P . . .
                . . P . . k . .
                . . P . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("킹은 위 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(6, 4), new Position(6, 5)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(6, 5))).isEqualTo(
                                    PieceType.WHITE_KING),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(6, 4))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . P k . . .
                . . P . . . . .
                . . P . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("킹은 왼쪽 위 대각선 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(6, 5), new Position(5, 6)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 6))).isEqualTo(
                                    PieceType.WHITE_KING),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(6, 5))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . k . . . .
                . . P . . . . .
                . . P . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("킹은 왼쪽 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(5, 6), new Position(4, 6)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 6))).isEqualTo(
                                    PieceType.WHITE_KING),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(5, 6))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . k . . . . .
                . . P . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("킹은 왼쪽 아래 대각선 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(4, 6), new Position(3, 5)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(3, 5))).isEqualTo(
                                    PieceType.WHITE_KING),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(4, 6))).isEqualTo(
                                    PieceType.BLANK)
                    );
                }),
                /*
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                . . k . . . . .
                . . . . . . . .
                . . . . . . . .
                . . . . . . . .
                */
                dynamicTest("킹은 아래 상대 말을 잡고 이동할 수 있다.", () -> {
                    generalChessStrategy.move(Color.WHITE, new Positions(new Position(3, 5), new Position(3, 4)));
                    Assertions.assertAll(
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(3, 4))).isEqualTo(
                                    PieceType.WHITE_KING),
                            () -> assertThat(generalChessStrategy.collectBoard().get(new Position(3, 5))).isEqualTo(
                                    PieceType.BLANK)
                    );
                })
        );
    }
}
