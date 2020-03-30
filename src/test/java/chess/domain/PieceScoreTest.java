package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public class PieceScoreTest {
	@DisplayName("최종적으로 각 팀의 점수를 계산해 제대로 나오는지 확인하는 테스트")
	@Test
	void calculateTeamTest() {
		Map<Position, Piece> pieces = new HashMap<>();

		pieces.put(Position.of("a3"), new Pawn(Color.WHITE, "p"));
		pieces.put(Position.of("a4"), new Pawn(Color.WHITE, "p"));
		pieces.put(Position.of("b6"), new Pawn(Color.BLACK, "P"));
		pieces.put(Position.of("f6"), new Pawn(Color.BLACK, "P"));
		pieces.put(Position.of("f5"), new Pawn(Color.BLACK, "P"));

		ChessBoard chessBoard = new ChessBoard(pieces);

		double whiteScore = PieceScore.calculateTeam(chessBoard.getPieces(), Color.WHITE);
		double blackScore = PieceScore.calculateTeam(chessBoard.getPieces(), Color.BLACK);

		assertThat(whiteScore).isEqualTo(38.5);
		assertThat(blackScore).isEqualTo(38.5);
	}
}
