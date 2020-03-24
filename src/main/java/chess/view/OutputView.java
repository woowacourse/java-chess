package chess.view;

import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.piece.Piece;

public class OutputView {
	public static void printGameStartInstruction() {
		System.out.println("체스 게임을 시작합니다.");
	}

	public static void printChessBoard(Pieces pieces) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 8; i > 0; i--) {
			for (int j = 1; j <= 8; j++) {
				Piece piece = pieces.findByPosition(new Position(j, i));
				if (piece == null)
					stringBuilder.append(".");
				else
					stringBuilder.append(piece.toString());
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder.toString());
	}

}
