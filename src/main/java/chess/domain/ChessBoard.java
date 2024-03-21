package chess.domain;

import chess.domain.position.Position;
import java.util.List;

public class ChessBoard {

    private final List<Space> spaces;

    public ChessBoard(SpaceGenerator spaceGenerator) {
        this.spaces = spaceGenerator.generateSpaces();
    }

    public void move(String from, String to) {
        Position fromPosition = Position.of(from);
        Position toPosition = Position.of(to);
        Space fromSpace = findSpace(fromPosition);
        Space toSpace = findSpace(toPosition);

        fromSpace.movePiece(toSpace, spaces);
    }

    private Space findSpace(Position position) {
        for (Space space : spaces) {
            if (space.isSamePosition(position)) {
                return space;
            }
        }
        throw new IllegalArgumentException("해당하는 Space가 없습니다");
    }

    public List<String> showBoard() {
        return spaces.stream()
                .map(Space::pieceCharacter)
                .toList();
    }
}
