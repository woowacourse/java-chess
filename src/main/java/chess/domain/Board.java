package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void validateSameTeamByPosition(Position position, Team team) {
        validatePieceExistsOnPosition(position);
        if (!pieces.get(position).isSameTeamWith(team)) {
            throw new ImpossibleMoveException("%s 팀이 움직일 차례입니다".formatted(team.name()));
        }
    }

    public void move(Movement movement) {
        validatePieceExistsOnPosition(movement.source());

        Piece thisPiece = pieces.get(movement.source());
        validateSameTeamPieceExistsOnTargetPosition(movement.target(), thisPiece);
        validateBlockingPieceExists(thisPiece, movement);

        pieces.put(movement.target(), thisPiece.move());
        pieces.remove(movement.source());
    }

    private void validatePieceExistsOnPosition(Position position) {
        if (!pieces.containsKey(position)) {
            throw new ImpossibleMoveException("해당 위치에 기물이 존재하지 않습니다.");
        }
    }

    private void validateSameTeamPieceExistsOnTargetPosition(Position targetPosition, Piece thisPiece) {
        if (pieces.containsKey(targetPosition) && thisPiece.isSameTeamWith(pieces.get(targetPosition))) {
            throw new ImpossibleMoveException("해당 위치에 아군 기물이 존재합니다.");
        }
    }

    private void validateBlockingPieceExists(Piece thisPiece, Movement movement) {
        List<Position> betweenPositions = findBetweenPositions(thisPiece, movement);

        if (betweenPositions.stream()
                .anyMatch(pieces::containsKey)) {
            throw new ImpossibleMoveException("이동을 가로막는 기물이 존재합니다.");
        }
    }

    private List<Position> findBetweenPositions(Piece thisPiece, Movement movement) {
        if (pieces.containsKey(movement.target())) {
            return thisPiece.findBetweenPositionsWhenAttack(movement);
        }
        return thisPiece.findBetweenPositions(movement);
    }

    public boolean isChecked(Team team) {
        Position kingPosition = getKingPosition(team);
        return isBeingAttacked(team, kingPosition);
    }

    public boolean isCheckmate(Team attackedTeam) {
        Position kingPosition = getKingPosition(attackedTeam);

        boolean isImmovable = isImmovable(attackedTeam, kingPosition);
        if (findAttackingPositions(attackedTeam, kingPosition).count() > 1 && isImmovable) {
            return true;
        }

        Position attackingPiecePosition = findAttackingPiecePosition(attackedTeam, kingPosition);
        return isImmovable
                && isNotBlockable(attackedTeam, new Movement(attackingPiecePosition, kingPosition))
                && cannotAttackCheckingPiece(attackedTeam.opponent(), attackingPiecePosition);
    }

    private Position getKingPosition(Team team) {
        Character character = Character.findCharacter(team, Kind.KING);
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().findCharacter() == character)
                .findAny()
                .orElseThrow(() -> new IllegalStateException(
                        "%s 왕이 체스판 위에 존재하기 않습니다.".formatted(team.name())))
                .getKey();
    }

    private boolean isImmovable(Team attackedTeam, Position kingPosition) {
        return kingPosition.findAllMovablePosition(pieces.get(kingPosition))
                .stream()
                .filter(position -> !(pieces.containsKey(position)
                        && pieces.get(position).isSameTeamWith(attackedTeam)))
                .allMatch(position -> isBeingAttacked(attackedTeam, position));
    }

    private boolean isBeingAttacked(Team team, Position position) {
        return findAttackingPositions(team, position).findAny().isPresent();
    }

    private Position findAttackingPiecePosition(Team attackedTeam, Position kingPosition) {
        return findAttackingPositions(attackedTeam, kingPosition)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("체크 상태가 아닙니다."))
                .getKey();
    }

    private Stream<Entry<Position, Piece>> findAttackingPositions(Team team, Position position) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().isSameTeamWith(team))
                .filter(entry -> entry.getKey() != position)
                .filter(entry -> isAttacking(entry.getValue(), new Movement(entry.getKey(), position)));
    }

    private boolean isAttacking(Piece thisPiece, Movement movement) {
        if (thisPiece.isAttacking(movement)) {
            return findBetweenPositions(thisPiece, movement).stream()
                    .noneMatch(pieces::containsKey);
        }
        return false;
    }

    private boolean isNotBlockable(Team attackedTeam, Movement movement) {
        List<Position> attackRoutePositions
                = pieces.get(movement.source()).findBetweenPositionsWhenAttack(movement);

        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeamWith(attackedTeam)
                        && !entry.getKey().equals(movement.target()))
                .noneMatch(entry -> entry.getKey().findAllMovablePosition(entry.getValue())
                        .stream()
                        .anyMatch(attackRoutePositions::contains));
    }

    private boolean cannotAttackCheckingPiece(Team attackingTeam, Position attackingPosition) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().isSameTeamWith(attackingTeam))
                .filter(entry -> entry.getValue().findCharacter()
                        != Character.findCharacter(attackingTeam.opponent(), Kind.KING))
                .filter(entry -> entry.getKey() != attackingPosition)
                .noneMatch(entry -> isAttacking(entry.getValue(), new Movement(entry.getKey(), attackingPosition)));
    }

    public Map<Position, Character> mapPositionToCharacter() {
        return pieces.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getValue().findCharacter()
                ));
    }
}
