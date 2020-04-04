package chess.domain.chessboard;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Rook;
import chess.domain.position.Position;
import chess.factory.BoardFactory;
import chess.view.OutputView;

public class ChessBoardTest {
	private ChessBoard chessBoard;

	@BeforeEach
	void setUp() {
		chessBoard = BoardFactory.createBoard();
	}

	@Test
	void printTest() {
		OutputView.printBoard(chessBoard);
	}

	@Test
	void findByPositionTest() {
		ChessPiece rook = chessBoard.findByPosition(Position.of(1, 1));
		ChessPiece pawn = chessBoard.findByPosition(Position.of(2, 2));

		assertThat(rook).isEqualTo(new Rook(Position.of(1, 1), Team.WHITE));
		assertThat(pawn).isEqualTo(new Pawn(Position.of(2, 2), Team.WHITE));
	}

	@Test
	void moveTest() {
		Position sourcePosition = Position.of(2, 2);
		Position targetPosition = Position.of(3, 2);

		chessBoard.move(sourcePosition, targetPosition);

		assertThat(chessBoard.findByPosition(sourcePosition)).isEqualTo(new Blank(sourcePosition));
		assertThat(chessBoard.findByPosition(targetPosition)).isEqualTo(new Pawn(targetPosition, Team.WHITE));

	}
}
