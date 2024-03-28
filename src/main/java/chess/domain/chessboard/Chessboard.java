package chess.domain.chessboard;

import static chess.domain.attribute.Color.BLACK;
import static chess.domain.attribute.Color.WHITE;
import static chess.domain.attribute.File.A;
import static chess.domain.attribute.File.B;
import static chess.domain.attribute.File.C;
import static chess.domain.attribute.File.D;
import static chess.domain.attribute.File.E;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.File.G;
import static chess.domain.attribute.File.H;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.ONE;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.TWO;

import chess.domain.attribute.File;
import chess.domain.attribute.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Chessboard {

    private final Map<Square, Piece> chessboard;

    private Chessboard(final Map<Square, Piece> chessboard) {
        this.chessboard = chessboard;
    }


    public static Chessboard createChessBoard() {
        Map<Square, Piece> chessboard = new HashMap<>();
        Set<Piece> pieces = new HashSet<>();
        pieces.addAll(creatWhitePieces());
        pieces.addAll(createBlackPieces());
        for (Piece piece : pieces) {
            chessboard.put(piece.currentSquare(), piece);
        }
        return new Chessboard(chessboard);
    }

    private static Set<Piece> creatWhitePieces() {
        Set<Piece> pieces = new HashSet<>();
        pieces.add(new King(WHITE, Square.of(E, ONE)));
        pieces.add(new Queen(WHITE, Square.of(D, ONE)));
        pieces.add(new Bishop(WHITE, Square.of(F, ONE)));
        pieces.add(new Bishop(WHITE, Square.of(C, ONE)));
        pieces.add(new Knight(WHITE, Square.of(B, ONE)));
        pieces.add(new Knight(WHITE, Square.of(G, ONE)));
        pieces.add(new Rook(WHITE, Square.of(A, ONE)));
        pieces.add(new Rook(WHITE, Square.of(H, ONE)));
        for (File file : File.values()) {
            pieces.add(new WhitePawn(Square.of(file, TWO)));
        }
        return pieces;
    }

    private static Set<Piece> createBlackPieces() {
        Set<Piece> pieces = new HashSet<>();
        pieces.add(new King(BLACK, Square.of(E, EIGHT)));
        pieces.add(new Queen(BLACK, Square.of(D, EIGHT)));
        pieces.add(new Bishop(BLACK, Square.of(F, EIGHT)));
        pieces.add(new Bishop(BLACK, Square.of(C, EIGHT)));
        pieces.add(new Knight(BLACK, Square.of(B, EIGHT)));
        pieces.add(new Knight(BLACK, Square.of(G, EIGHT)));
        pieces.add(new Rook(BLACK, Square.of(A, EIGHT)));
        pieces.add(new Rook(BLACK, Square.of(H, EIGHT)));
        for (File file : File.values()) {
            pieces.add(new BlackPawn(Square.of(file, SEVEN)));
        }
        return pieces;
    }

    public void move(final Square source, final Square target) {
        validateSourceSquareOccupied(source);
        Piece piece = chessboard.get(source);
        Set<Square> movableTargets = new HashSet<>(piece.findLegalMoves(findAllExistPieceOnBoard()));
        if (!movableTargets.contains(target)) {
            throw new IllegalArgumentException("기물이 %s에서 %s로 이동할 수 없습니다.".formatted(source, target));
        }
        piece.moveTo(target);
        movePieceToTarget(source, target);
    }

    private Set<Piece> findAllExistPieceOnBoard() {
        return new HashSet<>(chessboard.values());
    }

    private void validateSourceSquareOccupied(final Square square) {
        if (isBlank(square)) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }
    }

    private void movePieceToTarget(final Square source, final Square target) {
        chessboard.put(target, chessboard.get(source));
        chessboard.remove(source);
    }

    private boolean isBlank(final Square square) {
        return !chessboard.containsKey(square);
    }

    public Map<Square, Piece> getChessboard() {
        return Map.copyOf(chessboard);
    }
}
