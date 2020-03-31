package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.board.Board;
import domain.board.Rank;
import domain.board.fixture.KnightBoard;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;
import javafx.geometry.Pos;

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
	void canMove_SourceSameAsTarget_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"b1, WHITE, a1", "b1, WHITE, c2", "c3, BLACK, d3", "c3, BLACK, d4"})
	void canMove_InvalidDirection_ExceptionThrown(Position sourcePosition, Team team, Position targetPosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, targetPosition, team))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		Position sourcePosition = Position.of("b1");
		Position targetPosition = Position.of("c3");

		board.move(sourcePosition, targetPosition, Team.WHITE);

		Piece pieceAfterMove = board.getRanks().get(2).findPiece(targetPosition);
		assertThat(pieceAfterMove.getPosition()).isEqualTo(targetPosition);
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		assertThatThrownBy(() -> board.move(Position.of("b1"), Position.of("a3"), Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		Position sourcePosition = Position.of("b1");
		Position targetPosition = Position.of("c3");
		Rank rank = board.getRanks().get(2);

		board.move(sourcePosition, targetPosition, Team.WHITE);

		Piece targetPiece = rank.findPiece(targetPosition);
		assertThat(board.getRanks().get(1).getPieces().contains(targetPiece)).isFalse();
	}
}
