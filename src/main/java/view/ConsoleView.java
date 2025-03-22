package view;

import chess.Turn;
import chess.board.Column;
import chess.board.Position;
import chess.board.Row;
import chess.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import view.support.BoardComparator;
import view.support.OutputSupporter;

public class ConsoleView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final OutputSupporter outputSupporter;

    public ConsoleView(OutputSupporter outputSupporter) {
        this.outputSupporter = outputSupporter;
    }

    public void printBoard(Map<Position, Piece> pieces) {
        Map<Position, Piece> filledPieces = outputSupporter.fillBoard(pieces);
        List<Position> positions = new ArrayList<>(filledPieces.keySet());
        positions.sort(new BoardComparator());

        StringBuilder builder = new StringBuilder();
        Position printPosition = positions.getFirst();
        builder.append(printPosition.row().getValue()).append(" ");

        for (Position position : positions) {
            Piece piece = filledPieces.get(position);
            if (printPosition.row() != position.row()) {
                builder.append("\n").append(position.row().getValue()).append(" ");
                printPosition = position;
            }
            if (piece == null) {
                builder.append("- ").append(" ");
                continue;
            }
            String formattedPiece = outputSupporter.formatPiece(piece);
            builder.append(formattedPiece).append(" ");
        }
        builder.append("\n").append("  A  B  C  D  E  F  G  H").append("\n\n");
        System.out.println(builder);
    }

    public void printTurn(Turn currentTurn) {
        String formattedTurn = outputSupporter.formatTurn(currentTurn);
        System.out.printf("%s 차례입니다\n", formattedTurn);
    }

    public Position  requestStartPoint() {
        System.out.println("시작 좌표를 입력해주세요(ex)x,y");
        String[] inputParts = SCANNER.nextLine().split(",");
        Column column = Column.valueOf(inputParts[0]);
        Row row = Row.findByValue(Integer.parseInt(inputParts[1]));
        return new Position(column, row);
    }

    public Position requestDestination() {
        System.out.println("목표 좌표를 입력해주세요(ex)x,y");
        String[] inputParts = SCANNER.nextLine().split(",");
        Column column = Column.valueOf(inputParts[0]);
        Row row = Row.findByValue(Integer.parseInt(inputParts[1]));
        return new Position(column, row);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
