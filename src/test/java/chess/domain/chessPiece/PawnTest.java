package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.chessboard.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("폰 처음 2칸이동 테스트")
    void isMovable() {
        ChessBoard chessBoard = new ChessBoard();
        Piece blackPawn = chessBoard.findPieceByPosition(Position.of("b2"));
        Position targetPosition = Position.of("b4");

        assertThat(blackPawn.isMovable(targetPosition, null)).isTrue();
    }

    @Test
    @DisplayName("폰 공격 테스트")
    void isMovableAttack() {
        Piece blackPawn = new Pawn(Position.of("b2"), new BlackTeam());
        Piece whitePawn = new Pawn(Position.of("c3"), new WhiteTeam());

        assertThat(blackPawn.isMovable(Position.of("c3"), whitePawn)).isTrue();
    }

    @Test
    @DisplayName("폰 뒤로 이동 테스트")
    void isMovableFalse() {
        Piece blackPawn = new Pawn(Position.of("b2"), new BlackTeam());

        assertThat(blackPawn.isMovable(Position.of("b1"), null)).isFalse();

    }
}