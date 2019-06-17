package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private List<Position> board;

    public ChessBoard() {
        board = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            board.add(new Position(ChessTeam.BLANK, new BlankPiece()));
        }
        setMal();
    }

    public void setMal() {
        setBlack();
        setWhite();
    }

    private void setBlack() {
        String Mal = "RNBQKBNR";
        for (int i = 0; i < 8; i++) {
            board.set(i, new Position(ChessTeam.BLACK, new QueenPiece(String.valueOf(Mal.charAt(i)))));
        }
        for (int i = 8; i < 16; i++) {
            board.set(i, new Position(ChessTeam.BLACK, new QueenPiece("P")));
        }
    }

    private void setWhite() {
        String Mal = "RNBQKBNR";
        for (int i = 56; i < 64; i++) {
            board.set(i, new Position(ChessTeam.WHITE, new QueenPiece(String.valueOf(Mal.charAt(i - 56)).toLowerCase())));
        }
        for (int i = 48; i < 56; i++) {
            board.set(i, new Position(ChessTeam.WHITE, new QueenPiece("p")));
        }
    }

    public Position access(String destination) {
        String rowIndex = "abcdefgh";
        int index = 8 * (8 - Integer.parseInt(String.valueOf(destination.charAt(1))));
        index += rowIndex.indexOf(destination.charAt(0));
        return board.get(index);
    }

    public String status() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                stringBuilder.append(board.get(8*i+j));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void move(String from, String to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException();
        }
        if (from.charAt(0) < 97 || from.charAt(0) > 104
                || from.charAt(1) < 49 || from.charAt(1) > 56) {
            throw new IllegalArgumentException();
        }

        if (to.charAt(0) < 97 || to.charAt(0) > 104
                || to.charAt(1) < 49 || to.charAt(1) > 56) {
            throw new IllegalArgumentException();
        }
    }
}
