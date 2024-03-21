package chess.domain;

import java.util.List;

public class ChessBoard {

    private final List<Space> spaces;

    public ChessBoard(SpaceGenerator spaceGenerator) {
        this.spaces = spaceGenerator.generateSpaces();
    }

    public List<String> showBoard() {
        return spaces.stream()
                .map(Space::pieceCharacter)
                .toList();
    }
}
