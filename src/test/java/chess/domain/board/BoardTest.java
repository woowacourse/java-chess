package chess.domain.board;

import static chess.domain.board.BoardFixtures.createBlankBoard;
import static chess.domain.board.BoardFixtures.createBoardWithBlackBlocking;
import static chess.domain.board.PositionFixtures.initialBlackKing;
import static chess.domain.board.PositionFixtures.initialBlackQueen;
import static chess.domain.board.PositionFixtures.initialWhiteBishop;
import static chess.domain.board.PositionFixtures.initialWhiteKing;
import static chess.domain.board.PositionFixtures.initialWhiteKnight;
import static chess.domain.board.PositionFixtures.initialWhiteQueen;
import static chess.domain.board.PositionFixtures.initialWhiteRook;
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

		assertThatThrownBy(() -> board.move(Position.of(4, 4), Position.of(5, 5)))
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
				Arguments.of(initialWhiteQueen, Position.of(3, 6)),
				Arguments.of(initialWhiteBishop, Position.of(3, 5)),
				Arguments.of(initialWhiteRook, Position.of(3, 1))
		);
	}

	@ParameterizedTest
	@CsvSource(value = {"2, 4", "6, 4", "4, 6", "4, 2", "6, 6", "2, 2", "6, 2", "2, 6"})
	void moveWithEnemyBlocking(int targetRow, int targetColumn) {
		Board board = new Board(createBoardWithBlackBlocking(new Queen(Team.WHITE)));
		Position whiteQueen = Position.of(4, 4);

		assertThatThrownBy(() -> board.move(whiteQueen, Position.of(targetRow, targetColumn)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 위치로 기물을 옮길 수 없습니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"2, 5", "2, 3", "6, 5", "6, 3", "5, 6", "3, 6", "5, 2", "3, 2"})
	void moveKnightWithBlocking(int targetRow, int targetColumn) {
		Board board = new Board(createBoardWithBlackBlocking(new Knight(Team.WHITE)));
		Position whiteKnight = Position.of(4, 4);

		assertDoesNotThrow(() -> board.move(whiteKnight, Position.of(targetRow, targetColumn)));
	}

	@ParameterizedTest
	@CsvSource(value = {"5, 5", "5, 3"})
	void movePawnToEnemy(int targetRow, int targetColumn) {
		Board board = new Board(createBoardWithBlackBlocking(new Pawn(Team.WHITE)));
		Position whitePawn = Position.of(4, 4);

		assertDoesNotThrow(() -> board.move(whitePawn, Position.of(targetRow, targetColumn)));
	}

	@Test
	void movePawnToSameColumnEnemy() {
		Board board = new Board(createBoardWithBlackBlocking(new Pawn(Team.WHITE)));
		Position whitePawn = Position.of(4, 4);

		assertThatThrownBy(() -> board.move(whitePawn, Position.of(5, 4)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
	}

	@Test
	void startWithWhiteTeam() {
		Board board = new Board(BoardFactory.initiate());

		assertDoesNotThrow(() -> board.move(initialWhiteKnight, Position.of(3, 3)));
	}

	@Test
	void getAllyPiecesByColumn() {
		Board board = new Board(BoardFactory.initiate());
		List<Piece> blackPieces = board.getAllyPiecesByColumn(Team.BLACK, 1);

		assertThat(blackPieces.size()).isEqualTo(2);
	}
}
