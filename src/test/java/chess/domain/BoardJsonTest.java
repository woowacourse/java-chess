package chess.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardJsonTest {

    @Test
    void 보드_제이슨_형식_변환_테스트() {
        Board board = BoardFactory.create();
        BoardJson boardJson = new BoardJson(board);
        System.out.println(boardJson.getboardJson());

    }
}