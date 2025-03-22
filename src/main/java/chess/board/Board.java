package chess.board;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.Column;
import chess.position.Movement;
import chess.position.Position;
import chess.position.Row;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class Board {

    private final Map<Position, Color> colors;
    private final Map<Position, Piece> pieces;

    {
        // 기본 Empty
        Map<Position, Color> colors = new HashMap<>();
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                colors.put(new Position(row, column), Color.EMPTY);
            }
        }
        this.colors = colors;
    }

    public Board(Map<Position, Color> colors, Map<Position, Piece> pieces) {
        for (Entry<Position, Color> newColorEntry : colors.entrySet()) {
            Position position = newColorEntry.getKey();
            Color color = newColorEntry.getValue();
            this.colors.put(position, color);
        }
        this.pieces = new HashMap<>(pieces);
    }

    public Board() {
        // 색깔 넣기
        for (Column column : Column.values()) {
            // Rank 8, 7 -> Black
            colors.put(new Position(Row.EIGHT, column), Color.BLACK);
            colors.put(new Position(Row.SEVEN, column), Color.BLACK);
            // Rank 2, 1 -> White
            colors.put(new Position(Row.TWO, column), Color.WHITE);
            colors.put(new Position(Row.ONE, column), Color.WHITE);
        }
        // 기물 넣기
        Map<Position, Piece> pieces = new HashMap<>();
        for (Column column : Column.values()) { // 폰
            pieces.put(new Position(Row.SEVEN, column), Pawn.black());
            pieces.put(new Position(Row.TWO, column), Pawn.white());
        }
        for (Row row : List.of(Row.ONE, Row.EIGHT)) { // 폰을 제외한 기물
            pieces.put(new Position(row, Column.A), Rook.create());
            pieces.put(new Position(row, Column.B), Knight.create());
            pieces.put(new Position(row, Column.C), Bishop.create());
            pieces.put(new Position(row, Column.D), Queen.create());
            pieces.put(new Position(row, Column.E), King.create());
            pieces.put(new Position(row, Column.F), Bishop.create());
            pieces.put(new Position(row, Column.G), Knight.create());
            pieces.put(new Position(row, Column.H), Rook.create());
        }
        this.pieces = pieces;
    }

    public void move(Position start, Position end) {
        Piece piece = findPiece(start);
        Color color = colorAt(start);
        validateEndPosition(start, end);
        piece.validateMove(start, end);
        if (piece.type().canBeBlocked()) {
            validateRouteNotBlocked(piece.getValidateMovement(start, end), start, end);
        }
        if (piece.type() == PieceType.PAWN) {
            validatePawn(piece, start, end);
        }
        pieces.put(end, piece);
        colors.put(end, color);
        pieces.remove(start);
        colors.put(start, Color.EMPTY);
        piece.recordMoved();
    }

    private void validatePawn(Piece piece, Position start, Position end) {
        Movement validateMovement = piece.getValidateMovement(start, end);
        if (validateMovement == Movement.UP_UP) {
            if (piece.moved()) {
                throw new IllegalArgumentException("[ERROR] 폰은 첫 움직임에만 두 칸 이동할 수 있습니다.");
            }
            if (colorAt(start.move(Movement.UP)) != Color.EMPTY) {
                throw new IllegalArgumentException("[ERROR] 다른 기물에 의해 막혀서 이동할 수 없는 경로입니다.");
            }
        }
        if (validateMovement == Movement.DOWN_DOWN) {
            if (piece.moved()) {
                throw new IllegalArgumentException("[ERROR] 폰은 첫 움직임에만 두 칸 이동할 수 있습니다.");
            }
            if (colorAt(start.move(Movement.DOWN)) != Color.EMPTY) {
                throw new IllegalArgumentException("[ERROR] 다른 기물에 의해 막혀서 이동할 수 없는 경로입니다.");
            }
        }
        if (validateMovement.isDiagonal() && colorAt(end) == Color.EMPTY) {
            throw new IllegalArgumentException("[ERROR] 해당 위치에 잡을 수 있는 기물이 없습니다.");
        }
    }

    private void validateRouteNotBlocked(Movement movement, Position start, Position end) {
        Position nextPosition = start;
        while (!(nextPosition = nextPosition.move(movement)).equals(end)) { // end 끝까지 가면 끝
            if (pieces.containsKey(nextPosition)) { // 이 위치에 어떤 기물이 있는 경우
                throw new IllegalArgumentException("[ERROR] 다른 기물에 의해 막혀서 이동할 수 없는 경로입니다.");
            }
        }
    }

    private void validateEndPosition(Position start, Position end) {
        if (colorAt(end).isEmpty()) {
            return;
        }
        if (colorAt(end) == colorAt(start)) {
            throw new IllegalArgumentException("[ERROR] 같은 팀의 기물은 잡을 수 없습니다.");
        }
    }

    public Color colorAt(Position position) {
        if (!colors.containsKey(position)) {
            throw new IllegalStateException("[ERROR] 해당 위치의 색깔을 찾을 수 없습니다.");
        }
        return colors.get(position);
    }

    public PieceType pieceTypeAt(Position position) {
        if (!pieces.containsKey(position)) {
            throw new IllegalStateException("[ERROR] 해당 위치에 기물이 없습니다.");
        }
        return pieces.get(position).type();
    }

    private Piece findPiece(Position position) {
        if (!pieces.containsKey(position)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치에 기물이 없습니다.");
        }
        return pieces.get(position);
    }
}
