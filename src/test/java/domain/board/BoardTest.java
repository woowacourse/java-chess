package domain.board;

import static domain.board.BoardFactory.createBlankBoard;
import static domain.board.BoardFactory.createBoardWithBlackBlocking;
import static domain.board.BoardFactory.createCatchKingBoard;
import static domain.board.BoardFactory.createSameColumnPawnBoard;
import static domain.board.PositionFixtures.initialBlackKing;
import static domain.board.PositionFixtures.initialBlackKnight;
import static domain.board.PositionFixtures.initialBlackQueen;
import static domain.board.PositionFixtures.initialWhiteBishop;
import static domain.board.PositionFixtures.initialWhiteKing;
import static domain.board.PositionFixtures.initialWhiteKnight;
import static domain.board.PositionFixtures.initialWhiteQueen;
import static domain.board.PositionFixtures.initialWhiteRook;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Board;
import chess.domain.board.InitialBoard;
import chess.domain.board.Position;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {

	@Test
	void moveWithWrongSource() {
		Board board = new Board(createBlankBoard());

		assertThatThrownBy(() -> board.move(Position.of(4, 4), Position.of(5, 5)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 위치에 기물이 없습니다.");
	}

	@Test
	void moveToSameTeam() {
		Board board = new Board(InitialBoard.createBoard());

		assertAll(
				() -> assertThatThrownBy(() -> board.move(initialWhiteKing, initialWhiteQueen))
						.isInstanceOf(IllegalArgumentException.class)
						.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다."),
				() -> assertThatThrownBy(() -> board.move(initialBlackKing, initialBlackQueen))
						.isInstanceOf(IllegalArgumentException.class)
						.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.")
		);
	}

	@ParameterizedTest(name = "{index} - source:{0}, target:{1}")
	@MethodSource("createSourceAndTarget")
	void moveWithAllyBlocking(Position source, Position target) {
		Board board = new Board(InitialBoard.createBoard());

		assertThatThrownBy(() -> board.move(source, target))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 위치로 기물을 옮길 수 없습니다.");
	}

	private static Stream<Arguments> createSourceAndTarget() {
		return Stream.of(
				Arguments.of(initialWhiteQueen, Position.of(3, 6)),
				Arguments.of(initialWhiteBishop, Position.of(3, 5)),
				Arguments.of(initialWhiteRook, Position.of(3, 1))
		);
	}

	@ParameterizedTest(name = "{index} - target:{0}, {1}")
	@CsvSource(value = {"2, 4", "6, 4", "4, 6", "4, 2", "6, 6", "2, 2", "6, 2", "2, 6"})
	void moveWithEnemyBlocking(int targetRow, int targetColumn) {
		Board board = new Board(createBoardWithBlackBlocking(new Queen(Team.WHITE)));
		Position whiteQueen = Position.of(4, 4);

		assertThatThrownBy(() -> board.move(whiteQueen, Position.of(targetRow, targetColumn)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 위치로 기물을 옮길 수 없습니다.");
	}

	@ParameterizedTest(name = "{index} - target:{0}, {1}")
	@CsvSource(value = {"2, 5", "2, 3", "6, 5", "6, 3", "5, 6", "3, 6", "5, 2", "3, 2"})
	void moveKnightWithBlocking(int targetRow, int targetColumn) {
		Board board = new Board(createBoardWithBlackBlocking(new Knight(Team.WHITE)));
		Position whiteKnight = Position.of(4, 4);

		assertDoesNotThrow(() -> board.move(whiteKnight, Position.of(targetRow, targetColumn)));
	}

	@ParameterizedTest(name = "{index} - target:{0}, {1}")
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
				.hasMessageContaining("폰은 해당 위치의 기물을 잡을 수 없습니다.");
	}

	@Test
	void calculateScore() {
		Board board = new Board(InitialBoard.createBoard());

		assertAll(
				() -> assertThat(board.calculateScore(Team.BLACK)).isEqualTo(38),
				() -> assertThat(board.calculateScore(Team.WHITE)).isEqualTo(38)
		);
	}

	@Test
	void calculateScoreWithSameColumnPawn() {
		Board board = new Board(createSameColumnPawnBoard());

		assertThat(board.calculateScore(Team.WHITE)).isEqualTo(1.5);
	}

	@Test
	void startWithWhiteTeam() {
		Board board = new Board(InitialBoard.createBoard());

		assertDoesNotThrow(() -> board.move(initialWhiteKnight, Position.of(3, 3)));
	}

	@Test
	void moveAndChangeTeam() {
		Board board = new Board(InitialBoard.createBoard());
		Position nextWhiteKnight = Position.of(3, 3);
		board.move(initialWhiteKnight, nextWhiteKnight);

		assertAll(
				() -> assertThatThrownBy(() -> board.move(nextWhiteKnight, Position.of(5, 4)))
						.isInstanceOf(IllegalArgumentException.class)
						.hasMessageContaining("상대 팀의 기물을 옮길 수 없습니다."),
				() -> assertDoesNotThrow(() -> board.move(initialBlackKnight, Position.of(6, 3)))
		);
	}

	@Test
	void finishGameWithCatchingKing() {
		Board board = new Board(createCatchKingBoard());
		Position whiteKing = Position.of(4, 4);
		Position blackKing = Position.of(5, 5);
		board.move(whiteKing, blackKing);

		assertThat(board.isFinished()).isTrue();
	}

	@Test
	void judgeWinnerWithFinished() {
		Board board = new Board(createCatchKingBoard());
		Position whiteKing = Position.of(4, 4);
		Position blackKing = Position.of(5, 5);
		board.move(whiteKing, blackKing);

		assertThat(board.judgeWinner()).isEqualTo(Team.WHITE);
	}

	@Test
	void judgeWinnerWithRunning() {
		Board board = new Board(InitialBoard.createBoard());

		assertThatThrownBy(board::judgeWinner)
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("아직 종료되지 않은 게임입니다.");
	}
}
