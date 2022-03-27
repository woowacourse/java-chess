package chess.domain;

public class BoardFixture {

    static Board setup() {
        Board board = new BoardInitializer().init();
        return board;
    }
}
