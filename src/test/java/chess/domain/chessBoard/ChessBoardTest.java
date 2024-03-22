package chess.domain.chessBoard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ChessBoardTest {


    @Test
    @DisplayName("123")
    void a() {
        List<Space> spaceList = new ChessSpaceGenerator().generateSpaces();

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                System.out.print(spaceList.get(j * 8 + i).pieceCharacter() + " ");
            }
            System.out.println();
        }
    }
}
