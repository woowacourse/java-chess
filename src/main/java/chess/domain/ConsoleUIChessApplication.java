package chess.domain;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardFactory;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;

public class ConsoleUIChessApplication {

	public static void main(String[] args) {
		ConsoleOutputView.printChessStart();

		ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());
		ConsoleOutputView.printChessBoard(chessBoard);
		ConsoleInputView.inputChessCommand();
	}

}
