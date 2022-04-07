package chess.domain.piece;

import chess.domain.position.Position;

public class PieceFactory {

    public static Piece of(String type, String color, String position) {
        if (type.equals("pawn")) {
            return new Pawn(Color.of(color), Position.of(position));
        }
        if (type.equals("rook")) {
            return new Rook(Color.of(color), Position.of(position));
        }
        if (type.equals("bishop")) {
            return new Bishop(Color.of(color), Position.of(position));
        }
        if (type.equals("knight")) {
            return new Knight(Color.of(color), Position.of(position));
        }
        if (type.equals("queen")) {
            return new Queen(Color.of(color), Position.of(position));
        }
        if (type.equals("king")) {
            return new King(Color.of(color), Position.of(position));
        }
        throw new IllegalArgumentException("오류입니다.");
    }

}
