package chess.domain;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
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
        if (pieces.get(position).isOppositeTeamWith(team)) {
            throw new IllegalArgumentException("%s 팀이 움직일 차례입니다".formatted(team.name()));
        }
    }

    public void move(Positions positions) {
        validatePieceExistsOnPosition(positions.source());

        Piece thisPiece = pieces.get(positions.source());
        validateSameTeamPieceExistsOnTargetPosition(positions.target(), thisPiece);
        validateBlockingPieceExists(thisPiece, positions);

        pieces.put(positions.target(), thisPiece.move());
        pieces.remove(positions.source());
    }

    private void validatePieceExistsOnPosition(Position position) {
        if (!pieces.containsKey(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }
    }

    private void validateSameTeamPieceExistsOnTargetPosition(Position targetPosition, Piece thisPiece) {
        if (pieces.containsKey(targetPosition) && thisPiece.isSameTeamWith(pieces.get(targetPosition))) {
            throw new IllegalArgumentException("해당 위치에 아군 기물이 존재합니다.");
        }
    }

    private void validateBlockingPieceExists(Piece thisPiece, Positions positions) {
        List<Position> betweenPositions = findBetweenPositions(thisPiece, positions);

        if (betweenPositions.stream()
                .anyMatch(pieces::containsKey)) {
            throw new IllegalArgumentException("이동을 가로막는 기물이 존재합니다.");
        }
    }

    public CheckState findCheckState(Team team) {
        Position kingPosition = getKingPosition(team);
        if (!isChecked(team, kingPosition)) {
            return CheckState.SAFE;
        }
        if(isCheckmate(team, kingPosition)) {
            return CheckState.CHECK_MATE;
        }
        return CheckState.CHECK;
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

    private boolean isChecked(Team team, Position kingPosition) {
        return calculateAttackedPositionCount(team, kingPosition) > 0;
    }

    private boolean isCheckmate(Team team, Position kingPosition) {
        boolean isNotSafePathAvailableForKing = isNotSafePathAvailableForKing(team, kingPosition);
        if (isDoubleCheck(team, kingPosition) && isNotSafePathAvailableForKing) {
            return true;
        }

        Position attackingPiecePosition = findAttackingPiecePosition(team, kingPosition);
        boolean isNotBlockAttackingPiece
                = isNotBlockAttackingPiece(team, new Positions(attackingPiecePosition, kingPosition));
        boolean isNotAttackAttackingPiece = isNotAttackAttackingPiece(team.opponent(), attackingPiecePosition);
        return isNotSafePathAvailableForKing && isNotBlockAttackingPiece && isNotAttackAttackingPiece;
    }

    private boolean isNotSafePathAvailableForKing(Team team, Position kingPosition) {
        return kingPosition.findAllMovablePosition(new King(team))
                .stream()
                .filter(position -> !pieces.containsKey(position)
                        || (pieces.containsKey(position) && pieces.get(position).isOppositeTeamWith(team)))
                .allMatch(position -> calculateAttackedPositionCount(team, position) != 0);
    }

    private boolean isDoubleCheck(Team team, Position kingPosition) {
        return calculateAttackedPositionCount(team, kingPosition) >= 2;
    }

    private int calculateAttackedPositionCount(Team team, Position position) {
        return findAttackingPiecePositions(team, position).size();
    }

    private Position findAttackingPiecePosition(Team team, Position position) {
        return findAttackingPiecePositions(team, position)
                .stream()
                .findAny()
                .orElseThrow(() ->
                        new IllegalStateException("해당 위치를 공격하는 기물은 없습니다."));
    }

    private List<Position> findAttackingPiecePositions(Team team, Position position) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != position && entry.getValue().isOppositeTeamWith(team))
                .filter(entry -> isAttacking(entry.getValue(), new Positions(entry.getKey(), position)))
                .map(Entry::getKey)
                .toList();
    }

    private boolean isNotBlockAttackingPiece(Team team, Positions positions) {
        List<Position> attackRoutePositions = pieces.get(positions.source())
                .findBetweenPositionsWhenAttack(positions);
        List<Position> teamPieceMovablePositions = findTeamPieceMovablePositions(team, positions);

        return teamPieceMovablePositions.stream()
                .noneMatch(attackRoutePositions::contains);
    }

    private List<Position> findTeamPieceMovablePositions(Team team, Positions positions) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeamWith(team) && !entry.getKey().equals(positions.target()))
                .flatMap(entry -> entry.getKey()
                        .findAllMovablePosition(entry.getValue())
                        .stream()
                        .filter(position -> isAttacking(entry.getValue(), new Positions(entry.getKey(), position))))
                .toList();
    }

    private boolean isNotAttackAttackingPiece(Team attackingTeam, Position attackingPosition) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != getKingPosition(attackingTeam.opponent())
                        && entry.getKey() != attackingPosition)
                .filter(entry -> entry.getValue().isOppositeTeamWith(attackingTeam))
                .noneMatch(entry -> isAttacking(entry.getValue(), new Positions(entry.getKey(), attackingPosition)));
    }

    private boolean isAttacking(Piece thisPiece, Positions positions) {
        if (thisPiece.isAttacking(positions)) {
            List<Position> betweenPositions = findBetweenPositions(thisPiece, positions);
            return betweenPositions.stream()
                    .noneMatch(pieces::containsKey);
        }
        return false;
    }

    private List<Position> findBetweenPositions(Piece thisPiece, Positions positions) {
        if (pieces.containsKey(positions.target())) {
            return thisPiece.findBetweenPositionsWhenAttack(positions);
        }
        return thisPiece.findBetweenPositions(positions);
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
