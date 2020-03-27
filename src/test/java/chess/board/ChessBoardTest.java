package chess.board;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.score.Score;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.piece.type.Piece;

class ChessBoardTest {
//	@DisplayName("생성자 테스트")
//	@Test
//	void name() {
//		ChessBoard chessBoard = new ChessBoard();
//		assertThat(chessBoard).isInstanceOf(ChessBoard.class);
//	}
//
//	@DisplayName("팀에 따른 모든 말들을 가져온다.")
//	@Test
//	void giveMyPiece() {
//		ChessBoard chessBoard = new ChessBoard();
//		Map<Location, Piece> givenPieces = chessBoard.giveMyPiece(true);
//
//		Map<Location, Piece> actual = new HashMap<>();
//
//		for (PieceType pieceType : PieceType.values()) {
//			makeExpectMap(actual, pieceType);
//		}
//		assertThat(givenPieces).isEqualTo(actual);
//	}
//
//	private void makeExpectMap(Map<Location, Piece> expect, PieceType pieceType) {
//		List<Location> initialLocation = pieceType.reverseInitialLocation();
//
//		for (Location location : initialLocation) {
//			expect.put(location, Piece.of(pieceType, true));
//		}
//	}

    @DisplayName("팀 별로 감점해야하는 폰의 점수를 계산한다.")
    @Test
    void calculateReducePawnScore() {
        ChessBoard chessBoard = new ChessBoard();
        Score reduceScore = chessBoard.calculateReducePawnScore(Team.WHITE);
        assertThat(reduceScore.getValue()).isEqualTo(new Score(4).getValue());
    }
}