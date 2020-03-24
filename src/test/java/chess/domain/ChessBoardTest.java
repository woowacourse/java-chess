package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.chesspiece.ChessPiece;
import chess.factory.ChessPieceFactory;

public class ChessBoardTest {
	@Test
	@DisplayName("ChessBoard 생성 테스트")
	void create() {
		List<ChessPiece> blackChessPieces = ChessPieceFactory.blackTeamCreate();
		List<ChessPiece> whiteChessPieces = ChessPieceFactory.whiteTeamCreate();
		ChessBoard chessBoard = new ChessBoard(blackChessPieces, whiteChessPieces);
		assertThat(chessBoard).isInstanceOf(ChessBoard.class);
	}
}
