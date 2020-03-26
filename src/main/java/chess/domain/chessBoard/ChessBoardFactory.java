package chess.domain.chessBoard;

import java.util.HashMap;
import java.util.Map;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.PieceColor;
import chess.domain.chessPiece.pieceType.Bishop;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.Knight;
import chess.domain.chessPiece.pieceType.Pawn;
import chess.domain.chessPiece.pieceType.Queen;
import chess.domain.chessPiece.pieceType.Rook;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

// TODO: 2020/03/26 InitialChessBoard로 명명 수정? test는 삭제해도 괜찮을 듯, Factory 패턴에 대해서 찾아보기
public class ChessBoardFactory {
	public static Map<Position, ChessPiece> create() {
		Map<Position, ChessPiece> chessBoard = new HashMap<>();

		addOtherPiecesBy(chessBoard, PieceColor.WHITE, 1);
		addPawnPiecesBy(chessBoard, PieceColor.WHITE, 2);
		addPawnPiecesBy(chessBoard, PieceColor.BLACK, 7);
		addOtherPiecesBy(chessBoard, PieceColor.BLACK, 8);
		return chessBoard;
	}

	private static void addPawnPiecesBy(Map<Position, ChessPiece> chessBoard, PieceColor pieceColor, int rank) {
		for (ChessFile file : ChessFile.values()) {
			chessBoard.put(Position.of(file, ChessRank.from(rank)), ChessPiece.of(pieceColor.convertName(Pawn.NAME)));
		}
	}

	private static void addOtherPiecesBy(Map<Position, ChessPiece> chessBoard, PieceColor pieceColor, int rank) {
		chessBoard.put(Position.of("a" + rank), ChessPiece.of(pieceColor.convertName(Rook.NAME)));
		chessBoard.put(Position.of("b" + rank), ChessPiece.of(pieceColor.convertName(Knight.NAME)));
		chessBoard.put(Position.of("c" + rank), ChessPiece.of(pieceColor.convertName(Bishop.NAME)));
		chessBoard.put(Position.of("d" + rank), ChessPiece.of(pieceColor.convertName(Queen.NAME)));
		chessBoard.put(Position.of("e" + rank), ChessPiece.of(pieceColor.convertName(King.NAME)));
		chessBoard.put(Position.of("f" + rank), ChessPiece.of(pieceColor.convertName(Bishop.NAME)));
		chessBoard.put(Position.of("g" + rank), ChessPiece.of(pieceColor.convertName(Knight.NAME)));
		chessBoard.put(Position.of("h" + rank), ChessPiece.of(pieceColor.convertName(Rook.NAME)));
	}
}
