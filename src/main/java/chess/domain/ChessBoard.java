package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> cells = new LinkedHashMap<>();

    public ChessBoard() {
        List<Piece> blackPieces = PieceFactory.blackPieces();
        List<Piece> whitePieces = PieceFactory.whitePieces();
        init(blackPieces, whitePieces);
    }

    private void init(List<Piece> blackPieces, List<Piece> whitePieces) {
        for (Piece piece : blackPieces) {
            cells.put(piece.getPosition(), piece);
        }

        for (Piece piece : whitePieces) {
            cells.put(piece.getPosition(), piece);
        }
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

        piece.canMove(source, target);

        cells.remove(source);

        cells.put(target, piece);

    }
}
