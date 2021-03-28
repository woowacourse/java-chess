package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.InitializedBoard;
import chess.domain.board.Position;
import chess.domain.exceptions.OverDistanceException;
import chess.domain.exceptions.SameTeamException;
import chess.domain.exceptions.UnableMoveTypeException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PawnMoveStrategyTest {

    Board board;
    Position sourceW;
    Position sourceB;
    Piece pieceW;
    Piece pieceB;

    @BeforeEach
    void setUp() {
        InitializedBoard initializedBoard = new InitializedBoard();
        board = new Board(initializedBoard.board());
        sourceW = Position.of('c', 2);
        sourceB = Position.of('d', 7);
        pieceW = board.pieceAtPosition(sourceW);
        pieceB = board.pieceAtPosition(sourceB);
    }

    @DisplayName("Pawn 초기 움직임 : 두칸 이동")
    @Test
    void initialMove_valid() {
        Position targetW = Position.of('c', 4);
        Position targetB = Position.of('d', 5);

        board.move(sourceW, targetW, PieceColor.WHITE);
        board.move(sourceB, targetB, PieceColor.BLACK);

        Piece movedPieceW = board.pieceAtPosition(targetW);
        Piece movedPieceB = board.pieceAtPosition(targetB);

        assertAll(
            () -> assertEquals(pieceW, movedPieceW),
            () -> assertEquals(pieceB, movedPieceB)
        );
    }

    @DisplayName("Pawn 초기 움직임 : 초기 아닌데 두 칸 이동 시 예외 발생")
    @Test
    void initialMove_invalid_ExceptionThrown() {
        Position newSourceW = Position.of('c', 3);
        Position newSourceB = Position.of('d', 6);
        board.move(sourceW, newSourceW, PieceColor.WHITE);
        board.move(sourceB, newSourceB, PieceColor.BLACK);

        Position targetW = Position.of('c', 5);
        Position targetB = Position.of('d', 4);

        assertAll(
            () -> assertThatThrownBy(() -> board.move(newSourceW, targetW, PieceColor.WHITE))
                .isInstanceOf(OverDistanceException.class),

            () -> assertThatThrownBy(() -> board.move(newSourceB, targetB, PieceColor.BLACK))
                .isInstanceOf(OverDistanceException.class)
        );
    }

    @DisplayName("Pawn 한 칸 움직임 : 앞으로 한 칸")
    @Test
    void moveOne_Valid() {
        Position targetW = Position.of('c', 3);
        Position targetB = Position.of('d', 6);

        board.move(sourceW, targetW, PieceColor.WHITE);
        board.move(sourceB, targetB, PieceColor.BLACK);

        Piece movedPieceW = board.pieceAtPosition(targetW);
        Piece movedPieceB = board.pieceAtPosition(targetB);

        assertAll(
            () -> assertEquals(pieceW, movedPieceW),
            () -> assertEquals(pieceB, movedPieceB)
        );
    }

    @DisplayName("Pawn 한 칸 움직임 : 앞으로 한칸 움직임 실패 Block")
    @Test
    void moveOne_blockByEnemy_ExceptionThrown() {
        Position blockPositionW = Position.of('c', 3);
        Position blockPositionB = Position.of('d', 6);
        Piece blockPieceW = new Piece(PieceKind.PAWN, PieceColor.WHITE);
        Piece blockPieceB = new Piece(PieceKind.PAWN, PieceColor.BLACK);

        board.putPieceAtPosition(blockPositionW, blockPieceB);
        board.putPieceAtPosition(blockPositionB, blockPieceW);

        Position targetW = Position.of('c', 3);
        Position targetB = Position.of('d', 6);

        assertAll(
            () -> assertThatThrownBy(() -> board.move(sourceW, targetW, PieceColor.WHITE))
                .isInstanceOf(UnableMoveTypeException.class),

            () -> assertThatThrownBy(() -> board.move(sourceB, targetB, PieceColor.BLACK))
                .isInstanceOf(UnableMoveTypeException.class)
        );
    }

    @DisplayName("Pawn 거리 초과 : 3칸 이동 시도")
    @Test
    void move_OverMovableDistance_ExceptionThrown() {
        Position targetW = Position.of('c', 5);
        Position targetB = Position.of('d', 4);

        assertAll(
            () -> assertThatThrownBy(() -> board.move(sourceW, targetW, PieceColor.WHITE))
                .isInstanceOf(OverDistanceException.class),

            () -> assertThatThrownBy(() -> board.move(sourceB, targetB, PieceColor.BLACK))
                .isInstanceOf(OverDistanceException.class)
        );
    }

    @DisplayName("Pawn 공격 : 대각선 움직임")
    @Test
    void move_attackEnemy_diagonal() {
        Position attackPositionW = Position.of('c', 6);
        Position attackPositionB = Position.of('d', 3);
        Piece blockPieceW = new Piece(PieceKind.PAWN, PieceColor.WHITE);
        Piece blockPieceB = new Piece(PieceKind.PAWN, PieceColor.BLACK);

        board.putPieceAtPosition(attackPositionW, blockPieceW);
        board.putPieceAtPosition(attackPositionB, blockPieceB);

        Position targetW = Position.of('d', 3);
        Position targetB = Position.of('c', 6);

        board.move(sourceW, targetW, PieceColor.WHITE);
        board.move(sourceB, targetB, PieceColor.BLACK);

        Piece movedPieceW = board.pieceAtPosition(targetW);
        Piece movedPieceB = board.pieceAtPosition(targetB);

        assertAll(
            () -> assertEquals(pieceW, movedPieceW),
            () -> assertEquals(pieceB, movedPieceB)
        );
    }

    @DisplayName("Pawn 대각선 이동 실패")
    @Test
    void move_attackEnemy_ExceptionThrown() {
        Position targetW = Position.of('d', 3);
        Position targetB = Position.of('c', 6);

        assertAll(
            () -> assertThatThrownBy(() -> board.move(sourceW, targetW, PieceColor.WHITE))
                .isInstanceOf(UnableMoveTypeException.class),

            () -> assertThatThrownBy(() -> board.move(sourceB, targetB, PieceColor.BLACK))
                .isInstanceOf(UnableMoveTypeException.class)
        );
    }

    @DisplayName("Pawn 후진 이동 실패")
    @Test
    void moveBackWard_ExceptionThrown() {
        Position newPositionW = Position.of('c', 4);
        Position newPositionB = Position.of('d', 5);
        Piece pieceW = new Piece(PieceKind.PAWN, PieceColor.WHITE);
        Piece pieceB = new Piece(PieceKind.PAWN, PieceColor.BLACK);

        board.putPieceAtPosition(newPositionW, pieceW);
        board.putPieceAtPosition(newPositionB, pieceB);

        Position targetW = Position.of('c', 3);
        Position targetB = Position.of('d', 6);

        assertAll(
            () -> assertThatThrownBy(() -> board.move(newPositionW, targetW, PieceColor.WHITE))
                .isInstanceOf(UnableMoveTypeException.class),

            () -> assertThatThrownBy(() -> board.move(newPositionB, targetB, PieceColor.BLACK))
                .isInstanceOf(UnableMoveTypeException.class)
        );

    }

    @DisplayName("Pawn 같은 색깔 공격 실패")
    @Test
    void move_sameColor_ExceptionThrown() {
        Position attackPositionW = Position.of('c', 6);
        Position attackPositionB = Position.of('d', 3);
        Piece blockPieceW = new Piece(PieceKind.PAWN, PieceColor.WHITE);
        Piece blockPieceB = new Piece(PieceKind.PAWN, PieceColor.BLACK);

        board.putPieceAtPosition(attackPositionW, blockPieceB);
        board.putPieceAtPosition(attackPositionB, blockPieceW);

        Position targetW = Position.of('d', 3);
        Position targetB = Position.of('c', 6);

        assertAll(
            () -> assertThatThrownBy(() -> board.move(sourceW, targetW, PieceColor.WHITE))
                .isInstanceOf(SameTeamException.class),

            () -> assertThatThrownBy(() -> board.move(sourceB, targetB, PieceColor.BLACK))
                .isInstanceOf(SameTeamException.class)
        );
    }
}