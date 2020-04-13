package chess.domain.initialChessBoard;

public class InitialBlackChessBoardDTO implements InitialChessBoardDTO {

    private String initialBlackBoard;

    public InitialBlackChessBoardDTO() {
        this.initialBlackBoard = "('a7','p')," +
                "('b7','p')," +
                "('c7','p')," +
                "('d7','p')," +
                "('e7','p')," +
                "('f7','p')," +
                "('g7','p')," +
                "('h7','p')," +
                "('a8','r')," +
                "('b8','n')," +
                "('c8','b')," +
                "('d8','q')," +
                "('e8','k')," +
                "('f8','b')," +
                "('g8','n')," +
                "('h8','r')";
    }

    @Override
    public String getInitialChessBoard() {
        return this.initialBlackBoard;
    }
}
