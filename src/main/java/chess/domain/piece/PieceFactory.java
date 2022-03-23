package chess.domain.piece;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static chess.domain.position.File.EIGHT;
import static chess.domain.position.File.ONE;
import static chess.domain.position.File.SEVEN;
import static chess.domain.position.File.TWO;
import static chess.domain.position.Rank.A;
import static chess.domain.position.Rank.B;
import static chess.domain.position.Rank.C;
import static chess.domain.position.Rank.D;
import static chess.domain.position.Rank.E;
import static chess.domain.position.Rank.F;
import static chess.domain.position.Rank.G;
import static chess.domain.position.Rank.H;
import static java.util.stream.Collectors.toList;

import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieceFactory {

    private PieceFactory() {
    }

    public static List<Piece> whitePieces() {
        List<Piece> pieces = Arrays.stream(Rank.values())
                .map(rank -> new Pawn(WHITE, new Position(rank, TWO)))
                .collect(toList());

        pieces.add(new Rook(WHITE, new Position(A, ONE)));
        pieces.add(new Knight(WHITE, new Position(B, ONE)));
        pieces.add(new Bishop(WHITE, new Position(C, ONE)));
        pieces.add(new Queen(WHITE, new Position(D, ONE)));
        pieces.add(new King(WHITE, new Position(E, ONE)));
        pieces.add(new Bishop(WHITE, new Position(F, ONE)));
        pieces.add(new Knight(WHITE, new Position(G, ONE)));
        pieces.add(new Rook(WHITE, new Position(H, ONE)));

        return pieces;
    }

    public static List<Piece> blackPieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(new Rook(BLACK, new Position(A, EIGHT)));
        pieces.add(new Knight(BLACK, new Position(B, EIGHT)));
        pieces.add(new Bishop(BLACK, new Position(C, EIGHT)));
        pieces.add(new Queen(BLACK, new Position(D, EIGHT)));
        pieces.add(new King(BLACK, new Position(E, EIGHT)));
        pieces.add(new Bishop(BLACK, new Position(F, EIGHT)));
        pieces.add(new Knight(BLACK, new Position(G, EIGHT)));
        pieces.add(new Rook(BLACK, new Position(H,  EIGHT)));

        pieces.addAll(
                Arrays.stream(Rank.values())
                .map(rank -> new Pawn(BLACK, new Position(rank, SEVEN)))
                .collect(toList())
        );

        return pieces;
    }
}
