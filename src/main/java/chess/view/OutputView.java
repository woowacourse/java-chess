package chess.view;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import java.util.Map;

public class OutputView {

    public static void printChessBoard(Map<Position, Character> piecesCharacter) {
        for (int i = 8; i >= 1; i--) {
            for (int j = 1; j <= 8; j++) {
                if (piecesCharacter.containsKey(Position.of(i, j))) {
                    Character character = piecesCharacter.get(Position.of(i, j));
                    System.out.print(CharacterViewer.convertToString(character));
                    continue;
                }
                System.out.print(".");
            }
            System.out.println();
        }
    }
}
