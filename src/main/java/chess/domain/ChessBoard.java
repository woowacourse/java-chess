package chess.domain;

import static java.util.stream.Collectors.toList;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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


    public boolean isContainPiece(File file, List<Rank> ranks) {
        List<Position> positions = ranks.stream()
                .map(rank -> Position.of(file, rank))
                .collect(toList());

        for (Position position : positions) {
            Set<Position> cellsKeySet = cells.keySet();
            if (cellsKeySet.contains(position)) {
                return true;
            }
        }

        return false;
    }
}
