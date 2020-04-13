package chess.domain.initialChessBoard;

public class InitialChessBoardStateDTO {

    public static String getInitialTurn() {
        return "WHITE";
    }

    public static boolean isCaughtKing() {
        return false;
    }
}
