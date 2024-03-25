package model.chessboard;

import model.piece.Piece;
import model.piece.state.*;
import model.position.File;
import model.position.Position;
import model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
                () -> assertInstanceOf(Rook.class, chessBoard.get(Position.of(File.A, Rank.ONE))
                                                             .role(), "백색 룩은 a1 위치에 있어야 합니다."),
                () -> assertInstanceOf(Rook.class, chessBoard.get(Position.of(File.H, Rank.ONE))
                                                             .role(), "백색 룩은 h1 위치에 있어야 합니다."),
                () -> assertInstanceOf(Rook.class, chessBoard.get(Position.of(File.A, Rank.EIGHT))
                                                             .role(), "흑색 룩은 a8 위치에 있어야 합니다."),
                () -> assertInstanceOf(Rook.class, chessBoard.get(Position.of(File.H, Rank.EIGHT))
                                                             .role(), "흑색 룩은 h8 위치에 있어야 합니다.")
        );
    }

    @Test
    @DisplayName("create 호출 시 킹과 퀸이 올바르게 초기화되어야 한다.")
    void create_ShouldInitializeKingAndQueenCorrectly_WhenCalled() {
        Map<Position, Piece> chessBoard = ChessBoardFactory.create();
        assertAll("킹과 퀸의 위치와 색상 검증",
                () -> assertInstanceOf(Queen.class, chessBoard.get(Position.of(File.D, Rank.ONE))
                                                              .role(), "백색 퀸은 d1 위치에 있어야 합니다."),
                () -> assertInstanceOf(King.class, chessBoard.get(Position.of(File.E, Rank.ONE))
                                                             .role(), "백색 킹은 e1 위치에 있어야 합니다."),
                () -> assertInstanceOf(Queen.class, chessBoard.get(Position.of(File.D, Rank.EIGHT))
                                                              .role(), "흑색 퀸은 d8 위치에 있어야 합니다."),
                () -> assertInstanceOf(King.class, chessBoard.get(Position.of(File.E, Rank.EIGHT))
                                                             .role(), "흑색 킹은 e8 위치에 있어야 합니다.")
        );
    }

    @Test
    @DisplayName("create 호출 시 모든 폰이 올바르게 초기화되어야 한다.")
    void create_ShouldInitializePawnsCorrectly_WhenCalled() {
        Map<Position, Piece> chessBoard = ChessBoardFactory.create();
        for (File file : File.values()) {
            assertInstanceOf(Pawn.class, chessBoard.get(Position.of(file, Rank.TWO))
                                                   .role(), "백색 폰은 2번 rank에 있어야 합니다.");
            assertInstanceOf(Pawn.class, chessBoard.get(Position.of(file, Rank.SEVEN))
                                                   .role(), "흑색 폰은 7번 rank에 있어야 합니다.");
        }
    }

    @Test
    @DisplayName("create 호출 시 모든 Square가 올바르게 초기화되어야 한다.")
    void create_ShouldInitializeSquaresCorrectly_WhenCalled() {
        Map<Position, Piece> chessBoard = ChessBoardFactory.create();
        for (File file : File.values()) {
            assertAll(
                    () -> assertInstanceOf(Square.class, chessBoard.get(Position.of(file, Rank.THREE))
                                                                   .role(), "Square는 3번 rank에 초기화되어야 합니다."),
                    () -> assertInstanceOf(Square.class, chessBoard.get(Position.of(file, Rank.FOUR))
                                                                   .role(), "Square는 4번 rank에 초기화되어야 합니다."),
                    () -> assertInstanceOf(Square.class, chessBoard.get(Position.of(file, Rank.FIVE))
                                                                   .role(), "Square는 5번 rank에 초기화되어야 합니다."),
                    () -> assertInstanceOf(Square.class, chessBoard.get(Position.of(file, Rank.SIX))
                                                                   .role(), "Square는 6번 rank에 초기화되어야 합니다.")
            );
        }
    }
}

