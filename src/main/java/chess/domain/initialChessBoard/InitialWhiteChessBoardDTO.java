package chess.domain.initialChessBoard;

public class InitialWhiteChessBoardDTO implements InitialChessBoardDTO {
    private String initialWhiteBoard;

    public InitialWhiteChessBoardDTO() {
        this.initialWhiteBoard = "('a2','P')," +
                "('b2','P')," +
                "('c2','P')," +
                "('d2','P')," +
                "('e2','P')," +
                "('f2','P')," +
                "('g2','P')," +
                "('h2','P')," +
                "('a1','R')," +
                "('b1','N')," +
                "('c1','B')," +
                "('d1','Q')," +
                "('e1','K')," +
                "('f1','B')," +
                "('g1','N')," +
                "('h1','R')";
    }

    @Override
    public String getInitialChessBoard() {
        return initialWhiteBoard;
    }
}
