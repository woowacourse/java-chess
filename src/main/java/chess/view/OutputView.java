package chess.view;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;

/**
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class OutputView {
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

	public static void printChessBoard(Board board) {
		Map<Position, Piece> chessBoard = board.getBoard();

		for (Rank rank : Rank.values()) {
			for (File file : File.values()) {
				Piece piece = chessBoard.get(Position.of(file.getFile() + rank.getRow()));
				if (piece == null) {
					System.out.print(".");
					continue;
				}
				System.out.print(piece.getSymbol());
			}
			System.out.println();
		}
	}

	public static void printKingDie() {
		System.out.println("왕이 죽었습니다.");
	}
}
