package model.piece.state;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import model.Position;
import model.direction.MovingPattern;
import model.piece.Color;

public abstract class Role {
    protected Color color;
    private List<MovingPattern> movingPatterns;
    protected Role(Color color, List<MovingPattern> movingPatternList){
        this.color = color;
        this.movingPatterns = new ArrayList<>(movingPatternList);
    }
    public abstract Set<Position> possiblePositions(Position position);

    public Color getColor() {
        return color;
    }
}
