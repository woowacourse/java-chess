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

    public static void printWinner(final String whitePlayerName, final String blackPlayerName,
                                   final boolean isWhitePlayerWin, final boolean isBlackPlayerWin) {
        if (!isWhitePlayerWin && !isBlackPlayerWin) {
            System.out.println("무승부");
        }
        if (isWhitePlayerWin) {
            System.out.println(whitePlayerName.concat(" 승리!"));
        }
        if (isBlackPlayerWin) {
            System.out.println(blackPlayerName.concat(" 승리!"));
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
