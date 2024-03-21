package chess.domain.chessboard;

import static chess.domain.attribute.Color.BLACK;
import static chess.domain.attribute.Color.WHITE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;

public class  Chessboard {

    private final Map<Square, Piece> chessboard;

    private Chessboard(final Map<Square, Piece> chessboard) {
        this.chessboard = chessboard;
    }

    public static Chessboard create() {
        Map<Square, Piece> chessboard = new HashMap<>();
        Set<Piece> pieces = new HashSet<>();
        pieces.addAll(createPieces(WHITE));
        pieces.addAll(createPieces(BLACK));
        pieces.forEach(piece -> putPieces(chessboard, piece));
        return new Chessboard(chessboard);
    }

    private static void putPieces(final Map<Square, Piece> chessboard, final Piece piece) {
        PieceType pieceType = piece.getPieceType();
        for (int index = 0; index < pieceType.getCount(); index++) {
            Square square = pieceType.startSquareOf(piece.getColor(), index);
            chessboard.put(square, piece);
        }
    }

    private static Set<Piece> createPieces(final Color color) {
        Set<Piece> pieces = new HashSet<>();
        pieces.add(new King(color));
        pieces.add(new Queen(color));
        pieces.add(new Bishop(color));
        pieces.add(new Knight(color));
        pieces.add(new Rook(color));
        pieces.add(new StartingPawn(color));
        return pieces;
    }

    public Piece pieceOf(final Square square) {
        return chessboard.get(square);
    }

    public void move(final Square source, final Square target) {
        validateOccupied(source);
        Piece piece = chessboard.get(source);
        Set<Square> movableTargets = new HashSet<>(piece.movableSquaresFrom(source));
        removeSquaresOccupiedByAlly(movableTargets, piece);
        validateHasAnyMovable(movableTargets);
        movePieceToSquare(movableTargets, source, target);
    }

    private void removeSquaresOccupiedByAlly(final Set<Square> movableTargets, final Piece piece) {
        movableTargets.removeIf(movableTarget -> isOccupiedByAlly(piece, movableTarget));
    }

    private boolean isOccupiedByAlly(final Piece piece, final Square movableTarget) {
        if (chessboard.containsKey(movableTarget)) {
            Piece existPiece = chessboard.get(movableTarget);
            return existPiece.isAllyOf(piece);
        }
        return false;
    }

    private void validateOccupied(final Square square) {
        if (!isOccupied(square)) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }
    }

    private void validateHasAnyMovable(final Set<Square> movableTargets) {
        if (movableTargets.isEmpty()) {
            throw new IllegalArgumentException("이동할 수 있는 칸이 없습니다.");
        }
    }

    private void movePieceToSquare(final Set<Square> movableTargets, final Square source, final Square target) {
        if (movableTargets.contains(target)) {
            Piece tempPiece = chessboard.get(source);
            chessboard.remove(source);
            chessboard.put(target, tempPiece);
        }
    }
    private boolean isOccupied(final Square square) {
        return chessboard.containsKey(square);
    }

    public Map<Square, Piece> getChessboard() {
        return Map.copyOf(chessboard);
    }
}
