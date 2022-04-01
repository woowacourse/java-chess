package chess.view;

import java.util.Map;

import chess.converter.File;
import chess.converter.Rank;
import chess.controller.dto.BoardDto;
import chess.domain.board.ChessScore;
import chess.domain.piece.Color;

public class OutputView {

	private static final String ENTER = "\n";

	public void displayGameRule() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("> 게임 시작 : start");
		System.out.println("> 게임 종료 : end");
		System.out.println("> 점수 확인 : status");
		System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public void displayChessBoard(BoardDto board) {
		StringBuilder builder = new StringBuilder(ENTER);
		Map<String, String> value = board.getValue();
		for (Rank rank : Rank.reverseValues()) {
			addSymbol(builder, value, rank);
			builder.append(ENTER);
		}
		System.out.println(builder);
	}

	private void addSymbol(StringBuilder builder, Map<String, String> value, Rank rank) {
		for (File file : File.values()) {
			builder.append(value.get(file.getName() + rank.getName()));
		}
	}

	public void displayTurn(Color color) {
		if (color == Color.WHITE) {
			System.out.print("하얀색 차례 - ");
			return;
		}
		System.out.print("검은색 차례 - ");
	}

	public void displayErrorMessage(RuntimeException exception) {
		System.out.println("[ERROR] " + exception.getMessage() + ENTER);
	}

	public void displayScore(ChessScore chessScore) {
		System.out.println("검은색 팀 점수 : " + chessScore.getBlackScore());
		System.out.println("흰색 팀 점수 : " + chessScore.getWhiteScore());
	}
}
