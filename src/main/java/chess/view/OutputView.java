package chess.view;

import java.util.Optional;

import chess.domain.ChessScore;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class OutputView {

	public void displayGameRule() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("> 게임 시작 : start");
		System.out.println("> 게임 종료 : end");
		System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public void displayChessBoard(Board board) {
		for (int i = 8; i > 0; i--) {
			displayLine(board, i);
			System.out.println();
		}
		System.out.println();
	}

	private void displayLine(Board board, int i) {
		for (int j = 1; j <= 8; j++) {
			displaySymbol(board, i, j);
		}
	}

	private void displaySymbol(Board board, int i, int j) {
		Optional<Piece> nullablePiece = board.findPiece(new Position(i, j));
		if (nullablePiece.isPresent()) {
			System.out.print(nullablePiece.get().getSymbol());
			return;
		}
		System.out.print("ꕤ");
	}

	public void displayErrorMessage(RuntimeException exception) {
		System.out.println(exception.getMessage());
	}

	public void displayScore(ChessScore chessScore) {
		System.out.println("흰색 팀 점수 : " + chessScore.getWhiteScore());
		System.out.println("검은색 팀 점수 : " + chessScore.getBlackScore());
	}
}
