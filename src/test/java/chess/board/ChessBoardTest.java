package chess.board;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.gamestate.GameState;
import chess.piece.King;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.team.Team;

class ChessBoardTest {

	private static Stream<Arguments> providerPieces() {
		return Stream.of(
			Arguments.of(
				new HashMap<Location, Piece>() {{
					put(new Location(1, 'a'), new King(Team.WHITE));
					put(new Location(2, 'a'), new King(Team.BLACK));
				}}, true
			), Arguments.of(
				new HashMap<Location, Piece>() {{
					put(new Location(1, 'a'), new Pawn(Team.WHITE));
					put(new Location(2, 'a'), new King(Team.BLACK));
				}}, false
			)
		);
	}

	@DisplayName("같은팀으로 이동하는 경우 Exception")
	@Test
	void canMove() {
		Map<Location, Piece> board = new HashMap<>();
		Location starting = new Location(1, 'a');
		Location destination = new Location(2, 'a');
		board.put(starting, new Queen(Team.BLACK));
		board.put(destination, new Queen(Team.BLACK));

		ChessBoard chessBoard = new ChessBoard(board);
		assertThatThrownBy(() -> chessBoard.canMove(starting, destination))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("체스보드에서 내 팀의 정보만 가져오는 기능")
	@Test
	void testGiveMyPiece() {
		Map<Location, Piece> board = new HashMap<>();
		Location bottomLocation = new Location(1, 'a');
		Location topLocation = new Location(2, 'a');
		board.put(bottomLocation, new Queen(Team.BLACK));
		board.put(topLocation, new Queen(Team.BLACK));

		Map<Location, Piece> actual = new HashMap<>();
		Location actualBottomLocation = new Location(1, 'a');
		Location actualTopLocation = new Location(2, 'a');
		actual.put(actualBottomLocation, new Queen(Team.BLACK));
		actual.put(actualTopLocation, new Queen(Team.BLACK));

		ChessBoard chessBoard = new ChessBoard(board);
		Map<Location, Piece> giveMyPiece = chessBoard.giveMyPiece(Team.BLACK);

		assertThat(giveMyPiece).isEqualTo(actual);
	}

	@DisplayName("체스 보드에 왕이 2개 있는지 확인")
	@ParameterizedTest
	@MethodSource("providerPieces")
	void hasTwoKings(Map<Location, Piece> board, boolean expect) {
		ChessBoard chessBoard = new ChessBoard(board);
		boolean actual = chessBoard.hasTwoKings();
		assertThat(actual).isEqualTo(expect);
	}

	@DisplayName("체스보드에서 이동할 피스가 같은팀인지 확인")
	@Test
	void isTurn() {
		Map<Location, Piece> board = new HashMap<>();
		Location movingLocation = new Location(1, 'a');
		Location topLocation = new Location(2, 'a');
		board.put(movingLocation, new Queen(Team.BLACK));
		board.put(topLocation, new Queen(Team.BLACK));

		ChessBoard chessBoard = new ChessBoard(board);
		boolean actual = chessBoard.isTurn(movingLocation, GameState.RUNNING_BLACK_TURN);

		assertThat(actual).isTrue();
	}
}