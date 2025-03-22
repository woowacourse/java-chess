package chess;

import chess.piece.Piece;
import java.util.Scanner;

public class ChessGame {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Color START_COLOR = Color.WHITE;

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        chessGame.play();
    }

    public void play() {
        System.out.println("게임을 시작합니다.");
        ChessBoard chessBoard = ChessBoard.initialize();
        printChessBoard(chessBoard);

        Color currentTurnColor = START_COLOR;
        while (true) {
            System.out.println(currentTurnColor + "의 차례입니다. 기물을 이동하세요 ex) a1 a2");

            String[] commands = readLine().split(" ");
            Position from = toPosition(commands[0]);
            Position to = toPosition(commands[1]);
            chessBoard.move(currentTurnColor, from, to);
            printChessBoard(chessBoard);
            currentTurnColor = currentTurnColor.opposite();
        }
    }

    private void printChessBoard(ChessBoard chessBoard) {
        StringBuilder sb = new StringBuilder();
        for (int rowSymbol = 8; rowSymbol > 0; rowSymbol--) {
            Row row = Row.fromSymbol(rowSymbol);
            sb.append(row.getSymbol()).append("  ");
            for (char col = 'A'; col <= 'H'; col++) {
                Column column = Column.valueOf(col + "");
                Position position = new Position(row, column);
                Piece piece = chessBoard.findPiece(position);
                if (piece == null) {
                    sb.append(". ");
                    continue;
                }
                sb.append(piece).append(" ");
            }
            sb.append("\n");
        }
        sb.append("   ").append("a b c d e f g h");

        System.out.println(sb);
    }

    private String readLine() {
        return scanner.nextLine().trim();
    }

    private Position toPosition(String command) {
        Column column = Column.valueOf(Character.toString(command.charAt(0)).toUpperCase());
        Row row = Row.fromSymbol(command.charAt(1) - '0');
        return new Position(column, row);
    }
}
