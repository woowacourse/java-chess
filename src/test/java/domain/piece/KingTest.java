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
import domain.board.fixture.KingBoard;
import domain.command.MoveInfo;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class KingTest {
	private BoardGame chessGame;

	@BeforeEach
	void setUp() {
		chessGame = new BoardGame(new KingBoard().create());
	}

	@DisplayName("퀸 생성")
	@Test
	void constructor_CreateKing_Success() {
		Assertions.assertThat(new King(Position.of("b1"), Team.WHITE)).isInstanceOf(King.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"move b1 b1, WHITE", "move b2 b2, BLACK"})
	void canMove_SourceSameAsTarget_ExceptionThrown(MoveInfo moveInfo, Team team) {
		King king = new King(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> king.canMove(moveInfo.getTargetPosition(), team, chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"NNE, move b1 c3, WHITE", "NEE, move b1 d2, WHITE", "SWW, move c2 a1, BLACK", "SEE, move b2 d1, BLACK"})
	void validateDirection_InvalidDirection_ExceptionThrown(Direction direction, MoveInfo moveInfo, Team team) {
		King king = new King(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> king.validateDirection(direction))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("말이 움직일 수 없는 칸 수가 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"move b1 b3, WHITE", "move c2 e4, WHITE", "move b2 b4, BLACK"})
	void validateStepSize_InvalidStepSize_ExceptionThrown(MoveInfo moveInfo, Team team) {
		King king = new King(moveInfo.getSourcePosition(), team);

		assertThatThrownBy(() -> king.validateStepSize(moveInfo.getSourcePosition(), moveInfo.getTargetPosition()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_STEP_SIZE);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		MoveInfo moveInfo = new MoveInfo("move b1 c1");
		King king = new King(moveInfo.getSourcePosition(), Team.WHITE);

		king.move(moveInfo.getTargetPosition(), chessGame.getBoard());

		assertThat(king.position).isEqualTo(moveInfo.getTargetPosition());
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		MoveInfo moveInfo = new MoveInfo("move b1 c2");

		King king = new King(moveInfo.getSourcePosition(), Team.WHITE);

		assertThatThrownBy(() -> king.move(moveInfo.getTargetPosition(), chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		MoveInfo moveInfo = new MoveInfo("move b1 b2");
		King king = new King(moveInfo.getSourcePosition(), Team.WHITE);

		king.move(moveInfo.getTargetPosition(), chessGame.getBoard());

		Optional<Piece> targetPiece = chessGame.getBoard().findPiece(moveInfo.getTargetPosition());
		assertThat(targetPiece.get()).isEqualTo(king);
	}
}
