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
import domain.board.fixture.QueenBoard;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class QueenTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board(new QueenBoard().create().getRanks());
	}

	@DisplayName("퀸 생성")
	@Test
	void constructor_CreateQueen_Success() {
		Assertions.assertThat(new Queen(Position.of("b1"), Team.WHITE)).isInstanceOf(Queen.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"e1, WHITE, e1", "c7, BLACK, c7"})
	void canMove_SourceSameAsTarget_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"e1, WHITE, f3", "d2, WHITE, c4", "c7, BLACK, h8", "e5, BLACK, h4"})
	void canMove_InvalidDirection_ExceptionThrown(String sourcePosition, Team team, String targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		String sourcePosition = "e1";
		String targetPosition = "e4";

		board.move(sourcePosition, targetPosition, Team.WHITE);

		Piece pieceAfterMove = board.getRanks().get(3).findPiece(targetPosition);
		assertThat(pieceAfterMove.getPosition()).isEqualTo(Position.of(targetPosition));
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		assertThatThrownBy(() -> board.move("e1", "d2", Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		String sourcePosition = "e1";
		String targetPosition = "e5";
		Rank rank = board.getRanks().get(4);
		Piece targetPiece = rank.findPiece(targetPosition);

		board.move(sourcePosition, targetPosition, Team.WHITE);

		assertThat(board.getRanks().get(2).getPieces().contains(targetPiece)).isFalse();
	}
}
