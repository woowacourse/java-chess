package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import chess.dto.MovementDto;
import chess.exception.ImpossibleMoveException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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

    public void move(MovementDto movementDto) {
        validatePieceExistsOnPosition(movementDto.source());

        Piece thisPiece = pieces.get(movementDto.source());
        validateSameTeamPieceExistsOnTargetPosition(movementDto.target(), thisPiece);
        validateBlockingPieceExists(thisPiece, movementDto);

        pieces.put(movementDto.target(), thisPiece.move());
        pieces.remove(movementDto.source());
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

    private void validateBlockingPieceExists(Piece thisPiece, MovementDto movementDto) {
        List<Position> betweenPositions = findBetweenPositions(thisPiece, movementDto);

        if (betweenPositions.stream()
                .anyMatch(pieces::containsKey)) {
            throw new ImpossibleMoveException("이동을 가로막는 기물이 존재합니다.");
        }
    }

    public boolean isChecked(Team team) {
        Position kingPosition = getKingPosition(team);
        return isBeingAttacked(team, kingPosition);
    }

    public boolean isCheckmate(Team attackedTeam) {
        Position kingPosition = getKingPosition(attackedTeam);
        Piece king = pieces.get(kingPosition);

        // 자신이 공격받고 있는데 움직일데도 없음
        boolean isCheckAndImmovable = isCheckAndImmovable(attackedTeam, kingPosition, king);

        // 더블 체크이고, 움직일 곳이 없으면, true
        List<Position> attackingPiecePositions = findAttackingPiecePositions(attackedTeam, kingPosition);
        if (attackingPiecePositions.size() > 1 && isCheckAndImmovable) {
            return true;
        }
        Position attackingPiecePosition = attackingPiecePositions.get(0);

        // 나를 공격하는 유닛의 경로를 막을 수 없는 경우
        boolean isNotBlockable = isNotBlockable(attackedTeam, new MovementDto(attackingPiecePosition, kingPosition));

        // 나를 공격하는 유닛을 공격할 수 없는 경우
        boolean cannotAttackAttackingPiece
                = cannotAttackAttackingPiece(attackedTeam.opponent(), attackingPiecePosition);

        // 자신이 공격받고 있는데 움직일 데도 없음, 나를 공격하는 유닛의 경로를 막을 수 없음
        return isCheckAndImmovable && isNotBlockable && cannotAttackAttackingPiece;
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

    private boolean isCheckAndImmovable(Team attackedTeam, Position kingPosition, Piece king) {
        return kingPosition.findAllMovablePosition(king)
                .stream()
                .filter(position -> !pieces.containsKey(position)
                        || (pieces.containsKey(position) && !pieces.get(position).isSameTeamWith(attackedTeam)))
                .allMatch(position -> isBeingAttacked(attackedTeam, position));
    }

    private boolean isBeingAttacked(Team team, Position position) {
        return !findAttackingPiecePositions(team, position).isEmpty();
    }

    private List<Position> findAttackingPiecePositions(Team team, Position position) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != position)
                .filter(entry -> !entry.getValue().isSameTeamWith(team))
                .filter(entry -> isAttacking(entry.getValue(), new MovementDto(entry.getKey(), position)))
                .map(Entry::getKey)
                .toList();
    }

    private boolean isAttacking(Piece thisPiece, MovementDto movementDto) {
        if (thisPiece.isAttacking(movementDto)) {
            List<Position> betweenPositions = findBetweenPositions(thisPiece, movementDto);
            return betweenPositions.stream()
                    .noneMatch(pieces::containsKey);
        }
        return false;
    }

    private List<Position> findBetweenPositions(Piece thisPiece, MovementDto movementDto) {
        if (pieces.containsKey(movementDto.target())) {
            return thisPiece.findBetweenPositionsWhenAttack(movementDto);
        }
        return thisPiece.findBetweenPositions(movementDto);
    }

    private boolean isNotBlockable(Team attackedTeam, MovementDto movementDto) {
        List<Position> attackRoutePositions
                = pieces.get(movementDto.source()).findBetweenPositionsWhenAttack(movementDto);

        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeamWith(attackedTeam)
                        && !entry.getKey().equals(movementDto.target()))
                .noneMatch(entry -> entry.getKey().findAllMovablePosition(entry.getValue())
                        .stream()
                        .anyMatch(attackRoutePositions::contains));
    }

    private boolean cannotAttackAttackingPiece(Team attackingTeam, Position attackingPosition) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != getKingPosition(attackingTeam.opponent()))
                .filter(entry -> entry.getKey() != attackingPosition)
                .filter(entry -> !entry.getValue().isSameTeamWith(attackingTeam))
                .noneMatch(entry -> isAttacking(entry.getValue(), new MovementDto(entry.getKey(), attackingPosition)));
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
