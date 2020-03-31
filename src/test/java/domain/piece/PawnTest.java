package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.board.Board;
import domain.board.Rank;
import domain.board.fixture.PawnBoard;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;
import javafx.geometry.Pos;

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
	void canMove_InvalidDirection_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("말이 움직일 수 없는 칸 수가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, b4", "b5, BLACK, b2"})
	void canMove_InvalidStepSize_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"f1, WHITE, f3", "e1, WHITE, e3"})
	void canMove_InvalidTargetPosition_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}

	@DisplayName("처음 상태의 Pawn은 2칸 이동 가능")
	@Test
	void move_StateIsStart_Success() {
		Position sourcePosition = Position.of("b1");
		Position targetPosition = Position.of("b3");

		board.move(sourcePosition, targetPosition, Team.WHITE);

		Piece pieceAfterMove = board.getRanks().get(2).findPiece(targetPosition);
		assertThat(pieceAfterMove.getPosition()).isEqualTo(targetPosition);
	}

	@DisplayName("진행 상태의 Pawn은 2칸 이동 불가능")
	@Test
	void move_StateIsRun_ExceptionThrown() {
		Position sourcePosition = Position.of( "b1");
		Position firstTargetPosition = Position.of("b3");
		Position secondTargetPosition = Position.of("b5");

		board.move(sourcePosition, firstTargetPosition, Team.WHITE);

		assertThatThrownBy(() -> board.move(firstTargetPosition, secondTargetPosition, Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		Position sourcePosition = Position.of("a1");
		Position targetPosition = Position.of("a2");

		board.move(sourcePosition, targetPosition, Team.WHITE);

		Piece pieceAfterMove = board.getRanks().get(1).findPiece(targetPosition);
		assertThat(pieceAfterMove.getPosition()).isEqualTo(targetPosition);
	}

	@DisplayName("직선 - 기물이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_PieceAtLinearTargetPosition_ExceptionThrown() {
		assertThatThrownBy(() -> board.move(Position.of("e2"), Position.of("e1"), Team.BLACK))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_AT_TARGET_POSITION);
	}

	@DisplayName("대각선 - 적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtDiagonalTargetPosition_Capture() {
		Position sourcePosition = Position.of("d1");
		Position targetPosition = Position.of("e2");

		board.move(sourcePosition, targetPosition, Team.WHITE);

		Piece targetPiece = board.getRanks().get(1).findPiece(targetPosition);
		assertThat(board.getRanks().get(0).getPieces().contains(targetPiece)).isFalse();
	}

	@DisplayName("대각선 - 아군이 있는 목적지가 입력되면 예외 발생")
	@Test
	void move_OurTeamAtDiagonalTargetPosition_ExceptionThrown() {
		Position sourcePosition = Position.of("e1");
		Position targetPosition = Position.of("f2");

		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}
}