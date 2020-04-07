package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.board.Board;
import domain.board.fixture.PawnBoard;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class PawnTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board(new PawnBoard().create().getRanks());
	}

	@DisplayName("폰 생성")
	@Test
	void constructor_CreatePawn_Success() {
		Assertions.assertThat(new Pawn(Position.of("b1"), Team.WHITE)).isInstanceOf(Pawn.class);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"a1, WHITE, c1", "d1, WHITE, c1", "a1, WHITE, b3", "a3, WHITE, a2", "e2, BLACK, e3", "g3, BLACK, h1"})
	void canMove_InvalidDirection_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	// 우선 말이 움직일 수 있는 칸이 1,2칸 모두라고 가정한다.
	@DisplayName("말이 움직일 수 없는 칸 수가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, b4", "b5, BLACK, b2"})
	void canMove_InvalidStepSize_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"a2, WHITE, a4", "g2, WHITE, g4"})
	void canMove_InvalidTargetPosition_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}

	@DisplayName("처음 상태의 Pawn은 2칸 이동 가능")
	@Test
	void move_PawnIsInitialPosition_Success() {
		String sourcePosition = "f2";
		String targetPosition = "f4";

		board.move(sourcePosition, targetPosition, Team.WHITE);
		System.out.println("it works");
		Piece pieceAfterMove = board.findPiece(targetPosition, board.getRanks().get(3));
		assertThat(pieceAfterMove.getPosition()).isEqualTo(Position.of(targetPosition));
	}

	@DisplayName("진행 상태의 Pawn은 2칸 이동 불가능")
	@ParameterizedTest
	@CsvSource({"a3,a5,WHITE", "b5,b3,BLACK"})
	void move_PawnIsMoved_ExceptionThrown(String sourcePosition, String targetPosition, Team team) {

		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		String sourcePosition = "b1";
		String targetPosition = "b2";

		board.move(sourcePosition, targetPosition, Team.WHITE);
		Piece pieceAfterMove = board.findPiece(targetPosition, board.getRanks().get(1));
		assertThat(pieceAfterMove.getPosition()).isEqualTo(Position.of(targetPosition));
	}

	@DisplayName("직선 - 기물이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_PieceAtLinearTargetPosition_ExceptionThrown() {
		assertThatThrownBy(() -> board.move("e2", "e1", Team.BLACK))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_AT_TARGET_POSITION);
	}

	@DisplayName("대각선 - 적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtDiagonalTargetPosition_Capture() {
		String sourcePosition = "d1";
		String targetPosition = "e2";
		Piece targetPiece = board.findPiece(targetPosition, board.getRanks().get(1));

		board.move(sourcePosition, targetPosition, Team.WHITE);

		assertThat(board.getRanks().get(1).getPieces().contains(targetPiece)).isFalse();
	}

	@DisplayName("대각선 - 아군이 있는 목적지가 입력되면 예외 발생")
	@Test
	void move_OurTeamAtDiagonalTargetPosition_ExceptionThrown() {
		String sourcePosition = "e1";
		String targetPosition = "f2";
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}
}