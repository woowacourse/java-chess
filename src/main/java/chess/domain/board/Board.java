package chess.domain.board;

import chess.domain.location.Location;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.team.Team;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Piece> pieces;

    private Board(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board of(final List<Piece> pieces) {
        return new Board(pieces);
    }

    public static Board createWithInitialLocation() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(Rook.createInitialPieces());
        pieces.addAll(Knight.createInitialPieces());
        pieces.addAll(Bishop.createInitialPieces());
        pieces.addAll(King.createInitialPieces());
        pieces.addAll(Queen.createInitialPieces());
        pieces.addAll(Pawn.createInitialPieces());
        return new Board(pieces);
    }

    public void validate(final Location source, final Location target, final Team team) {
        validateSameLocation(source, target);
        final Piece sourcePiece = find(source);
        validateTeam(sourcePiece, team);

        validateMoveCapable(target, sourcePiece);
        validateIsNotSameTeam(target, sourcePiece);
        validateNotExistentInPath(sourcePiece.findPath(target));
        validatePawnMovable(sourcePiece, source, target);
    }

    public void move(final Location source, final Location target, final Team team) {
        validate(source, target, team);
        final Piece sourcePiece = find(source);
        removeIfExistent(target);
        sourcePiece.move(target);
    }

    private void validateTeam(final Piece piece, final Team team) {
        if (!piece.isSameTeam(team)) {
            throw new MoveFailureException("상대의 말은 움직일 수 없습니다.");
        }
    }

    private void validateIsNotSameTeam(final Location target, final Piece piece) {
        if (isExistent(target) && piece.isSameTeam(find(target))) {
            throw new MoveFailureException("목표 위치에 같은 팀의 말이 있습니다.");
        }
    }

    private void validateMoveCapable(final Location target, final Piece piece) {
        if (!piece.isMovable(target)) {
            throw new MoveFailureException("해당 체스 말은 해당 위치로 이동할 능력이 없습니다.");
        }
    }

    private void validateNotExistentInPath(final List<Location> pathToTarget) {
        boolean isExistent = pathToTarget.stream()
            .anyMatch(this::isExistent);

        if (isExistent) {
            throw new MoveFailureException("이동 경로에 말이 있습니다.");
        }
    }

    private void validateSameLocation(final Location source, final Location target) {
        if (source.equals(target)) {
            throw new MoveFailureException("현재 말의 위치와 목표 위치는 같을 수 없습니다.");
        }
    }

    private void validatePawnMovable(final Piece sourcePiece, final Location source,
        final Location target) {
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

    public Piece find(final Location location) {
        return pieces
            .stream()
            .filter(piece -> piece.isHere(location))
            .findFirst()
            .orElseThrow(() -> new MoveFailureException("해당 위치에 체스 말이 존재하지 않습니다."));
    }

    public boolean isExistent(final Location location) {
        return pieces
            .stream()
            .anyMatch(piece -> piece.isHere(location));
    }

    private void removeIfExistent(final Location target) {
        if (isExistent(target)) {
            Piece targetPiece = find(target);
            pieces.remove(targetPiece);
        }
    }

    public boolean isKingAlive(final Team team) {
        return pieces
            .stream()
            .filter(piece -> piece.isSameTeam(team))
            .anyMatch(Piece::isKing);
    }

    public List<Piece> toList() {
        return pieces;
    }
}
