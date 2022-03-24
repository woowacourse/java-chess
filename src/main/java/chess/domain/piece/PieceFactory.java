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
import static java.util.stream.Collectors.toList;

import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieceFactory {

    private PieceFactory() {
    }

    public static List<Piece> whitePieces() {
        List<Piece> pieces = Arrays.stream(File.values())
                .map(file -> new Pawn(WHITE, "p", new Position(file, TWO)))
                .collect(toList());

        pieces.add(new Rook(WHITE, "r", new Position(A, ONE)));
        pieces.add(new Knight(WHITE, "n", new Position(B, ONE)));
        pieces.add(new Bishop(WHITE, "b", new Position(C, ONE)));
        pieces.add(new Queen(WHITE, "q", new Position(D, ONE)));
        pieces.add(new King(WHITE, "k", new Position(E, ONE)));
        pieces.add(new Bishop(WHITE, "b", new Position(F, ONE)));
        pieces.add(new Knight(WHITE, "n", new Position(G, ONE)));
        pieces.add(new Rook(WHITE, "r", new Position(H, ONE)));

        return pieces;
    }

    public static List<Piece> blackPieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(new Rook(BLACK, "R", new Position(A, EIGHT)));
        pieces.add(new Knight(BLACK, "N", new Position(B, EIGHT)));
        pieces.add(new Bishop(BLACK, "B", new Position(C, EIGHT)));
        pieces.add(new Queen(BLACK, "Q", new Position(D, EIGHT)));
        pieces.add(new King(BLACK, "K", new Position(E, EIGHT)));
        pieces.add(new Bishop(BLACK, "B", new Position(F, EIGHT)));
        pieces.add(new Knight(BLACK, "N", new Position(G, EIGHT)));
        pieces.add(new Rook(BLACK, "R", new Position(H,  EIGHT)));

        pieces.addAll(
                Arrays.stream(File.values())
                .map(file -> new Pawn(BLACK, "P", new Position(file, SEVEN)))
                .collect(toList())
        );

        return pieces;
    }
}
