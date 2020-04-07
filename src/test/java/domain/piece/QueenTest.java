package domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.board.Board;
import domain.board.fixture.QueenBoard;
import domain.command.MoveCommand;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

class QueenTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = new QueenBoard().create();
	}

	@DisplayName("퀸 생성")
	@Test
	void constructor_CreateQueen_Success() {
		Assertions.assertThat(new Queen(Position.of("b1"), Team.WHITE)).isInstanceOf(Queen.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"move e1 e1, WHITE", "move c7 c7, BLACK"})
	void validateMovement_SourceSameAsTarget_ExceptionThrown(MoveCommand moveCommand, Team team) {
		Queen queen = new Queen(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(() -> queen.validateMovement(moveCommand.getTargetPosition(), team, board))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"NNE,move e1 f3, WHITE", "NNW,move d2 c4, WHITE", "SSE, move c7 d5, BLACK", "SSW, move e5 d3, BLACK"})
	void validateDirection_InvalidDirection_ExceptionThrown(Direction direction, MoveCommand moveCommand, Team team) {
		Queen queen = new Queen(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(() -> queen.validateDirection(direction))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		MoveCommand moveCommand = new MoveCommand("move e1 e4");
		Queen queen = new Queen(moveCommand.getSourcePosition(), Team.WHITE);

		queen.move(moveCommand.getTargetPosition(), Team.WHITE, board);

		assertThat(queen.position).isEqualTo(moveCommand.getTargetPosition());
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		MoveCommand moveCommand = new MoveCommand("move e1 d2");

		Queen queen = new Queen(moveCommand.getSourcePosition(), Team.WHITE);

		assertThatThrownBy(() -> queen.move(moveCommand.getTargetPosition(), Team.WHITE, board))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		MoveCommand moveCommand = new MoveCommand("move e1 e5");
		Queen queen = new Queen(moveCommand.getSourcePosition(), Team.WHITE);

		queen.move(moveCommand.getTargetPosition(), Team.WHITE, board);

		Optional<Piece> targetPiece = board.findPiece(moveCommand.getTargetPosition());
		assertThat(targetPiece.get()).isEqualTo(queen);
	}
}
