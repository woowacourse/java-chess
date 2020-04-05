package chess.db;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.state.Captured;
import chess.domain.piece.state.Initial;
import chess.domain.piece.state.Moved;
import chess.domain.piece.state.State;

/**
 *    디비의 정보와 체스 말을 매핑해주는 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class PieceMapper {
	public static Piece mappingBy(String pieceSymbol, String pieceState) {
		switch (pieceSymbol) {
			case "♚":
				return new King(mappingBy(pieceState), "♚");
			case "♛":
				return new Queen(mappingBy(pieceState), "♛");
			case "♞":
				return new Knight(mappingBy(pieceState), "♞");
			case "♝":
				return new Bishop(mappingBy(pieceState), "♝");
			case "♜":
				return new Rook(mappingBy(pieceState), "♜");
			case "♟":
				return new Pawn(mappingBy(pieceState), "♟");
			case "♔":
				return new King(mappingBy(pieceState), "♔");
			case "♕":
				return new Queen(mappingBy(pieceState), "♕");
			case "♘":
				return new Knight(mappingBy(pieceState), "♘");
			case "♗":
				return new Bishop(mappingBy(pieceState), "♗");
			case "♖":
				return new Rook(mappingBy(pieceState), "♖");
			case "♙":
				return new Pawn(mappingBy(pieceState), "♙");
		}
		return null;
	}

	private static State mappingBy(String pieceState) {
		switch (pieceState) {
			case "Initial":
				return new Initial();
			case "Moved":
				return new Moved();
			case "Captured":
				return new Captured();
		}
		return null;
	}
}
