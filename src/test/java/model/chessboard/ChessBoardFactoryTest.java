package model.chessboard;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.Map;
import model.piece.PieceHolder;
import model.piece.role.King;
import model.piece.role.Pawn;
import model.piece.role.Queen;
import model.piece.role.Rook;
import model.piece.role.Square;
import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardFactoryTest {

    @Test
    @DisplayName("create 호출 시 체스보드가 비어있지 않아야 한다.")
    void create_ShouldReturnNonEmptyBoard_WhenCalled() {
        Map<Position, PieceHolder> chessBoard = ChessBoardFactory.create();
        assertFalse(chessBoard.isEmpty(), "체스 보드는 생성 후 비어있지 않아야 합니다.");
    }

    @Test
    @DisplayName("create 호출 시 룩이 올바르게 초기화되어야 한다.")
    void create_ShouldInitializeRooksCorrectly_WhenCalled() {
        Map<Position, PieceHolder> chessBoard = ChessBoardFactory.create();
        assertAll("룩의 위치와 색상 검증",
                () -> assertInstanceOf(Rook.class, chessBoard.get(Position.of(1, 1))
                        .getRole(), "백색 룩은 a1 위치에 있어야 합니다."),
                () -> assertInstanceOf(Rook.class, chessBoard.get(Position.of(8, 1))
                        .getRole(), "백색 룩은 h1 위치에 있어야 합니다."),
                () -> assertInstanceOf(Rook.class, chessBoard.get(Position.of(1, 8))
                        .getRole(), "흑색 룩은 a8 위치에 있어야 합니다."),
                () -> assertInstanceOf(Rook.class, chessBoard.get(Position.of(8, 8))
                        .getRole(), "흑색 룩은 h8 위치에 있어야 합니다.")
        );
    }

    @Test
    @DisplayName("create 호출 시 킹과 퀸이 올바르게 초기화되어야 한다.")
    void create_ShouldInitializeKingAndQueenCorrectly_WhenCalled() {
        Map<Position, PieceHolder> chessBoard = ChessBoardFactory.create();
        assertAll("킹과 퀸의 위치와 색상 검증",
                () -> assertInstanceOf(Queen.class, chessBoard.get(Position.of(4, 1))
                        .getRole(), "백색 퀸은 d1 위치에 있어야 합니다."),
                () -> assertInstanceOf(King.class, chessBoard.get(Position.of(5, 1))
                        .getRole(), "백색 킹은 e1 위치에 있어야 합니다."),
                () -> assertInstanceOf(Queen.class, chessBoard.get(Position.of(4, 8))
                        .getRole(), "흑색 퀸은 d8 위치에 있어야 합니다."),
                () -> assertInstanceOf(King.class, chessBoard.get(Position.of(5, 8))
                        .getRole(), "흑색 킹은 e8 위치에 있어야 합니다.")
        );
    }

    @Test
    @DisplayName("create 호출 시 모든 폰이 올바르게 초기화되어야 한다.")
    void create_ShouldInitializePawnsCorrectly_WhenCalled() {
        Map<Position, PieceHolder> chessBoard = ChessBoardFactory.create();
        for (int file = 1; file <= 8; file++) {
            assertInstanceOf(Pawn.class, chessBoard.get(Position.of(file, 2))
                    .getRole(), "백색 폰은 2번 rank에 있어야 합니다.");
            assertInstanceOf(Pawn.class, chessBoard.get(Position.of(file, 7))
                    .getRole(), "흑색 폰은 7번 rank에 있어야 합니다.");
        }
    }

    @Test
    @DisplayName("create 호출 시 모든 Square가 올바르게 초기화되어야 한다.")
    void create_ShouldInitializeSquaresCorrectly_WhenCalled() {
        Map<Position, PieceHolder> chessBoard = ChessBoardFactory.create();
        for (int rank = 3; rank <= 6; rank++) {
            for (int file = 1; file <= 8; file++) {
                assertInstanceOf(Square.class, chessBoard.get(Position.of(file, rank))
                        .getRole(), "Square는 3에서 6번 rank 사이에 초기화되어야 합니다.");
            }
        }
    }
}

