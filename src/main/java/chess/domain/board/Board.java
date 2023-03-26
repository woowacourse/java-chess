package chess.domain.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.game.Team;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PositionPiece;
import chess.domain.position.Position;

public class Board {

    private static final PositionPiece EMPTY_POSITION_PIECE = new PositionPiece(new Position(null, null),
            EmptyPiece.create());
    // TODO: 2023/03/25 null 제거

    private final List<PositionPiece> positionPieces;

    protected Board(Map<Position, Piece> pieces) {
        List<PositionPiece> positionPieces = new ArrayList<>();
        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            positionPieces.add(new PositionPiece(entry.getKey(), entry.getValue()));
        }
        this.positionPieces = positionPieces;
    }

    public void move(Position source, Position target) {
        BoardMap map = new BoardMap(positionPieces);
        positionPieces.removeIf(it -> it.isAt(target));
        getPieceAt2(source).moveTo(target, map);
    }

    public boolean hasPositionTeamOf(Position position, Team team) {
        return getPieceAt2(position).hasTeam(team);
    }

    private PositionPiece getPieceAt2(Position position) {
        return positionPieces.stream()
                .filter(piece -> piece.isAt(position))
                .findFirst()
                .orElse(EMPTY_POSITION_PIECE);
    }

    public Map<Position, Piece> getPieces() {
        Map<Position, Piece> fuckingMap = new HashMap<>();
        for (PositionPiece positionPiece : positionPieces) {
            fuckingMap.put(positionPiece.getPosition(), positionPiece.getPiece());
        }
        return new HashMap<>(fuckingMap);
    }

    public double score(Team team) {
        return positionPieces.stream()
                .filter(it -> it.hasTeam(team))
                .mapToDouble(it -> it.scoreConsidering(new BoardMap(positionPieces)))
                .sum();
    }
}
