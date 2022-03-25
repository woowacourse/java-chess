package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> cells = new LinkedHashMap<>();

    public ChessBoard() {
        Map<Position, Piece> pieces = PieceFactory.createPieces();

        cells.putAll(pieces);
    }

    public Map<Position, Piece> getCells() {
        return cells;
    }

    public Team findTeam(Position position) {
        Piece piece = cells.get(position);

        return piece.getTeam();
    }

    public void move(Command command) {
        Map<String, Position> positions = command.makePosition();

        Position source = positions.get("source");

        Position target = positions.get("target");

        Piece piece = cells.get(source);

        piece.move(source, target, this);

        cells.remove(source);

        cells.put(target,piece);
    }
}
