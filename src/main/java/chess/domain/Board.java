package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.character.Character;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(Position oldPosition, Position newPosition) {
        validatePieceExistsOnOldPosition(oldPosition);

        Piece thisPiece = pieces.get(oldPosition);
        validateSameTeamPieceExistsOnNewPosition(newPosition, thisPiece);
        validateBlockingPieceExists(thisPiece, oldPosition, newPosition);

        pieces.put(newPosition, thisPiece.move());
        pieces.remove(oldPosition);
    }

    private void validatePieceExistsOnOldPosition(Position oldPosition) {
        if (!pieces.containsKey(oldPosition)) {
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
