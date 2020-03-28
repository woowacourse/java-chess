package chess.domain.chessboard;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;
import chess.domain.position.Position;

public class RowTest {
	private Row row;

	@BeforeEach
	void setUp() {
		row = new Row(Arrays.asList(
			new Rook(Position.of(1, 1), Team.WHITE),
			new Knight(Position.of(1, 2), Team.WHITE),
			new Bishop(Position.of(1, 3), Team.BLACK),
			new Queen(Position.of(1, 4), Team.BLACK),
			new Pawn(Position.of(1, 5), Team.BLACK)
		));
	}

	@Test
	void containsTest() {
		assertThat(row.contains(Position.of(1, 1))).isTrue();
		assertThat(row.contains(Position.of(1, 6))).isFalse();
	}

	@Test
	void findByPositionTest() {
		assertThat(row.findByPosition(Position.of(1, 1)))
			.isEqualTo(new Rook(Position.of(1,1 ), Team.WHITE));
	}

	@Test
	void replaceTest() {
		Position targetPosition = Position.of(1, 2);
		row.replace(
			targetPosition,
			new Pawn(targetPosition, Team.WHITE)
		);

		assertThat(row.get(1)).isEqualTo(new Pawn(targetPosition, Team.WHITE));
	}

	@Test
	void findByTeamTest() {
		List<ChessPiece> actualPieces = row.findByTeam(Team.WHITE);
		List<ChessPiece> expectedPieces = Arrays.asList(
			new Rook(Position.of(1, 1), Team.WHITE),
			new Knight(Position.of(1, 2), Team.WHITE)
		);

		assertThat(actualPieces).isEqualTo(expectedPieces);
	}

	@Test
	void isPawnTest() {
		assertThat(row.isPawn(4, Team.BLACK)).isTrue();
	}
}
