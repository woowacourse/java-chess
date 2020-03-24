package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.chesspiece.ChessPiece;

public class OutputView {
	public static void printBoard(ChessBoard chessBoard) {
		for (int i = 8; i >= 1; i--) {
			for (char j = 'a'; j <= 'h'; j++) {
				Position position = new Position(i, j);
				String name = ".";
				if (chessBoard.checkPosition(position)) {
					ChessPiece chessPiece = chessBoard.findByPosition(position);
					name = chessPiece.getName();
					if (chessPiece.isBlack()) {
						name = name.toUpperCase();
					}
				}
				System.out.print(name);
			}
			System.out.println();
		}
	}
}
