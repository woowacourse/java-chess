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
import domain.board.fixture.KnightBoard;
import domain.command.MoveCommand;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class KnightTest {
	private BoardGame chessGame;

	@BeforeEach
	void setUp() {
		chessGame = new BoardGame(new KnightBoard().create());
	}

	@DisplayName("나이트 생성")
	@Test
	void constructor_CreateKnight_Success() {
		Assertions.assertThat(new Knight(Position.of("b1"), Team.WHITE)).isInstanceOf(Knight.class);
	}

	@DisplayName("목적지에 현재 위치가 입력되면(제자리) 예외 발생")
	@ParameterizedTest
	@CsvSource({"move b1 b1, WHITE", "move c3 c3, BLACK"})
	void validateMovement_SourceSameAsTarget_ExceptionThrown(MoveCommand moveCommand, Team team) {
		Knight knight = new Knight(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(() -> knight.validateMovement(moveCommand.getTargetPosition(), team, chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.IS_IN_PLACE);
	}

	@DisplayName("유효하지 않은 방향이 입력되면 예외 발생")
	@ParameterizedTest
	@CsvSource({"W, move b1 a1, WHITE", "E, move b1 c1, WHITE", "NE, move c3 d4, WHITE",
		"S, move c3 c2, WHITE", "N, move c3 c4, BLACK", "SW, move c3 b2, BLACK"})
	void validateDirection_InvalidDirection_ExceptionThrown(Direction direction, MoveCommand moveCommand, Team team) {
		Knight knight = new Knight(moveCommand.getSourcePosition(), team);

		assertThatThrownBy(() -> knight.validateDirection(direction))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_DIRECTION);
	}

	@DisplayName("기물이 없는 목적지가 입력되면 말 이동")
	@Test
	void move_EmptyTargetPosition_Success() {
		MoveCommand moveCommand = new MoveCommand("move b1 c3");
		Knight knight = new Knight(moveCommand.getSourcePosition(), Team.WHITE);

		knight.move(moveCommand.getTargetPosition(), Team.WHITE, chessGame.getBoard());

		assertThat(knight.position).isEqualTo(moveCommand.getTargetPosition());
	}

	@DisplayName("아군이 있는 목적지가 입력되면 예외 발생 ")
	@Test
	void move_OurTeamAtTargetPosition_ExceptionThrown() {
		MoveCommand moveCommand = new MoveCommand("move b1 a3");

		Knight knight = new Knight(moveCommand.getSourcePosition(), Team.WHITE);

		assertThatThrownBy(() -> knight.move(moveCommand.getTargetPosition(), Team.WHITE, chessGame.getBoard()))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.HAS_OUR_TEAM_AT_TARGET_POSITION);
	}

	@DisplayName("적군이 있는 목적지가 입력되면 적군을 잡고 말 이동 ")
	@Test
	void move_EnemyAtTargetPosition_Capture() {
		MoveCommand moveCommand = new MoveCommand("move b1 c3");
		Knight knight = new Knight(moveCommand.getSourcePosition(), Team.WHITE);

		knight.move(moveCommand.getTargetPosition(), Team.WHITE, chessGame.getBoard());

		Optional<Piece> targetPiece = chessGame.getBoard().findPiece(moveCommand.getTargetPosition());
		assertThat(targetPiece.get()).isEqualTo(knight);
	}
}
