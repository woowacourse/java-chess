package chess.domain.chessBoard;

import chess.domain.position.Position;
import java.util.List;

public class ChessBoard {

    private final List<Space> spaces;

    public ChessBoard(SpaceGenerator spaceGenerator) {
        this.spaces = spaceGenerator.generateSpaces();
    }

    public void move(Position from, Position to) {
        Space fromSpace = findSpace(from);
        Space toSpace = findSpace(to);

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
