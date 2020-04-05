package chess.strategy;

import chess.piece.*;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.HashMap;
import java.util.Map;

import static chess.piece.Team.BLACK;
import static chess.piece.Team.WHITE;
import static chess.position.File.*;
import static chess.position.Rank.*;

public class NormalInitStrategy implements PiecesInitStrategy {
    @Override
    public Map<Position, Piece> init() {
        Map<Position, Piece> pieces = new HashMap<>();

        pieces.put(Position.of(A, EIGHT), new Rook(BLACK));
        pieces.put(Position.of(B, EIGHT), new Knight(BLACK));
        pieces.put(Position.of(C, EIGHT), new Bishop(BLACK));
        pieces.put(Position.of(D, EIGHT), new Queen(BLACK));
        pieces.put(Position.of(E, EIGHT), new King(BLACK));
        pieces.put(Position.of(F, EIGHT), new Bishop(BLACK));
        pieces.put(Position.of(G, EIGHT), new Knight(BLACK));
        pieces.put(Position.of(H, EIGHT), new Rook(BLACK));

        pieces.put(Position.of(A, ONE), new Rook(WHITE));
        pieces.put(Position.of(B, ONE), new Knight(WHITE));
        pieces.put(Position.of(C, ONE), new Bishop(WHITE));
        pieces.put(Position.of(D, ONE), new Queen(WHITE));
        pieces.put(Position.of(E, ONE), new King(WHITE));
        pieces.put(Position.of(F, ONE), new Bishop(WHITE));
        pieces.put(Position.of(G, ONE), new Knight(WHITE));
        pieces.put(Position.of(H, ONE), new Rook(WHITE));

        for (File file : File.valuesExceptNone()) {
            pieces.put(Position.of(file, SEVEN), new Pawn(BLACK));
            pieces.put(Position.of(file, TWO), new Pawn(WHITE));
            Rank.valuesRangeClosed(THREE, SIX)
                    .forEach(rank -> pieces.put(Position.of(file, rank), new EmptyPiece()));
        }
        return pieces;
    }
}
