package chess.view;

import java.util.List;

import chess.domain.Result;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Row;
import chess.domain.chesspiece.ChessPiece;

public class OutputView {
	private static final int BOARD_FIRST_INDEX = 0;
	private static final int BOARD_LAST_INDEX = 7;
	private static final String RESULT_FORMAT = "BLACK : %s점, WHITE : %s점";

	public static void printBoard(ChessBoard chessBoard) {
		List<Row> board = chessBoard.getRows();
		for (int column = BOARD_LAST_INDEX; column >= BOARD_FIRST_INDEX; column--) {
			printRow(board.get(column));
			System.out.println();
		}
	}

	private static void printRow(Row row) {
		for (int index = BOARD_FIRST_INDEX; index <= BOARD_LAST_INDEX; index++) {
			ChessPiece chessPiece = row.get(index);
			System.out.print(chessPiece.getName());
		}
	}

	public static void printRule() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작 : start");
		System.out.println("게임 종료 : end");
		System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
		System.out.println("현재 상태 보기 : status");
	}

	public static void printResult(Result result) {
		System.out.println(String.format(RESULT_FORMAT,
			result.getBlackTeamScore(), result.getWhiteTeamScore()));
		System.out.println(result.getWinTeam());
	}

	public static void printErrorMessage(Exception e) {
		System.out.println("예외 발생 : " + e.getMessage());
	}

	public static void printEndGame() {
		System.out.println("게임을 종료합니다.");
	}
}
