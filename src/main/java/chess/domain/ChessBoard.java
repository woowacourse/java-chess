package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> cells = new LinkedHashMap<>();

    public ChessBoard(List<Piece> blackPieces, List<Piece> whitePieces) {
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

    public int countPieces() {
        return cells.size();
    }

    public Map<Position, Piece> getCells() {
        return cells;
    }

    public void movePiece(Position source, Position target) {
        Piece piece = cells.get(source);

        isInPath(source, target);

        piece.canMove(source, target);

        move(source, target, piece);
    }

    private void isInPath(Position source, Position target) {
        Piece piece = cells.get(source);
        Team team = piece.getTeam();

        Piece targetPiece = cells.get(target);
        Team targetTeam = targetPiece.getTeam();

        if (team == targetTeam) {
            throw new IllegalArgumentException();
        }

        if (team != targetTeam) {
            cells.remove(target);
        }

        // "target" 까지 가는 경로 상에는 상대 팀이든 같은 팀이든 기물이 있으면 안됌
    }

    private void move(Position source, Position target, Piece piece) {
        cells.remove(source);
        cells.put(target, piece);
    }
}
