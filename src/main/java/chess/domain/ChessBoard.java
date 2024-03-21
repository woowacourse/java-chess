package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Night;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.HashSet;
import java.util.Set;

public class ChessBoard {

    private final Set<Piece> pieces;

    private ChessBoard(final Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public static ChessBoard init() {
        final Set<Piece> pieces = new HashSet<>();

        pieces.addAll((createPieceWithoutPawn(Color.WHITE, 8)));
        pieces.addAll((createPawn(Color.WHITE, 7)));
        pieces.addAll((createPawn(Color.BLACK, 2)));
        pieces.addAll((createPieceWithoutPawn(Color.BLACK, 1)));

        return new ChessBoard(pieces);
    }

    private static Set<Piece> createPawn(final Color color, final int rank) {
        return Set.of(new Pawn(color, new Position('a', rank)),
                new Pawn(color, new Position('b', rank)),
                new Pawn(color, new Position('c', rank)),
                new Pawn(color, new Position('d', rank)),
                new Pawn(color, new Position('e', rank)),
                new Pawn(color, new Position('f', rank)),
                new Pawn(color, new Position('g', rank)),
                new Pawn(color, new Position('h', rank)));
    }

    private static Set<Piece> createPieceWithoutPawn(final Color color, final int rank) {
        return Set.of(new Rook(color, new Position('a', rank)),
                new Night(color, new Position('b', rank)),
                new Bishop(color, new Position('c', rank)),
                new Queen(color, new Position('d', rank)),
                new King(color, new Position('e', rank)),
                new Bishop(color, new Position('f', rank)),
                new Night(color, new Position('g', rank)),
                new Rook(color, new Position('h', rank)));
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public Piece findBy(final Position other) {
        return pieces.stream()
                .filter(piece -> piece.isPosition(other))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 위치에 기물이 존재하지 않습니다."));
    }

    public void move(final Position currentPosition, final Position nextPosition) {
        final Piece currentPiece = findBy(currentPosition);

        if (currentPiece instanceof Pawn) {
            if (canCatch(currentPiece, nextPosition)) {

            }

            return;
        }

        if (!currentPiece.canMoveTo(nextPosition)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치로 이동할 수 없습니다.");
        }
        if (existInWay(currentPiece, nextPosition)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치로 이동할 수 없습니다.");
        }
    }

    private boolean canCatch(final Piece currentPiece, final Position nextPosition) {
        return currentPiece.getPosition().isDiagonalWithDistance(nextPosition, 1) &&
        currentPiece.isOtherColor(findBy(nextPosition));
    }

    private boolean exist(final Position other) {
        return pieces.stream()
                .anyMatch(piece -> piece.isPosition(other));
    }

    private boolean existInWay(final Piece currentPiece, final Position nextPosition) {
        if (exist(nextPosition)) {
           return true;
        }

        final Set<Position> route = currentPiece.getRoute(nextPosition);
        return pieces.stream().anyMatch(piece -> route.contains(piece.getPosition()));
    }
}
