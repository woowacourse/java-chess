package chess.game;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.view.OutputView;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static chess.domain.game.ChessGame.NO_MOVEMENT_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessGameTest {
	private ChessGame chessGame;

	@BeforeEach
	void setUp() {
		chessGame = new ChessGame(new ChessBoard(), Color.WHITE);
		chessGame.start();
	}

	@Test
	@DisplayName("킹이 죽으면 게임 끝")
	void killKing() {
		chessGame.run(Arrays.asList("move", "b1", "c3"));
		chessGame.run(Arrays.asList("move", "b7", "b6"));
		chessGame.run(Arrays.asList("move", "c3", "d5"));
		chessGame.run(Arrays.asList("move", "b6", "b5"));
		chessGame.run(Arrays.asList("move", "d5", "f6"));
		chessGame.run(Arrays.asList("move", "b5", "b4"));
		chessGame.run(Arrays.asList("move", "f6", "e8"));
		assertThat(chessGame.isOngoing()).isFalse();
	}

	@DisplayName("움직이지 않는 입력이 주어졌을 때 에러를 반환하는지")
	@Test
	void validate_noMovement_throwError() {
		AssertionsForClassTypes.assertThatThrownBy(() -> chessGame.run(Arrays.asList("move", "b2", "b2")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(NO_MOVEMENT_ERROR);
	}

	@Test
	@DisplayName("턴이 아닐 때 오류 발생")
	void validateTurn() {
		ChessGame chessGame = new ChessGame(new ChessBoard(), Color.WHITE);
		chessGame.start();
		assertThatThrownBy(() -> {
			chessGame.run(Arrays.asList("move", "b7", "b6"));
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining(String.format(chessGame.TURN_MESSAGE, Color.WHITE));
	}
}
