package chess.view;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.BlackPieces;
import chess.domain.piece.WhitePieces;

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

	public static void printChessBoard(WhitePieces whitePieces, BlackPieces blackPieces) {
		for (Rank rank : Rank.values()) {
			for (File file : File.values()) {
				Position position = Position.of(file.getFile() + rank.getRow());
				if (whitePieces.hasPiece(position)) {
					System.out.print(whitePieces.getPiece(position).getSymbol());
				}
				if (blackPieces.hasPiece(position)) {
					System.out.print(blackPieces.getPiece(position).getSymbol());
				}
				if (!whitePieces.hasPiece(position) && !blackPieces.hasPiece(position)) {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
}
