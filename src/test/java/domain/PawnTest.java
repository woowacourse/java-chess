package domain;

import static domain.PieceMoveResult.FAILURE;
import static domain.PieceMoveResult.SUCCESS;
import static domain.Position.A2;
import static domain.Position.A3;
import static domain.Position.A4;
import static domain.Position.A5;
import static domain.Position.A6;
import static domain.Position.A7;
import static domain.Team.BLACK;
import static domain.Team.WHITE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("한칸 전진 할 수 있는지 검증")
    void moveOneSuccess() {
        Pawn pawnWhite = new Pawn(A2, WHITE);
        Pawn pawnBlack = new Pawn(A7, BLACK);
        PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard() {
        };
        PieceMoveResult actualWhite = pawnWhite.move(A3, piecesOnChessBoard);
        PieceMoveResult actualBlack = pawnBlack.move(A6, piecesOnChessBoard);
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(actualWhite).isEqualTo(SUCCESS),
                () -> Assertions.assertThat(actualBlack).isEqualTo(SUCCESS)
        );
    }

    @Test
    @DisplayName("처음에 두칸 전진 할 수 있는지 검증")
    void moveTwoSuccess() {
        Pawn pawnWhite = new Pawn(A2, WHITE);
        Pawn pawnBlack = new Pawn(A7, BLACK);
        PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard() {
        };
        PieceMoveResult actualWhite = pawnWhite.move(A4, piecesOnChessBoard);
        PieceMoveResult actualBlack = pawnBlack.move(A5, piecesOnChessBoard);
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(actualWhite).isEqualTo(SUCCESS),
                () -> Assertions.assertThat(actualBlack).isEqualTo(SUCCESS)
        );
    }

    @Test
    @DisplayName("처음이 아닐 땐 두칸 전진 할 수 없는지 검증")
    void moveTwoFail() {
        Pawn pawnWhite = new Pawn(A2, WHITE);
        Pawn pawnBlack = new Pawn(A7, BLACK);
        PiecesOnChessBoard piecesOnChessBoard = new PiecesOnChessBoard() {
        };
        pawnWhite.move(A3, piecesOnChessBoard);
        pawnBlack.move(A6, piecesOnChessBoard);
        PieceMoveResult actualWhite = pawnWhite.move(A5, piecesOnChessBoard);
        PieceMoveResult actualBlack = pawnBlack.move(A4, piecesOnChessBoard);
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(actualWhite).isEqualTo(FAILURE),
                () -> Assertions.assertThat(actualBlack).isEqualTo(FAILURE)
        );
    }
}
