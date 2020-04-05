package chess.domain.dto;

import java.util.ArrayList;
import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;

public class ChessBoardDto {
	private List<List<String>> boardDto;

	private ChessBoardDto(List<List<String>> boardDto) {
		this.boardDto = boardDto;
	}

	public static ChessBoardDto of(ChessBoard board) {
		List<List<String>> initBoard = initBoard();
		deployPiece(board.getPieces(), initBoard);
		return new ChessBoardDto(initBoard);
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
				emptyRow.add("blank");
			}
			board.add(emptyRow);
		}
		return board;
	}

	public List<List<String>> getBoardDto() {
		return boardDto;
	}
}
