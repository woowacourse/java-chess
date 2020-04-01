package chess.state;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import chess.domain.position.Position;

public class BoardRepository {
	private static final Map<Position, Piece> initialBoard = new HashMap<>();

	static {
		for (int i = 'a'; i <= 'h'; i++) {
			initialBoard.put(Position.of((char)i + "2"),
				Pawn.of(Team.WHITE, Position.of((char)i + "2")));
			initialBoard.put(Position.of((char)i + "7"),
				Pawn.of(Team.BLACK, Position.of((char)i + "7")));
		}
		initialBoard.put(Position.of("a1"), Rook.of(Team.WHITE, Position.of("a1")));
		initialBoard.put(Position.of("b1"), Knight.of(Team.WHITE, Position.of("b1")));
		initialBoard.put(Position.of("c1"), Bishop.of(Team.WHITE, Position.of("c1")));
		initialBoard.put(Position.of("d1"), Queen.of(Team.WHITE, Position.of("d1")));
		initialBoard.put(Position.of("e1"), King.of(Team.WHITE, Position.of("e1")));
		initialBoard.put(Position.of("f1"), Bishop.of(Team.WHITE, Position.of("f1")));
		initialBoard.put(Position.of("g1"), Knight.of(Team.WHITE, Position.of("g1")));
		initialBoard.put(Position.of("h1"), Rook.of(Team.WHITE, Position.of("h1")));
		initialBoard.put(Position.of("a8"), Rook.of(Team.BLACK, Position.of("a8")));
		initialBoard.put(Position.of("b8"), Knight.of(Team.BLACK, Position.of("b8")));
		initialBoard.put(Position.of("c8"), Bishop.of(Team.BLACK, Position.of("c8")));
		initialBoard.put(Position.of("d8"), Queen.of(Team.BLACK, Position.of("d8")));
		initialBoard.put(Position.of("e8"), King.of(Team.BLACK, Position.of("e8")));
		initialBoard.put(Position.of("f8"), Bishop.of(Team.BLACK, Position.of("f8")));
		initialBoard.put(Position.of("g8"), Knight.of(Team.BLACK, Position.of("g8")));
		initialBoard.put(Position.of("h8"), Rook.of(Team.BLACK, Position.of("h8")));
	}

	public static Board create() {
		return new Board(initialBoard);
	}
}
