package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.board.Board;
import domain.board.fixture.KnightBoard;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class KnightTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board(new KnightBoard().create().getRanks());
	}

	@DisplayName("나이트 생성")
	@Test
	void constructor_CreateKnight_Success() {
		assertThat(new Knight(Position.of("b1"), Team.WHITE)).isInstanceOf(Knight.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, b1", "c3, BLACK, c3"})
	void canMove_SourceSameAsTarget_ExceptionThrown(String  sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, a1", "b1, WHITE, c2", "c3, BLACK, d3", "c3, BLACK, d4"})
	void canMove_InvalidDirection_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		String sourcePosition = "b1";
		String targetPosition = "c3";

		board.move(sourcePosition, targetPosition, Team.WHITE);

		Piece pieceAfterMove = board.findPiece(targetPosition, board.getRanks().get(2));
		assertThat(pieceAfterMove.getPosition()).isEqualTo(Position.of(targetPosition));
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		assertThatThrownBy(() -> board.move("b1", "a3", Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		String sourcePosition = "b1";
		String targetPosition = "c3";

		Piece targetPiece = board.findPiece(targetPosition, board.getRanks().get(2));

		board.move(sourcePosition, targetPosition, Team.WHITE);

		assertThat(board.getRanks().get(2).getPieces().contains(targetPiece)).isFalse();
	}

	@DisplayName("적군을 잡은 뒤에 적군이 서있던 위치로 이동")
	@ParameterizedTest
	@CsvSource({"b1, c3, 2, WHITE", "c3, b1, 0, BLACK"})
	void move_CaptureEnemy_MoveToEnemyPosition(String sourcePosition, String targetPosition, int rank, Team team) {
		board.move(sourcePosition, targetPosition, team);
		Piece movedPiece = board.findPiece(targetPosition, board.getRanks().get(rank));

		assertThat(movedPiece.team).isEqualTo(team);
	}

	@DisplayName("적군을 잡은 뒤에 아군이 sourcePosition에서 삭제됐는지 확인")
	@ParameterizedTest
	@CsvSource({"b1, c3, 0, WHITE", "c3, b1, 2, BLACK"})
	void move_CaptureEnemy_DeleteSourcePiece(String sourcePosition, String targetPosition, int rank, Team team) {
		Piece sourcePiece = board.findPiece(sourcePosition, board.getRanks().get(rank));
		assertThat(sourcePiece.team).isEqualTo(team);

		board.move(sourcePosition, targetPosition, team);

		assertThatThrownBy(() -> board.findPiece(sourcePosition, board.getRanks().get(rank)))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessageContaining(InvalidPositionException.INVALID_SOURCE_POSITION);
	}

	@DisplayName("적군을 잡은 뒤에 적군이 board 위에서 삭제됐는지 확인")
	@ParameterizedTest
	@CsvSource({"b1, c3, WHITE", "c3, b1, BLACK"})
	void move_CaptureEnemy_DeleteCapturedPiece(String sourcePosition, String targetPosition, Team team) {
		long beforeBoardSize = board.getRanks().stream()
			.flatMap(rank -> rank.getPieces().stream())
			.count();

		board.move(sourcePosition, targetPosition, team);

		long afterBoardSize = board.getRanks().stream()
			.flatMap(rank -> rank.getPieces().stream())
			.count();

		assertThat(afterBoardSize).isEqualTo(beforeBoardSize - 1);
	}
}
