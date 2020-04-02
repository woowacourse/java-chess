package domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.board.BoardGame;
import domain.board.fixture.PawnBoard;
import domain.command.MoveInfo;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class PawnTest {
	private BoardGame chessGame;

	@BeforeEach
	void setUp() {
		chessGame = new BoardGame(new PawnBoard().create());
	}

	@DisplayName("폰 생성")
	@Test
	void constructor_CreatePawn_Success() {
		Assertions.assertThat(new Pawn(Position.of("b1"), Team.WHITE)).isInstanceOf(Pawn.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"move a1 a1, WHITE", "move d1 d1, BLACK"})
	void canMove_SourceSameAsTarget_ExceptionThrown(MoveInfo moveInfo, Team team) {
		Pawn pawn = new Pawn(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> pawn.canMove(moveInfo.getTargetPosition(), team, chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"E, move a1 c1, WHITE", "W, move d1 c1, WHITE", "NNE, move a1 b3, WHITE",
		"S, move a3 a2, WHITE", "N, move e2 e3, BLACK", "W, move g3 h1, BLACK"})
	void validateDirection_InvalidDirection_ExceptionThrown(Direction direction, MoveInfo moveInfo, Team team) {
		Pawn pawn = new Pawn(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> pawn.validateDirection(direction))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("말이 움직일 수 없는 칸 수가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"move b1 b4, WHITE", "move b5 b2, BLACK"})
	void validateStepSize_InvalidStepSize_ExceptionThrown(MoveInfo moveInfo, Team team) {
		Pawn pawn = new Pawn(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> pawn.validateStepSize(moveInfo.getSourcePosition(), moveInfo.getTargetPosition()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"N, move f1 f3, WHITE", "N, move e1 e3, WHITE"})
	void validateRoute_InvalidTargetPosition_ExceptionThrown(Direction direction, MoveInfo moveInfo, Team team) {
		Pawn pawn = new Pawn(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> pawn.validateRoute(direction, moveInfo.getTargetPosition(), chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}

	@DisplayName("처음 상태의 Pawn은 2칸 이동 가능")
	@Test
	void canMove_StartAndTwoStep_Success() {
		MoveInfo moveInfo = new MoveInfo("move b1 b3");
		Pawn pawn = new Pawn(moveInfo.getSourcePosition(), Team.WHITE);

		pawn.canMove(moveInfo.getTargetPosition(), Team.WHITE, chessGame.getBoard());
		pawn.move(moveInfo.getTargetPosition(), chessGame.getBoard());

		assertThat(pawn.position).isEqualTo(moveInfo.getTargetPosition());
	}

	@DisplayName("진행 상태의 Pawn은 2칸 이동 불가능")
	@Test
	void move_RunAndTwoStep_ExceptionThrown() {
		MoveInfo moveInfo = new MoveInfo("move b1 b3");
		MoveInfo invalidInfo = new MoveInfo("move b3 b5");
		Pawn pawn = new Pawn(moveInfo.getSourcePosition(), Team.WHITE);

		pawn.move(moveInfo.getTargetPosition(), chessGame.getBoard());

		assertThatThrownBy(() -> pawn.canMove(invalidInfo.getTargetPosition(), Team.WHITE, chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		MoveInfo moveInfo = new MoveInfo("move a1 a2");
		Pawn pawn = new Pawn(moveInfo.getSourcePosition(), Team.WHITE);

		pawn.move(moveInfo.getTargetPosition(), chessGame.getBoard());

		assertThat(pawn.position).isEqualTo(moveInfo.getTargetPosition());
	}

	@DisplayName("직선 - 기물이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_PieceAtLinearTargetPosition_ExceptionThrown() {
		MoveInfo moveInfo = new MoveInfo("move e2 e1");
		Pawn pawn = new Pawn(moveInfo.getSourcePosition(), Team.WHITE);

		assertThatThrownBy(() -> pawn.move(moveInfo.getTargetPosition(), chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_AT_TARGET_POSITION);
	}

	// TODO: 2020-04-02 게터 
	@DisplayName("대각선 - 적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtDiagonalTargetPosition_Capture() {
		MoveInfo moveInfo = new MoveInfo("move d1 e2");
		Pawn pawn = new Pawn(moveInfo.getSourcePosition(), Team.WHITE);

		pawn.move(moveInfo.getTargetPosition(), chessGame.getBoard());

		Optional<Piece> targetPiece = chessGame.getBoard().findPiece(moveInfo.getTargetPosition());
		assertThat(targetPiece.get()).isEqualTo(pawn);
	}

	@DisplayName("대각선 - 아군이 있는 목적지가 입력되면 예외 발생")
	@Test
	void move_OurTeamAtDiagonalTargetPosition_ExceptionThrown() {
		MoveInfo moveInfo = new MoveInfo("move e1 f2");
		Pawn pawn = new Pawn(moveInfo.getSourcePosition(), Team.WHITE);

		assertThatThrownBy(() -> pawn.move(moveInfo.getTargetPosition(), chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}
}
