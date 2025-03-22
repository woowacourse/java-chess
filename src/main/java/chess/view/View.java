package chess.view;

import static chess.board.Color.BLACK;
import static chess.board.Color.WHITE;

import chess.board.ChessBoard;
import chess.board.Color;
import chess.board.Column;
import chess.board.Position;
import chess.board.Row;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class View {

    private final Scanner scanner = new Scanner(System.in);
    private static final Pattern POSITION_PATTERN = Pattern.compile("[0-7],[0-7]");

    private static final Map<Piece, String> pieceLabels = new HashMap<>() {{
        put(new King(WHITE), "♔");
        put(new Queen(WHITE), "♕");
        put(new Rook(WHITE), "♖");
        put(new Bishop(WHITE), "♗");
        put(new Knight(WHITE), "♘");
        put(new Pawn(WHITE), "♙");

        put(new King(BLACK), "♚");
        put(new Queen(BLACK), "♛");
        put(new Rook(BLACK), "♜");
        put(new Bishop(BLACK), "♝");
        put(new Knight(BLACK), "♞");
        put(new Pawn(BLACK), "♟");
    }};

    private static final Map<Integer, Column> columnNumbers = Map.of(
            0, Column.A,
            1, Column.B,
            2, Column.C,
            3, Column.D,
            4, Column.E,
            5, Column.F,
            6, Column.G,
            7, Column.H
    );

    private static final Map<Integer, Row> rowNumbers = Map.of(
            0, Row.ONE,
            1, Row.TWO,
            2, Row.THREE,
            3, Row.FOUR,
            4, Row.FIVE,
            5, Row.SIX,
            6, Row.SEVEN,
            7, Row.EIGHT
    );

    public void printStartGuide(ChessBoard chessBoard) {
        System.out.println("게임을 시작합니다.");
    }

    public void printChessBoard(ChessBoard chessBoard) {
        Map<Position, Piece> boardMap = chessBoard.getBoardMap();
        for (int row = 7; row >= 0; row--) {
            System.out.print("| ");
            for (int column = 0; column <= 7; column++) {
                Position position = new Position(rowNumbers.get(row), columnNumbers.get(column));
                if (boardMap.containsKey(position)) {
                    Piece piece = boardMap.get(position);
                    System.out.print(pieceLabels.get(piece) + " | ");
                    continue;
                }
                System.out.print("  | ");
            }
            System.out.print(" " + row);
            System.out.println();
            System.out.println("---------------------------------");
        }
        System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
    }

    public Position readPlayerStartPosition() {
        System.out.println("이동하고 싶은 기물의 좌표를 입력해주세요 (row,colum) ex 0,1");
        return getPosition();
    }

    public Position readPlayerEndPosition() {
        System.out.println("어디로 이동시킬 지 좌표를 입력해주세요 (row,colum) ex 0,1");
        return getPosition();
    }

    private Position getPosition() {
        String input = readLine();
        if (!POSITION_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("올바른 형식으로 입력해주세요.");
        }
        String[] split = input.split(",");
        Row row = rowNumbers.get(Integer.parseInt(split[0]));
        Column column = columnNumbers.get(Integer.parseInt(split[1]));
        return new Position(row, column);
    }

    private String readLine() {
        return scanner.nextLine().trim();
    }

    public void printTurnGuide(Color currentTurnColor) {
        System.out.println(currentTurnColor + "의 차례입니다.");
    }

    public void printExceptionGuide(String message) {
        System.out.println("[ERROR] " + message);
    }
}
