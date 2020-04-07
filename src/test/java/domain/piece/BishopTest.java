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
import domain.board.fixture.BishopBoard;
import domain.command.MoveCommand;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

class BishopTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = new BishopBoard().create();
	}

	@DisplayName("비숍 생성")
	@Test
	void constructor_CreateBishop_Success() {
		Assertions.assertThat(new Bishop(Position.of("b1"), Team.WHITE)).isInstanceOf(Bishop.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"move b1 b1, WHITE", "move c2 c2, BLACK"})
	void validateMovement_SourceSameAsTarget_ExceptionThrown(MoveCommand moveCommand, Team team) {
		Bishop bishop = new Bishop(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(() -> bishop.validateMovement(moveCommand.getTargetPosition(), team, board))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"N, move b1 b2, WHITE", "W, move b1 a1, WHITE", "E, move b1 c1, WHITE", "S, move c2 d4, BLACK"})
	void validateDirection_InvalidDirection_ExceptionThrown(Direction direction, MoveCommand moveCommand, Team team) {
		Bishop bishop = new Bishop(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(() -> bishop.validateDirection(direction))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"NE, move b1 e4, WHITE", "SW, move d3 b1, BLACK"})
	void validateRoute_InvalidTargetPosition_ExceptionThrown(Direction direction, MoveCommand moveCommand, Team team) {
		Bishop bishop = new Bishop(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(() -> bishop.validateRoute(direction, moveCommand.getTargetPosition(), board))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		MoveCommand moveCommand = new MoveCommand("move d2 f4");
		Bishop bishop = new Bishop(moveCommand.getSourcePosition(), Team.WHITE);

		bishop.move(moveCommand.getTargetPosition(), Team.WHITE, board);

		assertThat(bishop.position).isEqualTo(moveCommand.getTargetPosition());
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		MoveCommand moveCommand = new MoveCommand("move c2 d3");

		Bishop bishop = new Bishop(moveCommand.getSourcePosition(), Team.BLACK);

		assertThatThrownBy(() -> bishop.move(moveCommand.getTargetPosition(), Team.BLACK, board))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		MoveCommand moveCommand = new MoveCommand("move b1 c2");
		Bishop bishop = new Bishop(moveCommand.getSourcePosition(), Team.WHITE);

		bishop.move(moveCommand.getTargetPosition(), Team.WHITE, board);

		Optional<Piece> targetPiece = board.findPiece(moveCommand.getTargetPosition());
		assertThat(targetPiece.get()).isEqualTo(bishop);
	}
}
