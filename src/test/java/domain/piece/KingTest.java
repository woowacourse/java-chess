package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.board.Board;
import domain.board.fixture.KingBoard;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class KingTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board(new KingBoard().create().getRanks());
	}

	@DisplayName("킹 생성")
	@Test
	void constructor_CreateKing_Success() {
		Assertions.assertThat(new King(Position.of("b1"), Team.WHITE)).isInstanceOf(King.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, b1", "b2, BLACK, b2"})
	void canMove_SourceSameAsTarget_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, c3", "b1, WHITE, d2", "b2, BLACK, c4", "b2, BLACK, d1"})
	void canMove_InvalidDirection_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("말이 움직일 수 없는 칸 수가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, b3", "c2, WHITE, e4", "b2, BLACK, b4"})
	void canMove_InvalidStepSize_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		String sourcePosition = "b1";
		String targetPosition = "c1";

		board.move(sourcePosition, targetPosition, Team.WHITE);
		Piece pieceAfterMove = board.findPiece(targetPosition, board.getRanks().get(0));
		assertThat(pieceAfterMove.getPosition()).isEqualTo(Position.of(targetPosition));
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		assertThatThrownBy(() -> board.move("b1", "c2", Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		String sourcePosition = "b1";
		String targetPosition = "b2";
		Piece targetPiece = board.findPiece(targetPosition, board.getRanks().get(1));

		board.move(sourcePosition, targetPosition, Team.WHITE);

		assertThat(board.getRanks().get(2).getPieces().contains(targetPiece)).isFalse();
	}
}
