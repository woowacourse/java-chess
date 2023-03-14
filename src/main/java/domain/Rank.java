package domain;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final List<Square> squares;

    public Rank(int rowNumber, int totalColCount) {
        this.squares = new ArrayList<>();
        for (int col = 0; col < totalColCount; col++) {
            this.squares.add(new Square(rowNumber, col));
        }
    }

    public List<Square> getSquare() {
        return squares;
    }
}
