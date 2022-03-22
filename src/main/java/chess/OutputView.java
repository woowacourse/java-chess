package chess;

public class OutputView {
    public static void startGameBoard(BoardDto boardDto) {
        String joinedPieces = String.join("", boardDto.getPieces());
        for (int i = 8; i <= joinedPieces.length(); i += 8) {
            System.out.println(joinedPieces.substring(i-8, i));
        }
    }
}
