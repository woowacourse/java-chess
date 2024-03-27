package model.chessboard;

import model.piece.Piece;
import model.piece.role.RoleStatus;
import model.position.File;
import model.position.Position;
import model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ChessBoardFactoryTest {

    @Test
    @DisplayName("create 호출 시 체스보드가 비어있지 않아야 한다.")
    void create_ShouldReturnNonEmptyBoard_WhenCalled() {
        Map<Position, Piece> chessBoard = ChessBoardFactory.create();
        assertFalse(chessBoard.isEmpty(), "체스 보드는 생성 후 비어있지 않아야 합니다.");
    }

    @Test
    @DisplayName("create 호출 시 룩이 올바르게 초기화되어야 한다.")
    void create_ShouldInitializeRooksCorrectly_WhenCalled() {
        Map<Position, Piece> chessBoard = ChessBoardFactory.create();
        assertAll("룩의 위치와 색상 검증",
                () -> assertThat(RoleStatus.ROOK).isEqualTo(chessBoard.get(Position.of(File.A, Rank.ONE)).roleStatus()),
                () -> assertThat(RoleStatus.ROOK).isEqualTo(chessBoard.get(Position.of(File.H, Rank.ONE)).roleStatus()),
                () -> assertThat(RoleStatus.ROOK).isEqualTo(chessBoard.get(Position.of(File.A, Rank.EIGHT)).roleStatus()),
                () -> assertThat(RoleStatus.ROOK).isEqualTo(chessBoard.get(Position.of(File.H, Rank.EIGHT)).roleStatus())
        );
    }

    @Test
    @DisplayName("create 호출 시 킹과 퀸이 올바르게 초기화되어야 한다.")
    void create_ShouldInitializeKingAndQueenCorrectly_WhenCalled() {
        Map<Position, Piece> chessBoard = ChessBoardFactory.create();
        assertAll("킹과 퀸의 위치와 색상 검증",
                () -> assertThat(RoleStatus.QUEEN).isEqualTo(chessBoard.get(Position.of(File.D, Rank.ONE)).roleStatus()),
                () -> assertThat(RoleStatus.KING).isEqualTo(chessBoard.get(Position.of(File.E, Rank.ONE)).roleStatus()),
                () -> assertThat(RoleStatus.QUEEN).isEqualTo(chessBoard.get(Position.of(File.D, Rank.EIGHT)).roleStatus()),
                () -> assertThat(RoleStatus.KING).isEqualTo(chessBoard.get(Position.of(File.E, Rank.EIGHT)).roleStatus())
        );
    }

    @Test
    @DisplayName("create 호출 시 모든 폰이 올바르게 초기화되어야 한다.")
    void create_ShouldInitializePawnsCorrectly_WhenCalled() {
        Map<Position, Piece> chessBoard = ChessBoardFactory.create();
        for (File file : File.values()) {
            assertThat(RoleStatus.PAWN).isEqualTo(chessBoard.get(Position.of(file, Rank.TWO)).roleStatus());
            assertThat(RoleStatus.PAWN).isEqualTo(chessBoard.get(Position.of(file, Rank.SEVEN)).roleStatus());
        }
    }

    @Test
    @DisplayName("create 호출 시 모든 Square가 올바르게 초기화되어야 한다.")
    void create_ShouldInitializeSquaresCorrectly_WhenCalled() {
        Map<Position, Piece> chessBoard = ChessBoardFactory.create();
        for (File file : File.values()) {
            assertAll(
                    () -> assertThat(RoleStatus.SQUARE).isEqualTo(chessBoard.get(Position.of(file, Rank.THREE)).roleStatus()),
                    () -> assertThat(RoleStatus.SQUARE).isEqualTo(chessBoard.get(Position.of(file, Rank.FOUR)).roleStatus()),
                    () -> assertThat(RoleStatus.SQUARE).isEqualTo(chessBoard.get(Position.of(file, Rank.FIVE)).roleStatus()),
                    () -> assertThat(RoleStatus.SQUARE).isEqualTo(chessBoard.get(Position.of(file, Rank.SIX)).roleStatus())
            );
        }
    }
}

