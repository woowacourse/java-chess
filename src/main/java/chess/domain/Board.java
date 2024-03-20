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
        if (pieces.containsKey(oldPosition)) {
            Piece thisPiece = pieces.get(oldPosition);

            List<Position> betweenPositions;
            if (pieces.containsKey(newPosition)) {
                betweenPositions = thisPiece.findBetweenPositionsWhenAttack(oldPosition, newPosition);
            } else {
                betweenPositions = thisPiece.findBetweenPositions(oldPosition, newPosition);
            }

            for (Position betweenPosition : betweenPositions) {
                if (pieces.containsKey(betweenPosition)) {
                    throw new IllegalArgumentException("이동을 가로막는 기물이 존재합니다.");
                }
            }

            if (pieces.containsKey(newPosition) && thisPiece.isSameTeamWith(pieces.get(newPosition))) {
                throw new IllegalArgumentException("해당 위치에 아군 기물이 존재합니다.");
            }

            pieces.put(newPosition, thisPiece.move());
            pieces.remove(oldPosition);
            return;
        }
        throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
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
