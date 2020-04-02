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
import domain.board.fixture.QueenBoard;
import domain.command.MoveInfo;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class QueenTest {
	private BoardGame chessGame;

	@BeforeEach
	void setUp() {
		chessGame = new BoardGame(new QueenBoard().create());
	}

	@DisplayName("퀸 생성")
	@Test
	void constructor_CreateQueen_Success() {
		Assertions.assertThat(new Queen(Position.of("b1"), Team.WHITE)).isInstanceOf(Queen.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"move e1 e1, WHITE", "move c7 c7, BLACK"})
	void canMove_SourceSameAsTarget_ExceptionThrown(MoveInfo moveInfo, Team team) {
		Queen queen = new Queen(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> queen.canMove(moveInfo.getTargetPosition(), team, chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"NNE,move e1 f3, WHITE", "NNW,move d2 c4, WHITE", "SSE, move c7 d5, BLACK", "SSW, move e5 d3, BLACK"})
	void validateDirection_InvalidDirection_ExceptionThrown(Direction direction, MoveInfo moveInfo, Team team) {
		Queen queen = new Queen(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> queen.validateDirection(direction))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		MoveInfo moveInfo = new MoveInfo("move e1 e4");
		Queen queen = new Queen(moveInfo.getSourcePosition(), Team.WHITE);

		queen.move(moveInfo.getTargetPosition(), chessGame.getBoard());

		assertThat(queen.position).isEqualTo(moveInfo.getTargetPosition());
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		MoveInfo moveInfo = new MoveInfo("move e1 d2");

		Queen queen = new Queen(moveInfo.getSourcePosition(), Team.WHITE);

		assertThatThrownBy(() -> queen.move(moveInfo.getTargetPosition(), chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		MoveInfo moveInfo = new MoveInfo("move e1 e5");
		Queen queen = new Queen(moveInfo.getSourcePosition(), Team.WHITE);

		queen.move(moveInfo.getTargetPosition(), chessGame.getBoard());

		Optional<Piece> targetPiece = chessGame.getBoard().findPiece(moveInfo.getTargetPosition());
		assertThat(targetPiece.get()).isEqualTo(queen);
	}
}
