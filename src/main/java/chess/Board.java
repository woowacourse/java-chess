package chess;

import static chess.piece.Team.*;
import static chess.position.File.*;
import static chess.position.Rank.*;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;
import chess.position.File;
import chess.position.Position;

import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void initialize() {
        //Rank 8   R N B Q K B N R   (Black)
        //Rank 7   P P P P P P P P

        //Rank 2   P P P P P P P P  (White)
        //Rank 1   R N B Q K B N R

        pieces.put(Position.of(A, EIGHT), new Rook(BLACK));
        pieces.put(Position.of(B, EIGHT), new Knight(BLACK));
        pieces.put(Position.of(C, EIGHT), new Bishop(BLACK));
        pieces.put(Position.of(D, EIGHT), new Queen(BLACK));
        pieces.put(Position.of(E, EIGHT), new King(BLACK));
        pieces.put(Position.of(F, EIGHT), new Bishop(BLACK));
        pieces.put(Position.of(G, EIGHT), new Knight(BLACK));
        pieces.put(Position.of(H, EIGHT), new Rook(BLACK));
        for (File value : File.values()) {
            pieces.put(Position.of(value, SEVEN), new Pawn(BLACK));
        }


    }

    public void move(Position from, Position to) {
        Piece piece = pieces.get(from);
        List<Position> trace = piece.findReachablePositions(from, to);
        if (isExistAt(trace)) {
        	throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
		Piece target = pieces.remove(from);
		pieces.put(to, target);
    }

    public boolean isExistAt(List<Position> traces) {
        return traces.stream()
                .anyMatch(pieces::containsKey);
    }

    public Piece getPiece(Position position) {
        return pieces.get(position);
    }
}
