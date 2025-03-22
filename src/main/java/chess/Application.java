package chess;

import static chess.Color.*;
import static chess.Column.*;
import static chess.Row.*;

import chess.board.Board;
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

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Board board = new Board(makeInitialBoard());
        System.out.println("체스 게임을 시작합니다.");
        Color currentColor = WHITE;
        while(true) {
            System.out.printf("%s 차례입니다.\n움직이려는 말의 좌표와 도착 좌표를 입력하세요.\n", currentColor);
            String input = scanner.nextLine();
            String[] split = input.split(" ");
            String[] startString = split[0].split("");
            String[] goalString = split[1].split("");
            Position start = createPosition(startString);
            Position goal = createPosition(goalString);
            board.move(start, goal);
            currentColor = currentColor.opposite();
        }
    }

    public static Position createPosition(String[] positionString) {
        Column column = getColumn(positionString[0]);
        Row row = getRow(positionString[1]);
        return new Position(row, column);
    }

    private static Map<Position, Piece> makeInitialBoard() {
        Map<Position, Piece> board = new HashMap<>();
        initPawns(board);
        board.put(new Position(ONE, A), new Rook(WHITE));
        board.put(new Position(ONE, H), new Rook(WHITE));
        board.put(new Position(EIGHT, A), new Rook(BLACK));
        board.put(new Position(EIGHT, H), new Rook(BLACK));

        board.put(new Position(ONE, B), new Knight(WHITE));
        board.put(new Position(ONE, G), new Knight(WHITE));
        board.put(new Position(EIGHT, B), new Knight(BLACK));
        board.put(new Position(EIGHT, G), new Knight(BLACK));

        board.put(new Position(ONE, C), new Bishop(WHITE));
        board.put(new Position(ONE, F), new Bishop(WHITE));
        board.put(new Position(EIGHT, C), new Bishop(BLACK));
        board.put(new Position(EIGHT, F), new Bishop(BLACK));

        board.put(new Position(ONE, D), new Queen(WHITE));
        board.put(new Position(EIGHT, D), new Queen(BLACK));

        board.put(new Position(ONE, E), new King(WHITE));
        board.put(new Position(EIGHT, E), new King(BLACK));

        return board;
    }

    private static void initPawns(Map<Position, Piece> board) {
        for (Column column : Column.values()) {
            board.put(new Position(TWO, column), new Pawn(WHITE));
            board.put(new Position(SEVEN, column), new Pawn(BLACK));
        }
    }
}
