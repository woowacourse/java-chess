package chess.domain.piece;

import java.lang.reflect.Constructor;

public class PieceFactory {
    private PieceFactory() {
    }

    public static Piece from(char pieceName, Color color, char x, char y) {
        try {
            String className = PieceName.getClassNameByPieceName(pieceName);
            Class<Piece> pieceClass = (Class<Piece>) Class.forName(className);
            Constructor<?> constructor = pieceClass.getConstructor(Color.class, char.class, char.class);
            Piece piece = (Piece) constructor.newInstance(color, x, y);
            return piece;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
