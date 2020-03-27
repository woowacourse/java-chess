package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.function.BiFunction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessBoard;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class ChessGameTest {

	@DisplayName("체스판을 초기화하면 그대로 들어가있는지 테스트")
	@Test
	void initializeBoardTest() {
		ChessBoard chessBoard = new ChessBoard();

		assertThat(chessBoard.getPiece("e1")).isInstanceOf(King.class);
		assertThat(chessBoard.getPiece("d1")).isInstanceOf(Queen.class);
		assertThat(chessBoard.getPiece("b1")).isInstanceOf(Knight.class);
		assertThat(chessBoard.getPiece("g1")).isInstanceOf(Knight.class);
		assertThat(chessBoard.getPiece("c1")).isInstanceOf(Bishop.class);
		assertThat(chessBoard.getPiece("f1")).isInstanceOf(Bishop.class);
		assertThat(chessBoard.getPiece("a1")).isInstanceOf(Rook.class);
		assertThat(chessBoard.getPiece("h1")).isInstanceOf(Rook.class);

		for (File file : File.values()) {
			assertThat(chessBoard.getPiece(file.getFile() + "2")).isInstanceOf(Pawn.class);
		}

		assertThat(chessBoard.getPiece("e8")).isInstanceOf(King.class);
		assertThat(chessBoard.getPiece("d8")).isInstanceOf(Queen.class);
		assertThat(chessBoard.getPiece("b8")).isInstanceOf(Knight.class);
		assertThat(chessBoard.getPiece("g8")).isInstanceOf(Knight.class);
		assertThat(chessBoard.getPiece("c8")).isInstanceOf(Bishop.class);
		assertThat(chessBoard.getPiece("f8")).isInstanceOf(Bishop.class);
		assertThat(chessBoard.getPiece("a8")).isInstanceOf(Rook.class);
		assertThat(chessBoard.getPiece("h8")).isInstanceOf(Rook.class);

		for (File file : File.values()) {
			assertThat(chessBoard.getPiece(file.getFile() + "7")).isInstanceOf(Pawn.class);
		}
	}
}
