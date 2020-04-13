package chess.domain.initialChessBoard;

public class InitialChessBoardStateDTO {
    private String chessBoardState;

    public InitialChessBoardStateDTO() {
        this.chessBoardState = "('WHITE' , 0)";
    }

    public String getChessBoardState() {
        return chessBoardState;
    }
}
