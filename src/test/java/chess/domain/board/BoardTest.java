package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BoardTest {
    @DisplayName("빈 칸으로 말 이동")
    @Test
    void updateAtEmptyBoard() {
        Board board = new Board();
        Position source = Position.of("a2");
        Position target = Position.of("a3");
        board.updateBoard(source, target);

        Assertions.assertThatThrownBy(() -> {
            board.getPiece(source);
        }).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThat(board.getPiece(target)).isInstanceOf(Piece.class);
    }

    @DisplayName("상대 편으로 말 이동")
    @Test
    void updateAtEnemy() {
        Piece rook = new Rook(PieceType.ROOK, Team.WHITE);
        Piece pawn = new Pawn(PieceType.PAWN, Team.BLACK);
        Map<Position, Piece> entry = new HashMap<>();
        Position source = Position.of("a2");
        Position target = Position.of("a3");
        entry.put(source, rook);
        entry.put(target, pawn);
        Board board = new Board(entry);
        board.updateBoard(source, target);

        Assertions.assertThatThrownBy(() -> {
            board.getPiece(source);
        }).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThat(board.getPiece(target)).isNotNull();
        Assertions.assertThat(board.getPiece(target)).isInstanceOf(Piece.class);
    }

    @DisplayName("승자 팀을 판단")
    @Test
    void checkWinnerTest() {
        Map<Position, Piece> whiteKing = new HashMap<>();
        whiteKing.put(Position.of("a3"), new King(PieceType.KING, Team.WHITE));
        Board whiteWinBoard = new Board(whiteKing);

        Map<Position, Piece> blackKing = new HashMap<>();
        blackKing.put(Position.of("b4"), new King(PieceType.KING, Team.BLACK));
        Board blackWinBoard = new Board(blackKing);

        Map<Position, Piece> bothKing = new HashMap<>();
        bothKing.put(Position.of("a5"), new King(PieceType.KING, Team.BLACK));
        bothKing.put(Position.of("b7"), new King(PieceType.KING, Team.WHITE));
        Board drawBoard = new Board(bothKing);

        Assertions.assertThat(whiteWinBoard.checkWinner()).isEqualTo(Team.WHITE);
        Assertions.assertThat(blackWinBoard.checkWinner()).isEqualTo(Team.BLACK);
        Assertions.assertThat(drawBoard.checkWinner()).isNull();
    }

    @DisplayName("지정한 칸이 비어있는지 판단")
    @Test
    void isEmptyTest() {
        Position nonEmptyTarget = Position.of("a2");
        Position emptyTarget = Position.of("a3");
        Board board = new Board();

        Assertions.assertThat(board.isEmpty(nonEmptyTarget)).isFalse();
        Assertions.assertThat(board.isEmpty(emptyTarget)).isTrue();
    }

    @DisplayName("지정한 칸의 체스 말 반환")
    @Test
    void getPieceTest() {
        Board board = new Board();

        Assertions.assertThat(board.getPiece(Position.of("a2"))).isInstanceOf(Piece.class);
        Assertions.assertThatThrownBy(() -> {
            board.getPiece(Position.of("a3"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("같은 세로줄에 폰이 없을 때 점수 계산")
    @Test
    void calculateScoreWithoutSameFilePawnTest() {
        Board board = new Board();

        Assertions.assertThat(board.calculateScore(Team.WHITE)).extracting("boardScore").isEqualTo(38d);
        Assertions.assertThat(board.calculateScore(Team.BLACK)).extracting("boardScore").isEqualTo(38d);
    }

    @DisplayName("같은 세로줄에 폰이 있을 때 점수 계산")
    @Test
    void calculateScoreWithSameFilePawn() {
        Map<Position, Piece> sameFilePawn = new HashMap<>();
        sameFilePawn.put(Position.of("a3"), new Pawn(PieceType.PAWN, Team.WHITE));
        sameFilePawn.put(Position.of("a4"), new Pawn(PieceType.PAWN, Team.WHITE));
        sameFilePawn.put(Position.of("a5"), new Pawn(PieceType.PAWN, Team.WHITE));
        Board board = new Board(sameFilePawn);

        Assertions.assertThat(board.calculateScore(Team.WHITE)).extracting("boardScore").isEqualTo(1.5d);
    }
}
