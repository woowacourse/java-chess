package chess;

import chess.piece.Bishop;
import chess.piece.BlackPawn;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.WhitePawn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessBoard {

    private static final List<Piece> INITIAL_PIECES = List.of(
            new Rook(Color.BLACK, new Position(Column.A, Row.EIGHT)),
            new Knight(Color.BLACK, new Position(Column.B, Row.EIGHT)),
            new Bishop(Color.BLACK, new Position(Column.C, Row.EIGHT)),
            new Queen(Color.BLACK, new Position(Column.D, Row.EIGHT)),
            new King(Color.BLACK, new Position(Column.E, Row.EIGHT)),
            new Bishop(Color.BLACK, new Position(Column.F, Row.EIGHT)),
            new Knight(Color.BLACK, new Position(Column.G, Row.EIGHT)),
            new Rook(Color.BLACK, new Position(Column.H, Row.EIGHT)),
            new BlackPawn(new Position(Column.A, Row.SEVEN)),
            new BlackPawn(new Position(Column.B, Row.SEVEN)),
            new BlackPawn(new Position(Column.C, Row.SEVEN)),
            new BlackPawn(new Position(Column.D, Row.SEVEN)),
            new BlackPawn(new Position(Column.E, Row.SEVEN)),
            new BlackPawn(new Position(Column.F, Row.SEVEN)),
            new BlackPawn(new Position(Column.G, Row.SEVEN)),
            new BlackPawn(new Position(Column.H, Row.SEVEN)),

            new Rook(Color.WHITE, new Position(Column.A, Row.ONE)),
            new Knight(Color.WHITE, new Position(Column.B, Row.ONE)),
            new Bishop(Color.WHITE, new Position(Column.C, Row.ONE)),
            new Queen(Color.WHITE, new Position(Column.D, Row.ONE)),
            new King(Color.WHITE, new Position(Column.E, Row.ONE)),
            new Bishop(Color.WHITE, new Position(Column.F, Row.ONE)),
            new Knight(Color.WHITE, new Position(Column.G, Row.ONE)),
            new Rook(Color.WHITE, new Position(Column.H, Row.ONE)),
            new WhitePawn(new Position(Column.A, Row.TWO)),
            new WhitePawn(new Position(Column.B, Row.TWO)),
            new WhitePawn(new Position(Column.C, Row.TWO)),
            new WhitePawn(new Position(Column.D, Row.TWO)),
            new WhitePawn(new Position(Column.E, Row.TWO)),
            new WhitePawn(new Position(Column.F, Row.TWO)),
            new WhitePawn(new Position(Column.G, Row.TWO)),
            new WhitePawn(new Position(Column.H, Row.TWO))
    );

    private List<Piece> pieces;

    public ChessBoard(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static ChessBoard initialize() {
        return new ChessBoard(new ArrayList<>(INITIAL_PIECES));
    }

    public void move(Color color, Position from, Position to) {
        if (isNotExistPiece(from)) {
            throw new IllegalArgumentException("출발지에 기물이 없습니다.");
        }
        findPiece(from).move(this, color, to);
    }

    public boolean isExistPiece(Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.isPosition(position));
    }

    public boolean isNotExistPiece(Position position) {
        return !isExistPiece(position);
    }

    public boolean isColorInPosition(Color color, Position position) {
        if (isNotExistPiece(position)) {
            return false;
        }
        return findPiece(position).isColor(color);
    }

    public Piece findPiece(Position position) {
        return pieces.stream()
                .filter(piece -> piece.isPosition(position))
                .findFirst()
                .orElse(null);
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public void remove(Position position) {
        pieces = pieces.stream()
                .filter(piece -> !piece.isPosition(position))
                .toList();
    }
}
