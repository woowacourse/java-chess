package chess.view;

public class OutputView {

    public static void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printChessMap(final char[][] chessMap) {
        for (char[] file : chessMap) {
            printChessPiece(file);
            System.out.println();
        }
    }

    private static void printChessPiece(final char[] file) {
        for (char pieceName : file) {
            System.out.print(pieceName);
        }
    }

    public static void printResult(final String currentPlayerName, final double currentPlayerScore,
            final String opponentPlayerName, final double opponentPlayerScore) {
        System.out.print(currentPlayerName.concat(": "));
        System.out.println(currentPlayerScore);

        System.out.print(opponentPlayerName.concat(": "));
        System.out.println(opponentPlayerScore);
    }
}
