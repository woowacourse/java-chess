package view;

import java.util.ArrayList;
import java.util.List;

import domain.Piece;
import domain.PieceType;
import domain.Team;

public class OutputView {
	public void printChessBoard(List<Piece> pieces) {
		List<List<Character>> boardStatus = new ArrayList<>(8);
		for (int i = 0; i < 9; i++) {
			boardStatus.add(new ArrayList<>(List.of(' ', '.', '.', '.', '.', '.', '.', '.', '.')));
		}

		for (Piece piece : pieces) {
			int column = piece.getColumn();
			int row = piece.getRow();
			boardStatus.get(9 - row).set(column, mappingPiece(piece).value);
		}

		for (int i = 1; i < boardStatus.size(); i++) {
			for (Character c : boardStatus.get(i)) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	private PieceAsset mappingPiece(Piece piece) {
		Team pieceTeam = piece.getTeam();
		PieceType pieceType = piece.getPieceType();
		return PieceAsset.valueOf(pieceTeam.name() + "_" + pieceType.name());
	}

	enum PieceAsset {
		BLACK_KING('K'),
		BLACK_QUEEN('Q'),
		BLACK_ROOK('R'),
		BLACK_BISHOP('B'),
		BLACK_KNIGHT('N'),
		BLACK_PAWN('P'),
		WHITE_KING('k'),
		WHITE_QUEEN('q'),
		WHITE_ROOK('r'),
		WHITE_BISHOP('b'),
		WHITE_KNIGHT('n'),
		WHITE_PAWN('p');

		private final char value;

		PieceAsset(char value) {
			this.value = value;
		}
	}
}
