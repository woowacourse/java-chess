package chess.domain;

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

    public void move(Position oldPosition, Position newPosition) {
        validatePieceExistsOnPosition(oldPosition);

        Piece thisPiece = pieces.get(oldPosition);
        validateSameTeamPieceExistsOnNewPosition(newPosition, thisPiece);
        validateBlockingPieceExists(thisPiece, oldPosition, newPosition);

        pieces.put(newPosition, thisPiece.move());
        pieces.remove(oldPosition);
    }


    private void validatePieceExistsOnPosition(Position position) {
        if (!pieces.containsKey(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }
    }

    private void validateSameTeamPieceExistsOnNewPosition(Position newPosition, Piece thisPiece) {
        if (pieces.containsKey(newPosition) && thisPiece.isSameTeamWith(pieces.get(newPosition))) {
            throw new IllegalArgumentException("해당 위치에 아군 기물이 존재합니다.");
        }
    }

    private void validateBlockingPieceExists(Piece thisPiece, Position oldPosition, Position newPosition) {
        List<Position> betweenPositions = findBetweenPositions(thisPiece, oldPosition, newPosition);

        if (betweenPositions.stream()
                .anyMatch(pieces::containsKey)) {
            throw new IllegalArgumentException("이동을 가로막는 기물이 존재합니다.");
        }
    }

    public boolean isCheckmate(Team attackedTeam) {
        Position kingPosition = getKingPosition(attackedTeam);
        Piece king = pieces.get(kingPosition);

        // 자신 및 주변 유닛이 공격받고 있음
        return kingPosition.findNearbyPosition(king).stream()
                .filter(position -> !pieces.containsKey(position) ||
                        (pieces.containsKey(position) && pieces.get(position).isOppositeTeamWith(attackedTeam)))
                .allMatch(position -> isBeingAttacked(attackedTeam, position));
    }

    public boolean isChecked(Team team) {
        Position kingPosition = getKingPosition(team);
        return isBeingAttacked(team, kingPosition);
    }

    private boolean isBeingAttacked(Team team, Position position) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isOppositeTeamWith(team))
                .anyMatch(entry -> isAttacking(entry.getValue(), entry.getKey(), position));
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

    private boolean isAttacking(Piece thisPiece, Position thisPosition, Position attackPosition) {
        if (thisPiece.isAttacking(thisPosition, attackPosition)) {
            List<Position> betweenPositions = findBetweenPositions(thisPiece, thisPosition, attackPosition);
            return betweenPositions.stream()
                    .noneMatch(pieces::containsKey);
        }
        return false;
    }

    private List<Position> findBetweenPositions(Piece thisPiece, Position oldPosition, Position newPosition) {
        if (pieces.containsKey(newPosition)) {
            return thisPiece.findBetweenPositionsWhenAttack(oldPosition, newPosition);
        }
        return thisPiece.findBetweenPositions(oldPosition, newPosition);
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
