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
import domain.board.fixture.RookBoard;
import domain.command.MoveInfo;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class RookTest {
	private BoardGame chessGame;

	@BeforeEach
	void setUp() {
		chessGame = new BoardGame(new RookBoard().create());
	}

	@DisplayName("룩 생성")
	@Test
	void constructor_CreateRook_Success() {
		Assertions.assertThat(new Rook(Position.of("b1"), Team.WHITE)).isInstanceOf(Rook.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"move a1 a1, WHITE", "move a8 a8, BLACK"})
	void canMove_SourceSameAsTarget_ExceptionThrown(MoveInfo moveInfo, Team team) {
		Rook rook = new Rook(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> rook.canMove(moveInfo.getTargetPosition(), team, chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"NE, move a1 b2, WHITE", "NNE, move a3 b5, WHITE", "SW, move a8 b7, BLACK", "SSE, move a8 b6, BLACK"})
	void validateDirection_InvalidDirection_ExceptionThrown(Direction direction, MoveInfo moveInfo, Team team) {
		Rook rook = new Rook(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> rook.validateDirection(direction))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("목적지로 가는 경로에 기물이 있다면 예외 발생")
	@ParameterizedTest
	@CsvSource({"N, move a1 a4, WHITE", "S, move a8 a2, BLACK"})
	void validateRoutecanMove_InvalidTargetPosition_ExceptionThrown(Direction direction, MoveInfo moveInfo, Team team) {
		Rook rook = new Rook(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> rook.validateRoute(direction, moveInfo.getTargetPosition(), chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_PIECE_IN_ROUTE);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		MoveInfo moveInfo = new MoveInfo("move a1 a2");
		Rook rook = new Rook(moveInfo.getSourcePosition(), Team.WHITE);

		rook.move(moveInfo.getTargetPosition(), chessGame.getBoard());

		assertThat(rook.position).isEqualTo(moveInfo.getTargetPosition());
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		MoveInfo moveInfo = new MoveInfo("move a1 a3");

		Rook rook = new Rook(moveInfo.getSourcePosition(), Team.WHITE);

		assertThatThrownBy(() -> rook.move(moveInfo.getTargetPosition(), chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		MoveInfo moveInfo = new MoveInfo("move a3 a8");
		Queen queen = new Queen(moveInfo.getSourcePosition(), Team.WHITE);

		queen.move(moveInfo.getTargetPosition(), chessGame.getBoard());

		Optional<Piece> targetPiece = chessGame.getBoard().findPiece(moveInfo.getTargetPosition());
		assertThat(targetPiece.get()).isEqualTo(queen);
	}

}
