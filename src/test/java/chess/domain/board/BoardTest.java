package chess.domain.board;

import static chess.domain.board.BoardFixtures.createBlankBoard;
import static chess.domain.board.BoardFixtures.createBoardWithBlackBlocking;
import static chess.domain.board.File.A;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.PositionFixtures.initialBlackKing;
import static chess.domain.board.PositionFixtures.initialBlackQueen;
import static chess.domain.board.PositionFixtures.initialWhiteBishop;
import static chess.domain.board.PositionFixtures.initialWhiteKing;
import static chess.domain.board.PositionFixtures.initialWhiteKnight;
import static chess.domain.board.PositionFixtures.initialWhiteQueen;
import static chess.domain.board.PositionFixtures.initialWhiteRook;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.THREE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Team;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {

	@Test
	void isAlly() {
		Board board = new Board(BoardFactory.initiate());

		assertAll(
				() -> assertThat(board.isAlly(initialWhiteKing, Team.WHITE)).isTrue(),
				() -> assertThat(board.isAlly(initialWhiteKing, Team.BLACK)).isFalse()
		);
	}

	@Test
	void isCheck() {
		Board board = new Board(BoardFactory.initiate());

		assertAll(
				() -> assertThat(board.isCheck(initialWhiteKing)).isTrue(),
				() -> assertThat(board.isCheck(initialBlackQueen)).isFalse()
		);
	}

	@Test
	void moveWithWrongSource() {
		Board board = new Board(createBlankBoard());

		assertThatThrownBy(() -> board.move(Position.of(FOUR, D), Position.of(FIVE, E)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 위치에 기물이 없어 움직일 수 없습니다.");
	}

	@Test
	void moveToSameTeam() {
		Board board = new Board(BoardFactory.initiate());

		assertAll(
				() -> assertThatThrownBy(() -> board.move(initialWhiteKing, initialWhiteQueen))
						.isInstanceOf(IllegalArgumentException.class)
						.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다."),
				() -> assertThatThrownBy(() -> board.move(initialBlackKing, initialBlackQueen))
						.isInstanceOf(IllegalArgumentException.class)
						.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.")
		);
	}

	@ParameterizedTest
	@MethodSource("createSourceAndTarget")
	void moveWithAllyBlocking(Position source, Position target) {
		Board board = new Board(BoardFactory.initiate());

		assertThatThrownBy(() -> board.move(source, target))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("다른 기물에 막혀 해당 위치로 기물을 옮길 수 없습니다.");
	}

	private static Stream<Arguments> createSourceAndTarget() {
		return Stream.of(
				Arguments.of(initialWhiteQueen, Position.of(THREE, F)),
				Arguments.of(initialWhiteBishop, Position.of(THREE, E)),
				Arguments.of(initialWhiteRook, Position.of(THREE, A))
		);
	}

	@ParameterizedTest
	@CsvSource(value = {"TWO, D", "SIX, D", "FOUR, F", "FOUR, B", "SIX, F", "TWO, B", "SIX, B", "TWO, F"})
	void moveWithEnemyBlocking(Rank rank, File file) {
		Board board = new Board(createBoardWithBlackBlocking(new Queen(Team.WHITE)));
		Position whiteQueen = Position.of(FOUR, D);

		assertThatThrownBy(() -> board.move(whiteQueen, Position.of(rank, file)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 위치로 기물을 옮길 수 없습니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"TWO, E", "TWO, C", "SIX, E", "SIX, C", "FIVE, F", "THREE, F", "FIVE, B", "THREE, B"})
	void moveKnightWithBlocking(Rank rank, File file) {
		Board board = new Board(createBoardWithBlackBlocking(new Knight(Team.WHITE)));
		Position whiteKnight = Position.of(FOUR, D);

		assertDoesNotThrow(() -> board.move(whiteKnight, Position.of(rank, file)));
	}

	@ParameterizedTest
	@CsvSource(value = {"FIVE, E", "FIVE, C"})
	void movePawnToEnemy(Rank rank, File file) {
		Board board = new Board(createBoardWithBlackBlocking(new Pawn(Team.WHITE)));
		Position whitePawn = Position.of(FOUR, D);

		assertDoesNotThrow(() -> board.move(whitePawn, Position.of(rank, file)));
	}

	@Test
	void movePawnToSameFileEnemy() {
		Board board = new Board(createBoardWithBlackBlocking(new Pawn(Team.WHITE)));
		Position whitePawn = Position.of(FOUR, D);

		assertThatThrownBy(() -> board.move(whitePawn, Position.of(FIVE, D)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
	}

	@Test
	void startWithWhiteTeam() {
		Board board = new Board(BoardFactory.initiate());

		assertDoesNotThrow(() -> board.move(initialWhiteKnight, Position.of(THREE, C)));
	}

	@Test
	void getAllyPiecesByFile() {
		Board board = new Board(BoardFactory.initiate());
		List<Piece> blackPieces = board.getAllyPiecesByFile(Team.BLACK, A);

		assertThat(blackPieces.size()).isEqualTo(2);
	}
}
