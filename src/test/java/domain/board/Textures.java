package domain.board;

import domain.Board;
import domain.piece.Piece;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Textures {
    public static Board makeBoard(List<List<Piece>> pieces) {
        try {
            Constructor<Board> declaredConstructor = Board.class.getDeclaredConstructor(List.class);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(pieces);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
