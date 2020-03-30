package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.board.Board;
import domain.board.fixture.RookBoard;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class RookTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board(new RookBoard().create().getRanks());
	}

	@DisplayName("룩 생성")
	@Test
	void constructor_CreateRook_Success() {
		Assertions.assertThat(new Rook(Position.of("b1"), Team.WHITE)).isInstanceOf(Rook.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"a1, WHITE, a1", "a8, BLACK, a8"})
	void canMove_SourceSameAsTarget_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"a1, WHITE, b2", "a3, WHITE, f4", "a8, BLACK, b7", "a8, BLACK, g6"})
	void canMove_InvalidDirection_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"a1, WHITE, a4", "a8,BLACK,a2"})
	void canMove_InvalidTargetPosition_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		String sourcePosition = "a1";
		String targetPosition = "a2";

		board.move(sourcePosition, targetPosition, Team.WHITE);

		Piece pieceAfterMove = board.getRanks().get(1).findPiece(targetPosition);
		assertThat(pieceAfterMove.getPosition()).isEqualTo(Position.of(targetPosition));
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		assertThatThrownBy(() -> board.move("a1", "a3", Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		String sourcePosition = "a3";
		String targetPosition = "a8";

		board.move(sourcePosition, targetPosition, Team.WHITE);

		Piece targetPiece = board.getRanks().get(7).findPiece(targetPosition);
		assertThat(board.getRanks().get(2).getPieces().contains(targetPiece)).isFalse();
	}
}
