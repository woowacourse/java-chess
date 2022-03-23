package chess.domain.piece;

import static chess.domain.piece.File.EIGHT;
import static chess.domain.piece.File.ONE;
import static chess.domain.piece.File.SEVEN;
import static chess.domain.piece.File.TWO;
import static chess.domain.piece.Rank.A;
import static chess.domain.piece.Rank.B;
import static chess.domain.piece.Rank.C;
import static chess.domain.piece.Rank.D;
import static chess.domain.piece.Rank.E;
import static chess.domain.piece.Rank.F;
import static chess.domain.piece.Rank.G;
import static chess.domain.piece.Rank.H;
import static chess.domain.piece.Type.BISHOP;
import static chess.domain.piece.Type.KING;
import static chess.domain.piece.Type.KNIGHT;
import static chess.domain.piece.Type.PAWN;
import static chess.domain.piece.Type.QUEEN;
import static chess.domain.piece.Type.ROOK;
import static java.util.stream.Collectors.toList;

import chess.domain.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieceFactory {

    private PieceFactory() {
    }

    public static List<Piece> whitePieces() {
        List<Piece> pieces = Arrays.stream(Rank.values())
                .map(rank -> new Piece(PAWN.getSymbol(), new Position(rank, TWO)))
                .collect(toList());

        pieces.add(new Piece(ROOK.getSymbol(), new Position(A, ONE)));
        pieces.add(new Piece(KNIGHT.getSymbol(), new Position(B, ONE)));
        pieces.add(new Piece(BISHOP.getSymbol(), new Position(C, ONE)));
        pieces.add(new Piece(QUEEN.getSymbol(), new Position(D, ONE)));
        pieces.add(new Piece(KING.getSymbol(), new Position(E, ONE)));
        pieces.add(new Piece(BISHOP.getSymbol(), new Position(F, ONE)));
        pieces.add(new Piece(KNIGHT.getSymbol(), new Position(G, ONE)));
        pieces.add(new Piece(ROOK.getSymbol(), new Position(H, ONE)));

        return pieces;
    }

    public static List<Piece> blackPieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(new Piece(ROOK.getSymbol().toUpperCase(), new Position(A, EIGHT)));
        pieces.add(new Piece(KNIGHT.getSymbol().toUpperCase(), new Position(B, EIGHT)));
        pieces.add(new Piece(BISHOP.getSymbol().toUpperCase(), new Position(C, EIGHT)));
        pieces.add(new Piece(QUEEN.getSymbol().toUpperCase(), new Position(D, EIGHT)));
        pieces.add(new Piece(KING.getSymbol().toUpperCase(), new Position(E, EIGHT)));
        pieces.add(new Piece(BISHOP.getSymbol().toUpperCase(), new Position(F, EIGHT)));
        pieces.add(new Piece(KNIGHT.getSymbol().toUpperCase(), new Position(G, EIGHT)));
        pieces.add(new Piece(ROOK.getSymbol().toUpperCase(), new Position(H,  EIGHT)));

        pieces.addAll(
                Arrays.stream(Rank.values())
                .map(rank -> new Piece(PAWN.getSymbol().toUpperCase(), new Position(rank, SEVEN)))
                .collect(toList())
        );

        return pieces;
    }
}
