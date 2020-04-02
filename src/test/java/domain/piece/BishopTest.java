package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.board.Board;
import domain.board.fixture.BishopBoard;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class BishopTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board(new BishopBoard().create().getRanks());
	}

	@DisplayName("비숍 생성")
	@Test
	void constructor_CreateBishop_Success() {
		Assertions.assertThat(new Bishop(Position.of("b1"), Team.WHITE)).isInstanceOf(Bishop.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, b1", "c2, BLACK, c2"})
	void canMove_SourceSameAsTarget_ExceptionThrown(String  sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, b2", "b1, WHITE, a1", "b1, WHITE, c1", "c2, BLACK, c1", "c2, BLACK, d4"})
	void canMove_InvalidDirection_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, e4", "d3, BLACK , b1"})
	void canMove_InvalidTargetPosition_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		String sourcePosition = "d2";
		String targetPosition = "f4";

		board.move(sourcePosition, targetPosition, Team.WHITE);
		Piece pieceAfterMove = board.findPiece(targetPosition, board.getRanks().get(3));
		assertThat(pieceAfterMove.getPosition()).isEqualTo(Position.of(targetPosition));
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		assertThatThrownBy(() -> board.move("c2", "d3", Team.BLACK))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		String sourcePosition = "b1";
		String targetPosition = "c2";
		Piece targetPiece = board.findPiece(targetPosition, board.getRanks().get(1));

		board.move(sourcePosition, targetPosition, Team.WHITE);

		assertThat(board.getRanks().get(2).getPieces().contains(targetPiece)).isFalse();
	}

	@DisplayName("적군을 잡은 뒤에 적군이 서있던 위치로 이동")
	@Test
	void move_CaptureEnemy_MoveToEnemyPosition() {
		String sourcePosition = "b1";
		String targetPosition = "c2";

		board.move(sourcePosition, targetPosition, Team.WHITE);
		Piece movedPiece = board.findPiece(targetPosition, board.getRanks().get(1));

		assertThat(movedPiece.team).isEqualTo(Team.WHITE);
	}

	@DisplayName("적군을 잡은 뒤에 아군이 sourcePosition에서 삭제됐는지 확인")
	@Test
	void move_CaptureEnemy_DeleteSourcePiece() {
		String sourcePosition = "b1";
		String targetPosition = "c2";

		Piece sourcePiece = board.findPiece(sourcePosition, board.getRanks().get(0));
		assertThat(sourcePiece.team).isEqualTo(Team.WHITE);

		board.move(sourcePosition, targetPosition, Team.WHITE);

		assertThatThrownBy(() -> board.findPiece(sourcePosition, board.getRanks().get(0)))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessageContaining(InvalidPositionException.INVALID_SOURCE_POSITION);
	}

	@DisplayName("적군을 잡은 뒤에 적군이 board 위에서 삭제됐는지 확인")
	@Test
	void move_CaptureEnemy_DeleteCapturedPiece() {
		String sourcePosition = "b1";
		String targetPosition = "c2";

		long beforeBoardSize = board.getRanks().stream()
			.flatMap(rank -> rank.getPieces().stream())
			.count();

		board.move(sourcePosition, targetPosition, Team.WHITE);

		long afterBoardSize = board.getRanks().stream()
			.flatMap(rank -> rank.getPieces().stream())
			.count();

		assertThat(afterBoardSize).isEqualTo(beforeBoardSize - 1);
	}
}
