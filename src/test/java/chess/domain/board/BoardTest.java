package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.score.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardTest {
    @DisplayName("빈 칸으로 말 이동")
    @Test
    void updateAtEmptyBoard() {
        Board board = new Board();
        Position source = Positions.of("a2");
        Position target = Positions.of("a3");
        board.updateBoard(source, target);

        Assertions.assertThat(board.getPiece(source)).isNull();
        Assertions.assertThat(board.getPiece(target)).isNotNull();
        Assertions.assertThat(board.getPiece(target)).isInstanceOf(Piece.class);
    }

    @DisplayName("상대 편으로 말 이동")
    @Test
    void updateAtEnemy() {
        Piece rook = new Piece(PieceType.ROOK, Team.WHITE);
        Piece pawn = new Piece(PieceType.PAWN, Team.BLACK);
        Map<Position, Piece> entry = new HashMap<>();
        Position source = Positions.of("a2");
        Position target = Positions.of("a3");
        entry.put(source, rook);
        entry.put(target, pawn);
        Board board = new Board(entry);
        board.updateBoard(source, target);

        Assertions.assertThat(board.getPiece(source)).isNull();
        Assertions.assertThat(board.getPiece(target)).isNotNull();
        Assertions.assertThat(board.getPiece(target)).isInstanceOf(Piece.class);
    }

    @DisplayName("승자 팀을 판단")
    @Test
    void checkWinnerTest() {
        Map<Position, Piece> whiteKing = new HashMap<>();
        whiteKing.put(Positions.of("a3"), new Piece(PieceType.KING, Team.WHITE));
        Board whiteWinBoard = new Board(whiteKing);

        Map<Position, Piece> blackKing = new HashMap<>();
        blackKing.put(Positions.of("b4"), new Piece(PieceType.KING, Team.BLACK));
        Board blackWinBoard = new Board(blackKing);

        Map<Position, Piece> bothKing = new HashMap<>();
        bothKing.put(Positions.of("a5"), new Piece(PieceType.KING, Team.BLACK));
        bothKing.put(Positions.of("b7"), new Piece(PieceType.KING, Team.WHITE));
        Board drawBoard = new Board(bothKing);

        Assertions.assertThat(whiteWinBoard.checkWinner()).isEqualTo(Optional.of(Team.WHITE));
        Assertions.assertThat(blackWinBoard.checkWinner()).isEqualTo(Optional.of(Team.BLACK));
        Assertions.assertThat(drawBoard.checkWinner()).isEmpty();
    }

    @DisplayName("지정한 칸이 비어있는지 판단")
    @Test
    void isEmptyTest() {
        Position nonEmptyTarget = Positions.of("a2");
        Position emptyTarget = Positions.of("a3");
        Board board = new Board();

        Assertions.assertThat(board.isEmpty(nonEmptyTarget)).isFalse();
        Assertions.assertThat(board.isEmpty(emptyTarget)).isTrue();
    }

    @DisplayName("지정한 칸의 체스 말 반환")
    @Test
    void getPieceTest() {
        Board board = new Board();

        Assertions.assertThat(board.getPiece(Positions.of("a2"))).isInstanceOf(Piece.class);
        Assertions.assertThat(board.getPiece(Positions.of("a3"))).isNull();
    }

    @DisplayName("같은 세로줄에 폰이 없을 때 점수 계산")
    @Test
    void calculateScoreWithoutSameFilePawnTest() {
        Board board = new Board();

        Assertions.assertThat(Score.calculateScore(board, Team.WHITE)).isEqualTo(38d);
        Assertions.assertThat(Score.calculateScore(board, Team.BLACK)).isEqualTo(38d);
    }

    @DisplayName("같은 세로줄에 폰이 있을 때 점수 계산")
    @Test
    void calculateScoreWithSameFilePawn() {
        Map<Position, Piece> sameFilePawn = new HashMap<>();
        sameFilePawn.put(Positions.of("a3"), new Piece(PieceType.PAWN, Team.WHITE));
        sameFilePawn.put(Positions.of("a4"), new Piece(PieceType.PAWN, Team.WHITE));
        sameFilePawn.put(Positions.of("a5"), new Piece(PieceType.PAWN, Team.WHITE));
        Board board = new Board(sameFilePawn);

        Assertions.assertThat(Score.calculateScore(board, Team.WHITE)).isEqualTo(1.5d);
    }
}
