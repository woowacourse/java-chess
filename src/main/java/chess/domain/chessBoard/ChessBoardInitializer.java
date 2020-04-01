package chess.domain.chessBoard;

import java.util.HashMap;
import java.util.Map;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.Knight;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.Bishop;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.Queen;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.Rook;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.pawn.BlackPawn;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.pawn.WhitePawn;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

public class ChessBoardInitializer {

	public static Map<Position, ChessPiece> create() {
		Map<Position, ChessPiece> chessBoard = new HashMap<>();
		setPawn(chessBoard);
		setOthers(chessBoard);
		return chessBoard;
	}

	private static void setPawn(Map<Position, ChessPiece> chessBoard) {
		setWhitePawn(chessBoard, 2);
		setBlackPawn(chessBoard, 7);
	}

	private static void setWhitePawn(Map<Position, ChessPiece> chessBoard, int chessRank) {
		for (ChessFile chessFile : ChessFile.values()) {
			chessBoard.put(Position.of(chessFile, ChessRank.from(chessRank)), new WhitePawn());
		}
	}

	private static void setBlackPawn(Map<Position, ChessPiece> chessBoard, int chessRank) {
		for (ChessFile chessFile : ChessFile.values()) {
			chessBoard.put(Position.of(chessFile, ChessRank.from(chessRank)), new BlackPawn());
		}
	}

	private static void setOthers(Map<Position, ChessPiece> chessBoard) {
		setOthersBy(chessBoard, PieceColor.WHITE, 1);
		setOthersBy(chessBoard, PieceColor.BLACK, 8);
	}

	private static void setOthersBy(Map<Position, ChessPiece> chessBoard, PieceColor pieceColor, int chessRank) {
		chessBoard.put(Position.of(ChessFile.A, ChessRank.from(chessRank)), new Rook(pieceColor));
		chessBoard.put(Position.of(ChessFile.B, ChessRank.from(chessRank)), new Knight(pieceColor));
		chessBoard.put(Position.of(ChessFile.C, ChessRank.from(chessRank)), new Bishop(pieceColor));
		chessBoard.put(Position.of(ChessFile.D, ChessRank.from(chessRank)), new Queen(pieceColor));
		chessBoard.put(Position.of(ChessFile.E, ChessRank.from(chessRank)), new King(pieceColor));
		chessBoard.put(Position.of(ChessFile.F, ChessRank.from(chessRank)), new Bishop(pieceColor));
		chessBoard.put(Position.of(ChessFile.G, ChessRank.from(chessRank)), new Knight(pieceColor));
		chessBoard.put(Position.of(ChessFile.H, ChessRank.from(chessRank)), new Rook(pieceColor));
	}

}
