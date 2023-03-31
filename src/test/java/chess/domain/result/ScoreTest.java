package chess.domain.result;

import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreTest {
    @DisplayName("Pawn 이 한 File 에 하나 있으면 1점으로 계산된다")
    @Test
    void onePawninFile() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(0, 1), new BlackPawn(PieceType.PAWN));
        board.put(Position.of(0, 2), new BlackPawn(PieceType.PAWN));
        Score score = Score.calculate(board);

        assertThat(score.getScore(TeamColor.BLACK)).isEqualTo(2.0);
    }

    @DisplayName("Pawn 이 한 File 에 두개 이상 있으면 각각 0.5점으로 계산된다.")
    @Test
    void TwoPawninSameFile() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(0, 1), new BlackPawn(PieceType.PAWN));
        board.put(Position.of(1, 1), new BlackPawn(PieceType.PAWN));
        Score score = Score.calculate(board);

        assertThat(score.getScore(TeamColor.BLACK)).isEqualTo(1.0);
    }

    @DisplayName("Queen, Rook, Bishop, Knight 점수 계산 테스트(Pawn 제외)")
    @Test
    void allPieceExceptPawn() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(1, 1), new Queen(PieceType.QUEEN, TeamColor.BLACK));
        board.put(Position.of(3, 4), new Rook(PieceType.ROOK, TeamColor.BLACK));
        board.put(Position.of(2, 6), new Bishop(PieceType.BISHOP, TeamColor.BLACK));
        board.put(Position.of(7, 7), new Knight(PieceType.KNIGHT, TeamColor.BLACK));

        Score score = Score.calculate(board);

        assertThat(score.getScore(TeamColor.BLACK)).isEqualTo(19.5);
    }

    @DisplayName("Queen, Rook, Bishop, Knight 점수 계산 테스트(같은 File 에 Pawn 2개 있을 때)")
    @Test
    void allPieceWithTwoPawnInSameFile() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.of(1, 1), new Queen(PieceType.QUEEN, TeamColor.BLACK));
        board.put(Position.of(3, 4), new Rook(PieceType.ROOK, TeamColor.BLACK));
        board.put(Position.of(2, 6), new Bishop(PieceType.BISHOP, TeamColor.BLACK));
        board.put(Position.of(7, 7), new Knight(PieceType.KNIGHT, TeamColor.BLACK));
        board.put(Position.of(5, 5), new BlackPawn(PieceType.PAWN));
        board.put(Position.of(4, 5), new BlackPawn(PieceType.PAWN));

        Score score = Score.calculate(board);

        assertThat(score.getScore(TeamColor.BLACK)).isEqualTo(20.5);
    }
}
