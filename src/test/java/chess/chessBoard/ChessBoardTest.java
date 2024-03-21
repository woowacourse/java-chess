package chess.chessBoard;

import chess.domain.chessBoard.ChessSpaceGenerator;
import chess.domain.chessBoard.Space;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {


    @Test
    @DisplayName("123")
    void a() {
        List<Space> spaceList = new ChessSpaceGenerator().generateSpaces();

        for(int j=0;j<8;j++) {
            for (int i = 0; i < 8; i++) {
                System.out.print(spaceList.get(j * 8 + i).pieceCharacter()+" ");
            }
            System.out.println();
        }
    }
}
