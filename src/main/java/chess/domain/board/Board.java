package chess.domain.board;

import chess.domain.location.Location;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private final static double BASIC_SCORE_CRITERION = 1;
    private final static double PAWN_SAME_COL_SCORE = 0.5;

    private final List<Piece> pieces;

    private Board(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board of(final List<Piece> pieces) {
        return new Board(pieces);
    }

    public void move(Location source, Location target, Team team) {
        validateSameLocation(source, target);
        Piece sourcePiece = find(source);
        validateTeam(sourcePiece, team);

        validateMoveCapable(target, sourcePiece);
        validateIsNotSameTeam(target, sourcePiece);
        validateNotExistentInPath(sourcePiece.findPath(target));
        validatePawnMovable(sourcePiece, source, target);

        removeIfExistent(target);
        sourcePiece.move(target);
    }

    private void validateTeam(Piece piece, Team team) {
        if (!piece.isSameTeam(team)) {
            throw new MoveFailureException("상대의 말은 움직일 수 없습니다.");
        }
    }

    private void validateIsNotSameTeam(Location target, Piece piece) {
        if (isExistent(target) && piece.isSameTeam(find(target))) {
            throw new MoveFailureException("목표 위치에 같은 팀의 말이 있습니다.");
        }
    }

    private void validateMoveCapable(Location target, Piece piece) {
        if (!piece.isMovable(target)) {
            throw new MoveFailureException("해당 체스 말은 해당 위치로 이동할 능력이 없습니다.");
        }
    }

    private void validateNotExistentInPath(List<Location> pathToTarget) {
        for (Location location : pathToTarget) {
            if (isExistent(location)) {
                throw new MoveFailureException("이동 경로에 말이 있습니다.");
            }
        }
    }

    private void validateSameLocation(Location source, Location target) {
        if (source.equals(target)) {
            throw new MoveFailureException("현재 말의 위치와 목표 위치는 같을 수 없습니다.");
        }
    }

    private void validatePawnMovable(Piece sourcePiece, Location source, Location target) {
        if (sourcePiece.isPawn()) {
            int subX = source.subtractX(target);
            if (subX == 0 && isExistent(target)) {
                throw new MoveFailureException("폰이 이동할 수 없는 상황 입니다.");
            }
            if (subX != 0 && !isExistent(target)) {
                throw new MoveFailureException("폰이 이동할 수 없는 상황 입니다.");
            }
        }
    }

    public Piece find(Location location) {
        return pieces
            .stream()
            .filter(piece -> piece.areYouHere(location))
            .findFirst()
            .orElseThrow(() -> new MoveFailureException("해당 위치에 체스 말이 존재하지 않습니다."));
    }

    public boolean isExistent(Location location) {
        return pieces
            .stream()
            .anyMatch(piece -> piece.areYouHere(location));
    }

    private void removeIfExistent(Location target) {
        if (isExistent(target)) {
            Piece targetPiece = find(target);
            pieces.remove(targetPiece);
        }
    }

    public double score(Team team) {
        return scoreExceptPawn(team) + scorePawn(team);
    }

    private double scoreExceptPawn(Team team) {
        return pieces
            .stream()
            .filter(piece -> piece.isSameTeam(team))
            .filter(piece -> !piece.isPawn())
            .mapToDouble(piece -> piece.getPieceType().getScore())
            .sum();
    }

    private double scorePawn(Team team) {
        final Map<Integer, Long> frequencyPerX = pieces
            .stream()
            .filter(piece -> piece.isSameTeam(team))
            .filter(Piece::isPawn)
            .collect(Collectors.groupingBy(Piece::getX, Collectors.counting()));

        return frequencyPerX
            .values()
            .stream()
            .mapToDouble(count -> count <= BASIC_SCORE_CRITERION ?
                count :
                count * PAWN_SAME_COL_SCORE)
            .sum();
    }

    public boolean isKingAlive(Team team) {
        return pieces
            .stream()
            .filter(piece -> piece.isSameTeam(team))
            .anyMatch(Piece::isKing);
    }

    public List<Piece> toList() {
        return pieces;
    }
}
