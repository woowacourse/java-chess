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
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.result.Score;
import chess.team.Team;

class ChessBoardTest {

	private static Stream<Arguments> providerPieces() {
		return Stream.of(
			Arguments.of(
				new HashMap<Location, Piece>() {{
					put(Location.of('a', 1), King.of(Team.WHITE));
					put(Location.of('a', 2), King.of(Team.BLACK));
				}}, true
			), Arguments.of(
				false, new HashMap<Location, Piece>() {{
					put(Location.of('a', 1), Pawn.of(Team.WHITE));
					put(Location.of('a', 2), King.of(Team.BLACK));
				}}
			)
		);
	}

	private static Stream<Arguments> providerBoard() {
		return Stream.of(
			Arguments.of(
				new HashMap<Location, Piece>() {{
					put(Location.of("b8"), King.of(Team.BLACK));
					put(Location.of("c8"), Rook.of(Team.BLACK));
					put(Location.of("a7"), Pawn.of(Team.BLACK));
					put(Location.of("c7"), Pawn.of(Team.BLACK));
					put(Location.of("d7"), Bishop.of(Team.BLACK));
					put(Location.of("b6"), Pawn.of(Team.BLACK));
					put(Location.of("e6"), Queen.of(Team.BLACK));
				}}, Team.BLACK, 20
			),
			Arguments.of(
				new HashMap<Location, Piece>() {{
					put(Location.of("e1"), Rook.of(Team.WHITE));
					put(Location.of("f1"), King.of(Team.WHITE));

					put(Location.of("f2"), Pawn.of(Team.WHITE));
					put(Location.of("g2"), Pawn.of(Team.WHITE));

					put(Location.of("f3"), Pawn.of(Team.WHITE));
					put(Location.of("h3"), Pawn.of(Team.WHITE));

					put(Location.of("f4"), Knight.of(Team.WHITE));
					put(Location.of("g4"), Queen.of(Team.WHITE));
				}}, Team.WHITE, 19.5
			)
		);
	}

	@DisplayName("같은팀으로 이동하는 경우 Exception")
	@Test
	void canMove() {
		Map<Location, Piece> board = new HashMap<>();
		Location starting = Location.of('a', 1);
		Location destination = Location.of('a', 2);
		board.put(starting, Queen.of(Team.BLACK));
		board.put(destination, Queen.of(Team.BLACK));

		ChessBoard chessBoard = new ChessBoard(board);
		assertThatThrownBy(() -> chessBoard.move(starting, destination))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("체스보드에서 내 팀의 정보만 가져오는 기능")
	@Test
	void testGiveMyPiece() {
		Map<Location, Piece> board = new HashMap<>();
		Location bottomLocation = Location.of('a', 1);
		Location topLocation = Location.of('a', 2);
		board.put(bottomLocation, Queen.of(Team.BLACK));
		board.put(topLocation, Queen.of(Team.BLACK));

		Map<Location, Piece> actual = new HashMap<>();
		Location actualBottomLocation = Location.of('a', 1);
		Location actualTopLocation = Location.of('a', 2);
		actual.put(actualBottomLocation, Queen.of(Team.BLACK));
		actual.put(actualTopLocation, Queen.of(Team.BLACK));

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
		Location movingLocation = Location.of('a', 1);
		Location topLocation = Location.of('a', 2);
		board.put(movingLocation, Queen.of(Team.BLACK));
		board.put(topLocation, Queen.of(Team.BLACK));

		ChessBoard chessBoard = new ChessBoard(board);
		boolean actual = chessBoard.isTurn(movingLocation, GameState.RUNNING_BLACK_TURN);

		assertThat(actual).isTrue();
	}

	// abcdefgh
	// .KR.....  8
	// P.PB....  7
	// .P..Q...  6
	// ........  5
	// .....nq.  4
	// .....p.p  3
	// .....pp.  2
	// ....rk..  1
	@DisplayName("결과 계산")
	@ParameterizedTest()
	@MethodSource("providerBoard")
	void calculateScore(Map<Location, Piece> board, Team team, double expect) {
		System.out.println(board.size());
		ChessBoard chessBoard = new ChessBoard(board);
		Score score = chessBoard.calculateScore(team);

		assertThat(score.getAmount()).isEqualTo(expect);
	}
}