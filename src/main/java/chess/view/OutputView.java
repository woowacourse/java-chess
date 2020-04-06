package chess.view;

import chess.domain.Color;
import chess.domain.GameManager;
import chess.domain.PieceScore;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

/**
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class OutputView {
	public static final String BLANK_POSITION = ".";

	private OutputView() {
	}

	public static void printGameInstruction() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작: start");
		System.out.println("게임 종료: end");
		System.out.println("게임 이동: move source위치 target위치 - 예. move b2 b3");
	}

	public static void printException(Exception e) {
		System.out.println(e.getMessage());
	}

	public static void printGameEnd() {
		System.out.println("게임을 종료합니다.");
	}

	public static void printChessBoard(GameManager gameManager) {
		for (Row row : Row.values()) {
			for (Column column : Column.values()) {
				Position position = Position.of(column, row);
				if (gameManager.getPiece(column.getColumn() + row.getRow()).isBlank()) {
					System.out.print(BLANK_POSITION);
					continue;
				}
				System.out.print(gameManager.getPiece(column.getColumn() + row.getRow()).getSymbol());
			}
			System.out.println();
		}
	}

	public static void printStatus(GameManager gameManager) {
		System.out.println("하얀색 진영의 점수 : " + PieceScore.calculateByColor(gameManager, Color.WHITE));
		System.out.println("검정색 진영의 점수 : " + PieceScore.calculateByColor(gameManager, Color.BLACK));
	}
}
