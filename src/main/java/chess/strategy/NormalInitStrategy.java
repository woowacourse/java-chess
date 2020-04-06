package chess.strategy;

import chess.piece.*;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.HashMap;
import java.util.Map;

import static chess.piece.PieceType.*;
import static chess.piece.Team.BLACK;
import static chess.piece.Team.WHITE;
import static chess.position.File.*;
import static chess.position.Rank.*;

public class NormalInitStrategy implements PiecesInitStrategy {
    @Override
    public Map<Position, Piece> init() {
        Map<Position, Piece> pieces = new HashMap<>();

        pieces.put(Position.of(A, EIGHT), new Piece(BLACK, ROOK));
        pieces.put(Position.of(B, EIGHT), new Piece(BLACK, KNIGHT));
        pieces.put(Position.of(C, EIGHT), new Piece(BLACK, BISHOP));
        pieces.put(Position.of(D, EIGHT), new Piece(BLACK, QUEEN));
        pieces.put(Position.of(E, EIGHT), new Piece(BLACK, KING));
        pieces.put(Position.of(F, EIGHT), new Piece(BLACK, BISHOP));
        pieces.put(Position.of(G, EIGHT), new Piece(BLACK, KNIGHT));
        pieces.put(Position.of(H, EIGHT), new Piece(BLACK, ROOK));

        pieces.put(Position.of(A, ONE), new Piece(WHITE, ROOK));
        pieces.put(Position.of(B, ONE), new Piece(WHITE, KNIGHT));
        pieces.put(Position.of(C, ONE), new Piece(WHITE, BISHOP));
        pieces.put(Position.of(D, ONE), new Piece(WHITE, QUEEN));
        pieces.put(Position.of(E, ONE), new Piece(WHITE, KING));
        pieces.put(Position.of(F, ONE), new Piece(WHITE, BISHOP));
        pieces.put(Position.of(G, ONE), new Piece(WHITE, KNIGHT));
        pieces.put(Position.of(H, ONE), new Piece(WHITE, ROOK));

        for (File file : File.valuesExceptNone()) {
            pieces.put(Position.of(file, SEVEN), new Piece(BLACK, PAWN));
            pieces.put(Position.of(file, TWO), new Piece(WHITE, PAWN));
            Rank.valuesRangeClosed(THREE, SIX)
                    .forEach(rank -> pieces.put(Position.of(file, rank), new Piece(Team.NONE, PieceType.NONE)));
        }
        return pieces;
    }
}
