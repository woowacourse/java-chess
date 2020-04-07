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
import domain.board.fixture.PawnBoard;
import domain.command.MoveCommand;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

class PawnTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = new PawnBoard().create();
	}

	@DisplayName("폰 생성")
	@Test
	void constructor_CreatePawn_Success() {
		Assertions.assertThat(new Pawn(Position.of("b1"), Team.WHITE)).isInstanceOf(Pawn.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"move a1 a1, WHITE", "move d1 d1, BLACK"})
	void validateMovement_SourceSameAsTarget_ExceptionThrown(MoveCommand moveCommand, Team team) {
		Pawn pawn = new Pawn(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(() -> pawn.validateMovement(moveCommand.getTargetPosition(), team, board))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"E, move a1 c1, WHITE", "W, move d1 c1, WHITE", "NNE, move a1 b3, WHITE",
		"S, move a3 a2, WHITE", "N, move e2 e3, BLACK", "W, move g3 h1, BLACK"})
	void validateDirection_InvalidDirection_ExceptionThrown(Direction direction, MoveCommand moveCommand, Team team) {
		Pawn pawn = new Pawn(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(() -> pawn.validateDirection(direction))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("말이 움직일 수 없는 칸 수가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"move b1 b4, WHITE", "move b5 b2, BLACK"})
	void validateStepSize_InvalidStepSize_ExceptionThrown(MoveCommand moveCommand, Team team) {
		Pawn pawn = new Pawn(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(
			() -> pawn.validateStepSize(moveCommand.getSourcePosition(), moveCommand.getTargetPosition()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"N, move f1 f3, WHITE", "N, move e1 e3, WHITE"})
	void validateRoute_InvalidTargetPosition_ExceptionThrown(Direction direction, MoveCommand moveCommand, Team team) {
		Pawn pawn = new Pawn(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(() -> pawn.validateRoute(direction, moveCommand.getTargetPosition(), board))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}

	@DisplayName("처음 상태의 Pawn은 2칸 이동 가능")
	@Test
	void move_StartAndTwoStep_Success() {
		MoveCommand moveCommand = new MoveCommand("move b1 b3");
		Pawn pawn = new Pawn(moveCommand.getSourcePosition(), Team.WHITE);

		pawn.move(moveCommand.getTargetPosition(), Team.WHITE, board);

		assertThat(pawn.position).isEqualTo(moveCommand.getTargetPosition());
	}

	@DisplayName("진행 상태의 Pawn은 2칸 이동 불가능")
	@Test
	void move_RunAndTwoStep_ExceptionThrown() {
		MoveCommand moveCommand = new MoveCommand("move b1 b3");
		MoveCommand invalidInfo = new MoveCommand("move b3 b5");
		Pawn pawn = new Pawn(moveCommand.getSourcePosition(), Team.WHITE);

		pawn.move(moveCommand.getTargetPosition(), Team.WHITE, board);

		assertThatThrownBy(() -> pawn.move(invalidInfo.getTargetPosition(), Team.WHITE, board))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		MoveCommand moveCommand = new MoveCommand("move a1 a2");
		Pawn pawn = new Pawn(moveCommand.getSourcePosition(), Team.WHITE);

		pawn.move(moveCommand.getTargetPosition(), Team.WHITE, board);

		assertThat(pawn.position).isEqualTo(moveCommand.getTargetPosition());
	}

	@DisplayName("직선 - 기물이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_PieceAtLinearTargetPosition_ExceptionThrown() {
		MoveCommand moveCommand = new MoveCommand("move e2 e1");
		Pawn pawn = new Pawn(moveCommand.getSourcePosition(), Team.BLACK);

		assertThatThrownBy(() -> pawn.move(moveCommand.getTargetPosition(), Team.BLACK, board))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_AT_TARGET_POSITION);
	}

	@DisplayName("대각선 - 적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtDiagonalTargetPosition_Capture() {
		MoveCommand moveCommand = new MoveCommand("move d1 e2");
		Pawn pawn = new Pawn(moveCommand.getSourcePosition(), Team.WHITE);

		pawn.move(moveCommand.getTargetPosition(), Team.WHITE, board);

		Optional<Piece> targetPiece = board.findPiece(moveCommand.getTargetPosition());
		assertThat(targetPiece.get()).isEqualTo(pawn);
	}

	@DisplayName("대각선 - 아군이 있는 목적지가 입력되면 예외 발생")
	@Test
	void move_OurTeamAtDiagonalTargetPosition_ExceptionThrown() {
		MoveCommand moveCommand = new MoveCommand("move e1 f2");
		Pawn pawn = new Pawn(moveCommand.getSourcePosition(), Team.WHITE);

		assertThatThrownBy(() -> pawn.move(moveCommand.getTargetPosition(), Team.WHITE, board))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}
}
