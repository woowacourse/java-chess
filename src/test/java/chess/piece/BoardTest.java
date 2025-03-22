package chess.piece;

import static org.junit.jupiter.api.Assertions.*;

import chess.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("보드 게임 셋업 테스트")
    void test1() {
        //given
        //when
        final Board board = Board.emptyBoard();
        board.gameSetUp();
        final Map<Position, Piece> mapView = board.getMapView();
        //then
        for (Map.Entry<Position, Piece> entry :mapView.entrySet()) {
            System.out.println(entry);
        }

    }

}
