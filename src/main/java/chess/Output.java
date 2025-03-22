package chess;

public class Output {

    public static void printBoard() {
        System.out.println("기물");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Main.pieceBoard[i][j] == null) {
                    System.out.print("@ ");
                }
                else {
                    System.out.print(Main.pieceBoard[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
