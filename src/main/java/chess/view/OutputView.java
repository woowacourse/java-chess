package chess.view;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;
import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {
	public static void printOperationsFormat() {
		System.out.println("> 체스 게임을 시작합니다. \n> 게임 시작 : start\n> 게임 종료 : end\n> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public static void printBoard(ChessGame chessGame) {
		printEmptyLine();
		for (Column column : Column.values()) {
			System.out.println(createRowLine(chessGame, column));
		}
		printEmptyLine();
	}

	private static String createRowLine(ChessGame chessGame, Column column) {
		StringBuilder rowLineStringBuilder = new StringBuilder();

		for (Row row : Row.values()) {
			Position position = Board.of(row, column);
			Piece piece = chessGame.getPieces()
					.findBy(position)
					.orElseGet(Blank::new);
			rowLineStringBuilder.append(piece.getName());
		}
		return rowLineStringBuilder.toString();
	}
	public static void printFinishByKingDead(Color color){
		System.out.println(color.getName() + "가 승리하였습니다.");
	}

	private static void printEmptyLine() {
		System.out.println("");
	}

	public static void printFinish() {
		System.out.println("체스 게임을 종료합니다.");
	}

	public static void printScore(Map<Color, Double> scores) {
		for(Color color : scores.keySet()) {
			System.out.println(color.getName() + "의 점수는 " + scores.get(color) + "점 입니다.");
		}
	}
}
