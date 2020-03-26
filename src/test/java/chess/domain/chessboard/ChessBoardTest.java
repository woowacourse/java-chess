package chess.domain.chessboard;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.Queen;
import chess.domain.chessPiece.position.File;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ChessBoardTest {
	private ChessBoard chessBoard;

	@BeforeEach
	void setUp() {
		chessBoard = new ChessBoard();
	}

	@Test
	@DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : QueenBlack")
	void findPieceByPositionTestQueenBlack() {
		assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.ONE))).isInstanceOf(Queen.class);
	}

	@Test
	@DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : null")
	void findPieceByPositionTestNull() {
		assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.THREE))).isEqualTo(null);
	}

	@Test
	@DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : QueenWhite")
	void findPieceByPositionTestQueenWhite() {
		assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.EIGHT))).isInstanceOf(Queen.class);
	}

	@Test
	@DisplayName("같은팀이 있는 위치로 체스말 이동")
	void movePieceWithError() {
		assertThatThrownBy(() -> chessBoard.movePiece(Position.of("c1"), Position.of("b2")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("해당 칸에 같은 팀의 말이 존재 합니다.");
	}

	@DisplayName("왕이 살아있는경우")
	@Test
	void isSurviveKings1() {
		assertThat(chessBoard.isSurviveKings()).isTrue();
	}

	@DisplayName("왕이 하나 없는 경우")
	@Test
	void isSurviveKings2() {
		Piece blackKing = chessBoard.findPieceByPosition(Position.of("e1"));
		chessBoard.removeAttackedPiece(blackKing);

		assertThat(chessBoard.isSurviveKings()).isFalse();
	}
}
