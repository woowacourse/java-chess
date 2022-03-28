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
                        file -> Position.of(file, TWO), file -> new Pawn(WHITE, "p")
                ));

        pieces.put(Position.of(A, ONE), new Rook(WHITE, "r"));
        pieces.put(Position.of(B, ONE), new Knight(WHITE, "n"));
        pieces.put(Position.of(C, ONE), new Bishop(WHITE, "b"));
        pieces.put(Position.of(D, ONE), new Queen(WHITE, "q"));
        pieces.put(Position.of(E, ONE), new King(WHITE, "k"));
        pieces.put(Position.of(F, ONE), new Bishop(WHITE, "b"));
        pieces.put(Position.of(G, ONE), new Knight(WHITE, "n"));
        pieces.put(Position.of(H, ONE), new Rook(WHITE, "r"));

        return pieces;
    }

    private static Map<Position, Piece> blackPieces() {
        Map<Position, Piece> pieces = Arrays.stream(File.values())
                .collect(toMap(
                        file -> Position.of(file, SEVEN), file -> new Pawn(BLACK, "P")
                ));

        pieces.put(Position.of(A, EIGHT), new Rook(BLACK, "R"));
        pieces.put(Position.of(B, EIGHT), new Knight(BLACK, "N"));
        pieces.put(Position.of(C, EIGHT), new Bishop(BLACK, "B"));
        pieces.put(Position.of(D, EIGHT), new Queen(BLACK, "Q"));
        pieces.put(Position.of(E, EIGHT), new King(BLACK, "K"));
        pieces.put(Position.of(F, EIGHT), new Bishop(BLACK, "B"));
        pieces.put(Position.of(G, EIGHT), new Knight(BLACK, "N"));
        pieces.put(Position.of(H, EIGHT), new Rook(BLACK, "R"));

        return pieces;
    }
}
