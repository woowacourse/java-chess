package chess.domain;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {
	@Test
	@DisplayName("게임이 종료되지 않았으면 계속 진행되는 지 테스트")
	void move() {
		ChessGame chessGame = new ChessGame(ChessBoardFactory.create());
		chessGame.move(Position.of("a2"), Position.of("a4"));
		assertThat(chessGame.getTurn()).isEqualTo(Side.BLACK);
	}

	@Test
	@DisplayName("게임이 종료될 조건인지 확인")
	void moveAndEnd() {
		List<Piece> pieces = new ArrayList<>(Arrays.asList(
				new King(Side.WHITE, Position.of("d4")),
				new King(Side.BLACK, Position.of("d5"))));
		ChessGame chessGame = new ChessGame(new ChessBoard(pieces));
		chessGame.move(Position.of("d4"), Position.of("d5"));
		assertThat(chessGame.isEnd()).isTrue();
	}

	@Test
	@DisplayName("상태가 종료된 상태인지 확인하는 테스트")
	void isEnd() {
		ChessGame chessGame = new ChessGame(ChessBoardFactory.create());
		assertThat(chessGame.isEnd()).isFalse();
	}

	@Test
	@DisplayName("자기 턴이 아닌 경우에 상대 말을 움직이면 에러 처리")
	void moveWithOtherSidePiece() {
		ChessGame chessGame = new ChessGame(ChessBoardFactory.create());
		assertThatThrownBy(() -> chessGame.move(Position.of("a7"), Position.of("a6")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("본인의 말만 움직일 수 있습니다.");
	}

	@Test
	@DisplayName("게임이 끝내기")
	void end() {
		ChessGame chessGame = new ChessGame(ChessBoardFactory.create());
		chessGame.end();
		assertThat(chessGame.isEnd()).isTrue();
	}
}