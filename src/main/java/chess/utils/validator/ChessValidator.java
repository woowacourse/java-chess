package chess.utils.validator;

public class ChessValidator {
    public static void checkChessId(int chessId) {
        if (chessId <= 0) {
            throw new IllegalArgumentException("Chess id must be positive number.");
        }
    }
}
