package view;

import domain.ChessColumn;
import domain.Rank;
import domain.Square;

public class Move implements Command{

    private final String src;
    private final String dest;

    public Move(String input) {
        String[] inputs = input.split(" ");
        validate(inputs);
        this.src = inputs[1];
        this.dest = inputs[2];
    }

    private void validate(String[] inputs) {
        if (inputs.length != 3) {
            throw new IllegalArgumentException("move source 위치 target 위치를 입력해야합니다.");
        }
    }

    public Square getSource() {
        return toSquare(src);
    }

    private Square toSquare(String squareInput) {
        char column = squareInput.charAt(0);
        char row = squareInput.charAt(1);
        ChessColumn chessColumn = ChessColumn.find(column);
        Rank rank = Rank.find(Character.getNumericValue(row));
        return Square.of(chessColumn, rank);
    }

    public Square getTarget() {
        return toSquare(dest);
    }
}

