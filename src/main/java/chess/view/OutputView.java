package chess.view;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.game.Result;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.Map;

import static chess.domain.board.ChessBoard.BOARD_SIZE;

public class OutputView {
	private static final String RESULT_FORMAT = "%s: %.1f점 - %s%n";

	private OutputView() {
	}

	public static void gameStart() {
		System.out.println("> 체스 게임을 시작합니다.");
		System.out.println("> 게임 시작 : start");
		System.out.println("> 게임 종료: end");
		System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public static void printChessBoard(ChessBoard chessBoard) {
		Map<Position, Piece> board = chessBoard.getChessBoard();
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				Piece piece = board.get(Position.of(i, j));
				System.out.print(piece.getName());
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printResult(Result result) {
		Map<Color, Double> score = result.getResult();
		Map<Color, String> winOrLose = result.getWinOrLose();
		System.out.printf(RESULT_FORMAT,
				Color.BLACK.name(), score.get(Color.BLACK), winOrLose.get(Color.BLACK));
		System.out.printf(RESULT_FORMAT,
				Color.WHITE.name(), score.get(Color.WHITE), winOrLose.get(Color.WHITE));
		System.out.println();
	}
}
