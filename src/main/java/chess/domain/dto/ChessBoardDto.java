package chess.domain.dto;

import java.util.ArrayList;
import java.util.List;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;

public class ChessBoardDto {
	private static final String BLANK = "blank";

	private List<List<String>> board;
	private String turn;

	private ChessBoardDto(List<List<String>> board, String turn) {
		this.board = board;
		this.turn = turn;
	}

	public static ChessBoardDto of(ChessGame chessGame) {
		List<List<String>> initBoard = initBoard();
		deployPiece(chessGame.getBoard().getPieces(), initBoard);
		String turn = chessGame.getTurn().name();
		return new ChessBoardDto(initBoard, turn);
	}

	private static void deployPiece(List<Piece> board, List<List<String>> initBoard) {
		for (Piece piece : board) {
			initBoard.get(8 - piece.getPosition().getRow().getSymbol())
					.set(piece.getPosition().getCol().getValue() - 1, makeName(piece));
		}
	}

	private static String makeName(Piece piece) {
		String name = piece.getName() + "_" + piece.getSide();
		return name.toLowerCase();
	}

	private static List<List<String>> initBoard() {
		List<List<String>> board = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			List<String> emptyRow = new ArrayList<>();
			for (int j = 0; j < 8; j++) {
				emptyRow.add(BLANK);
			}
			board.add(emptyRow);
		}
		return board;
	}

	public List<List<String>> getBoard() {
		return board;
	}

	public String getTurn() {
		return turn;
	}
}
