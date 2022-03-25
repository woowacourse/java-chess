package chess.domain.piece;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.File.C;
import static chess.domain.position.File.D;
import static chess.domain.position.File.E;
import static chess.domain.position.File.F;
import static chess.domain.position.File.G;
import static chess.domain.position.File.H;
import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.TWO;
import static java.util.stream.Collectors.toMap;

import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;

public class PieceFactory {

    private PieceFactory() {
    }

    public static Map<Position, Piece> createPieces() {
        Map<Position, Piece> pieces = blackPieces();

        pieces.putAll(whitePieces());

        return pieces;
    }

    private static Map<Position, Piece> whitePieces() {
        Map<Position, Piece> pieces = Arrays.stream(File.values())
                .collect(toMap(
                         file -> new Position(file, TWO), file -> new Pawn(WHITE, "p")
                ));

        pieces.put(new Position(A, ONE), new Rook(WHITE, "r"));
        pieces.put(new Position(B, ONE), new Knight(WHITE, "n"));
        pieces.put(new Position(C, ONE), new Bishop(WHITE, "b"));
        pieces.put(new Position(D, ONE), new Queen(WHITE, "q"));
        pieces.put(new Position(E, ONE), new King(WHITE, "k"));
        pieces.put(new Position(F, ONE), new Bishop(WHITE, "b"));
        pieces.put(new Position(G, ONE), new Knight(WHITE, "n"));
        pieces.put( new Position(H, ONE), new Rook(WHITE, "r"));

        return pieces;
    }

    private static Map<Position, Piece> blackPieces() {
        Map<Position, Piece> pieces = Arrays.stream(File.values())
                .collect(toMap(
                        file -> new Position(file, SEVEN), file -> new Pawn(BLACK, "P")
                ));

        pieces.put(new Position(A, EIGHT), new Rook(BLACK, "R"));
        pieces.put(new Position(B, EIGHT), new Knight(BLACK, "N"));
        pieces.put(new Position(C, EIGHT), new Bishop(BLACK, "B"));
        pieces.put(new Position(D, EIGHT), new Queen(BLACK, "Q"));
        pieces.put(new Position(E, EIGHT), new King(BLACK, "K"));
        pieces.put(new Position(F, EIGHT), new Bishop(BLACK, "B"));
        pieces.put(new Position(G, EIGHT), new Knight(BLACK, "N"));
        pieces.put(new Position(H,  EIGHT), new Rook(BLACK, "R"));

        return pieces;
    }
}
