package chess.domain.board;

import chess.domain.location.Location;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private final List<Piece> pieces;

    private Board(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board of(final List<Piece> pieces) {
        return new Board(pieces);
    }

    public void move(Location source, Location target, Team currentTurnTeam) {
        validateIsNotSameLocation(source, target);
        Piece sourcePiece = find(source);
        validateSourcePieceIsCurrentTurn(sourcePiece, currentTurnTeam);

        sourcePiece.moveTo(target, this);
    }

    private void validateIsNotSameLocation(Location source, Location target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("[ERROR] 시작 위치와 목표 위치는 같을 수 없습니다.");
        }
    }

    private void validateSourcePieceIsCurrentTurn(Piece sourcePiece, Team currentTurnTeam) {
        if (!sourcePiece.isSameTeam(currentTurnTeam)) {
            throw new IllegalArgumentException("[ERROR] 현재 턴의 말만 움직일 수 있습니다.");
        }
    }

    public boolean isPieceExistIn(Location location) {
        return pieces
            .stream()
            .anyMatch(piece -> piece.isExistIn(location));
    }

    public Piece find(Location location) {
        return pieces
            .stream()
            .filter(piece -> piece.isExistIn(location))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 위치에 체스 말이 존재하지 않습니다."));
    }

    public void remove(Piece targetPiece) {
        pieces.remove(targetPiece);
    }

    public double score(Team team) {
        return scoreExceptionPawn(team) + scorePawn(team);
    }

    private double scoreExceptionPawn(Team team) {
        return pieces
            .stream()
            .filter(piece -> piece.isSameTeam(team))
            .filter(piece -> !piece.isPawn())
            .mapToDouble(piece -> piece.pieceScore().score())
            .sum();
    }

    private double scorePawn(Team team) {
        Map<Integer, Long> frequencyPerX = pieces
            .stream()
            .filter(piece -> piece.isSameTeam(team))
            .filter(piece -> piece.isPawn())
            .collect(Collectors.groupingBy(Piece::getX, Collectors.counting()));

        return frequencyPerX
            .values()
            .stream()
            .mapToDouble(count -> count <= 1 ? count : count * 0.5)
            .sum();
    }

    public List<Piece> toList() {
        return new ArrayList<>(pieces);
    }

    public boolean isAnyKingDead() {
        return pieces
            .stream()
            .filter(Piece::isKing)
            .count() != 2;
    }
}
