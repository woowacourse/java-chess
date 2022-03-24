package chess;

import static chess.position.Rank.*;
import static chess.position.File.*;

import chess.exception.HasObstacleException;
import chess.exception.UnmovableException;
import chess.piece.*;
import chess.position.Position;
import java.util.*;

public class ChessBoard {

    private final List<Piece> pieces;
    private Color currentColor;

    public ChessBoard() {
        pieces = List.of(
            new Rook(Color.BLACK, new Position(A, EIGHT)),
            new Knight(Color.BLACK, new Position(B, EIGHT)),
            new Bishop(Color.BLACK, new Position(C, EIGHT)),
            new Queen(Color.BLACK, new Position(D, EIGHT)),
            new King(Color.BLACK, new Position(E, EIGHT)),
            new Bishop(Color.BLACK, new Position(F, EIGHT)),
            new Knight(Color.BLACK, new Position(G, EIGHT)),
            new Rook(Color.BLACK, new Position(H, EIGHT)),
            new Pawn(Color.BLACK, new Position(A, SEVEN)),
            new Pawn(Color.BLACK, new Position(B, SEVEN)),
            new Pawn(Color.BLACK, new Position(C, SEVEN)),
            new Pawn(Color.BLACK, new Position(D, SEVEN)),
            new Pawn(Color.BLACK, new Position(E, SEVEN)),
            new Pawn(Color.BLACK, new Position(F, SEVEN)),
            new Pawn(Color.BLACK, new Position(G, SEVEN)),
            new Pawn(Color.BLACK, new Position(H, SEVEN)),
            new Rook(Color.WHITE, new Position(A, ONE)),
            new Knight(Color.WHITE, new Position(B, ONE)),
            new Bishop(Color.WHITE, new Position(C, ONE)),
            new Queen(Color.WHITE, new Position(D, ONE)),
            new King(Color.WHITE, new Position(E, ONE)),
            new Bishop(Color.WHITE, new Position(F, ONE)),
            new Knight(Color.WHITE, new Position(G, ONE)),
            new Rook(Color.WHITE, new Position(H, ONE)),
            new Pawn(Color.WHITE, new Position(A, TWO)),
            new Pawn(Color.WHITE, new Position(B, TWO)),
            new Pawn(Color.WHITE, new Position(C, TWO)),
            new Pawn(Color.WHITE, new Position(D, TWO)),
            new Pawn(Color.WHITE, new Position(E, TWO)),
            new Pawn(Color.WHITE, new Position(F, TWO)),
            new Pawn(Color.WHITE, new Position(G, TWO)),
            new Pawn(Color.WHITE, new Position(H, TWO)));
        this.currentColor = Color.WHITE;
    }

    public ChessBoard(List<Piece> pieces, Color color) {
        this.pieces = pieces;
        this.currentColor = color;
    }

    public void move(Position from, Position to) {
        if (isSamePosition(from, to)) {
            throw new IllegalArgumentException(String.format(
                "같은 위치(%s)로 기물을 이동할 수 없습니다.", from));
        }

        if (isNotMovablePieceColor(from)) {
            throw new IllegalArgumentException(String.format(
                "%s에 위치한 기물은 %s 색깔이 아닙니다.", from, currentColor));
        }

        if (isSameColorPiecesByPosition(from, to)) {
            throw new IllegalArgumentException(String.format(
                "%s에서 %s로 기물을 이동할 수 없습니다.", from, to));
        }

        Piece piece = pickPieceByPosition(from);

        if (!piece.isMovablePosition(to)) {
            throw new UnmovableException(String.format(
                "%s의 기물을 %s에서 %s로 이동할 수 없습니다.", piece.getClass().getSimpleName(), from, to));
        }

        if (HasObstacleBetweenPositions(from, to)) {
            throw new HasObstacleException(String.format("%s에서 %s로 기물을 이동할 수 없습니다.", from, to));
        }

        piece.move(to);
        currentColor = currentColor.reverse();
    }

    private boolean HasObstacleBetweenPositions(Position from, Position to) {
        return from.getPath(to).stream()
            .anyMatch(this::hasPieceByPosition);
    }

    private boolean isSamePosition(Position from, Position to) {
        return from.equals(to);
    }

    private boolean isNotMovablePieceColor(Position from) {
        Piece piece = pickPieceByPosition(from);
        return !piece.isSameColor(currentColor);
    }

    private boolean isSameColorPiecesByPosition(Position from, Position to) {
        if (hasPieceByPosition(to)) {
            Piece fromPiece = pickPieceByPosition(from);
            Piece toPiece = pickPieceByPosition(to);
            return fromPiece.isSameColor(toPiece.getColor());
        }
        return false;
    }

    private Piece pickPieceByPosition(Position position) {
        return pieces.stream()
            .filter(piece -> piece.isSamePosition(position))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException(String.format("%s에는 기물이 없습니다.", position)));
    }

    private boolean hasPieceByPosition(Position position) {
        return pieces.stream()
            .anyMatch(piece -> piece.isSamePosition(position));
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
