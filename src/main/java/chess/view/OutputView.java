package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.chesspiece.ChessPiece;

public class OutputView {
	public static void printBoard(ChessBoard chessBoard) {
		for (int i = 8; i >= 1; i--) {
		    printRows(i,chessBoard);
			System.out.println();
		}
	}

    private static void printRows(int i, ChessBoard chessBoard) {
        for (char j = 'a'; j <= 'h'; j++) {
            Position position = new Position(i, j);
            System.out.print(getCoordinateName(chessBoard,position));
        }
	}

    private static String getCoordinateName(ChessBoard chessBoard, Position position) {
        String name = ".";
        if (chessBoard.checkPosition(position)) {
            ChessPiece chessPiece = chessBoard.findByPosition(position);
            return getPieceName(chessPiece);
        }
        return name;
    }

    private static String getPieceName(ChessPiece chessPiece) {
        String name = chessPiece.getName();
        if (chessPiece.isBlack()) {
            return name.toUpperCase();
        }
        return name;
    }
}
