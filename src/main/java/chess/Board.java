package chess;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> positions;

    public Board() {
        positions = new HashMap<>();
        positions.put(new Position(Column.D, Row.ONE), new King(Color.BLACK, new Position(Column.D, Row.ONE)));
        positions.put(new Position(Column.E, Row.ONE), new Queen(Color.BLACK, new Position(Column.E, Row.ONE)));
        positions.put(new Position(Column.C, Row.ONE), new Bishop(Color.BLACK, new Position(Column.C, Row.ONE)));
        positions.put(new Position(Column.F, Row.ONE), new Bishop(Color.BLACK, new Position(Column.F, Row.ONE)));
        positions.put(new Position(Column.B, Row.ONE), new Knight(Color.BLACK, new Position(Column.B, Row.ONE)));
        positions.put(new Position(Column.G, Row.ONE), new Knight(Color.BLACK, new Position(Column.G, Row.ONE)));
        positions.put(new Position(Column.A, Row.ONE), new Rook(Color.BLACK, new Position(Column.A, Row.ONE)));
        positions.put(new Position(Column.H, Row.ONE), new Rook(Color.BLACK, new Position(Column.H, Row.ONE)));
        positions.put(new Position(Column.A, Row.TWO), new Pawn(Color.BLACK, new Position(Column.A, Row.TWO)));
        positions.put(new Position(Column.B, Row.TWO), new Pawn(Color.BLACK, new Position(Column.B, Row.TWO)));
        positions.put(new Position(Column.C, Row.TWO), new Pawn(Color.BLACK, new Position(Column.C, Row.TWO)));
        positions.put(new Position(Column.D, Row.TWO), new Pawn(Color.BLACK, new Position(Column.D, Row.TWO)));
        positions.put(new Position(Column.E, Row.TWO), new Pawn(Color.BLACK, new Position(Column.E, Row.TWO)));
        positions.put(new Position(Column.F, Row.TWO), new Pawn(Color.BLACK, new Position(Column.F, Row.TWO)));
        positions.put(new Position(Column.G, Row.TWO), new Pawn(Color.BLACK, new Position(Column.G, Row.TWO)));
        positions.put(new Position(Column.H, Row.TWO), new Pawn(Color.BLACK, new Position(Column.H, Row.TWO)));

        positions.put(new Position(Column.D, Row.EIGHT), new King(Color.WHITE, new Position(Column.D, Row.EIGHT)));
        positions.put(new Position(Column.E, Row.EIGHT), new Queen(Color.WHITE, new Position(Column.E, Row.EIGHT)));
        positions.put(new Position(Column.C, Row.EIGHT), new Bishop(Color.WHITE, new Position(Column.C, Row.EIGHT)));
        positions.put(new Position(Column.F, Row.EIGHT), new Bishop(Color.WHITE, new Position(Column.F, Row.EIGHT)));
        positions.put(new Position(Column.B, Row.EIGHT), new Knight(Color.WHITE, new Position(Column.B, Row.EIGHT)));
        positions.put(new Position(Column.G, Row.EIGHT), new Knight(Color.WHITE, new Position(Column.G, Row.EIGHT)));
        positions.put(new Position(Column.A, Row.EIGHT), new Rook(Color.WHITE, new Position(Column.A, Row.EIGHT)));
        positions.put(new Position(Column.H, Row.EIGHT), new Rook(Color.WHITE, new Position(Column.H, Row.EIGHT)));
        positions.put(new Position(Column.A, Row.SEVEN), new Pawn(Color.WHITE, new Position(Column.A, Row.SEVEN)));
        positions.put(new Position(Column.B, Row.SEVEN), new Pawn(Color.WHITE, new Position(Column.B, Row.SEVEN)));
        positions.put(new Position(Column.C, Row.SEVEN), new Pawn(Color.WHITE, new Position(Column.C, Row.SEVEN)));
        positions.put(new Position(Column.D, Row.SEVEN), new Pawn(Color.WHITE, new Position(Column.D, Row.SEVEN)));
        positions.put(new Position(Column.E, Row.SEVEN), new Pawn(Color.WHITE, new Position(Column.E, Row.SEVEN)));
        positions.put(new Position(Column.F, Row.SEVEN), new Pawn(Color.WHITE, new Position(Column.F, Row.SEVEN)));
        positions.put(new Position(Column.G, Row.SEVEN), new Pawn(Color.WHITE, new Position(Column.G, Row.SEVEN)));
        positions.put(new Position(Column.H, Row.SEVEN), new Pawn(Color.WHITE, new Position(Column.H, Row.SEVEN)));
    }

    public void movePiece(Position start, Position end) {
        validateHavingPieceAt(start);
        Piece piece = positions.get(start);
        if (positions.containsKey(end) && piece.getColor() == positions.get(end).getColor()) {
            throw new IllegalArgumentException("같은 팀 말이 있는 곳으로 갈 수 없습니다.");
        }
        if (piece.isMovableTo(end, positions)) {
            if (positions.containsKey(end)) {
                System.out.println("상대팀 말을 잡았습니다.");
            }
            positions.remove(start);
            positions.put(end, piece.moveTo(end));
        } else {
            System.out.println("움직일 수 없는 위치입니다.");
        }
    }

    public boolean hasPieceAt(Position position) {
    return positions.containsKey(position);
    }

    public Piece getPiece(Position position) {
        return positions.get(position);
    }

    private void validateHavingPieceAt(Position position) {
        if (!hasPieceAt(position)) {
            throw new IllegalArgumentException("해당 위치에 움직일 말이 존재하지 않습니다.");
        }
    }
}
