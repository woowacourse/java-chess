package chess.domain;

public class BoardFixture {

    public static Board setup() {
        Board board = new BoardInitializer().init();
        return board;
    }
}
