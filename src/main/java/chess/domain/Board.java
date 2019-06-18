package chess.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static Map<Position, Piece> board;

    static {
        board = new HashMap<>();
        List<Character> aToH = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
        List<Piece> piecesTeamBlack = Arrays.asList(
                new Rook(Team.BLACK), new Knight(Team.BLACK), new Bishop(Team.BLACK),
                new Queen(Team.BLACK), new King(Team.BLACK), new Bishop(Team.BLACK),
                new Knight(Team.BLACK), new Rook(Team.BLACK)
        );
        List<Piece> piecesTeamWhite = Arrays.asList(
                new Rook(Team.WHITE), new Knight(Team.WHITE), new Bishop(Team.WHITE),
                new Queen(Team.WHITE), new King(Team.WHITE), new Bishop(Team.WHITE),
                new Knight(Team.WHITE), new Rook(Team.WHITE)
        );

        for (int i = 0; i < aToH.size(); i++) {
            board.put(new Position(new Coordinate(aToH.get(i)), new Coordinate(1)), piecesTeamBlack.get(i));
            board.put(new Position(new Coordinate(aToH.get(i)), new Coordinate(8)), piecesTeamWhite.get(i));
        }

        aToH.forEach(character -> board.put(new Position(new Coordinate(character), new Coordinate(2)), new Pawn(Team.BLACK)));
        aToH.forEach(character -> board.put(new Position(new Coordinate(character), new Coordinate(7)), new Pawn(Team.WHITE)));
    }

    public static Piece at(final Position position) {
        return board.get(position);
    }

    public static String boardAt(final Position position) {
        if (!board.containsKey(position)) {
            return ".";
        }

        Piece piece = board.get(position);

        if (!piece.isLife()) {
            return ".";
        }

        if (piece.isBlackTeam()) {
            return piece.getName();
        }
        return piece.getName().toUpperCase();
    }

    public static void move(Position source, Position target) {
        if (!board.containsKey(source)) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }

        Piece sourcePiece = board.get(source);

        if (board.containsKey(target) && sourcePiece.isSameTeam(board.get(target))) {
            throw new IllegalArgumentException("같은 팀이 있는 위치로 이동이 불가능합니다.");
        }

        board.put(target, sourcePiece);
    }
}
