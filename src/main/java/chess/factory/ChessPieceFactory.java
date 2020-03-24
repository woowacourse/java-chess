package chess.factory;

import java.util.ArrayList;
import java.util.List;

import chess.Position;
import chess.Team;
import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;

public class ChessPieceFactory {

	public static List<ChessPiece> blackTeamCreate() {
		List<ChessPiece> chessPieces = new ArrayList<>();
		chessPieces.add(new King(new Position(8, 'e'), Team.BLACK));
		chessPieces.add(new Queen(new Position(8, 'd'), Team.BLACK));
		chessPieces.add(new Rook(new Position(8, 'a'), Team.BLACK));
		chessPieces.add(new Rook(new Position(8, 'h'), Team.BLACK));
		chessPieces.add(new Bishop(new Position(8, 'c'), Team.BLACK));
		chessPieces.add(new Bishop(new Position(8, 'f'), Team.BLACK));
		chessPieces.add(new Knight(new Position(8, 'b'), Team.BLACK));
		chessPieces.add(new Knight(new Position(8, 'g'), Team.BLACK));
		for (char col = 'a'; col <= 'h'; col++) {
			chessPieces.add(new Pawn(new Position(7, col), Team.BLACK));
		}
		return chessPieces;
	}

	public static List<ChessPiece> whiteTeamCreate() {
		List<ChessPiece> chessPieces = new ArrayList<>();
		chessPieces.add(new King(new Position(1, 'e'), Team.WHITE));
		chessPieces.add(new Queen(new Position(1, 'd'), Team.WHITE));
		chessPieces.add(new Rook(new Position(1, 'a'), Team.WHITE));
		chessPieces.add(new Rook(new Position(1, 'h'), Team.WHITE));
		chessPieces.add(new Bishop(new Position(1, 'c'), Team.WHITE));
		chessPieces.add(new Bishop(new Position(1, 'f'), Team.WHITE));
		chessPieces.add(new Knight(new Position(1, 'b'), Team.WHITE));
		chessPieces.add(new Knight(new Position(1, 'g'), Team.WHITE));
		for (char col = 'a'; col <= 'h'; col++) {
			chessPieces.add(new Pawn(new Position(2, col), Team.WHITE));
		}
		return chessPieces;
	}
}
