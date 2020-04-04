package chess;

import chess.domain.chesspiece.*;
import chess.domain.game.Player;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.*;
import java.util.stream.Collectors;

public class PieceFactory {
    private static final Map<Piece, List<Position>> pieces = new LinkedHashMap<>();

    static {
        createPieces(Player.WHITE, Column.ONE, Column.TWO);
        createPieces(Player.BLACK, Column.EIGHT, Column.SEVEN);
    }

    public static void createPieces(Player player, Column column, Column pawnColumn){
        pieces.put(new Rook(player), Arrays.asList(Positions.of(Row.A, column), Positions.of(Row.H, column)));
        pieces.put(new Knight(player), Arrays.asList(Positions.of(Row.B, column), Positions.of(Row.G, column)));
        pieces.put(new Bishop(player), Arrays.asList(Positions.of(Row.C, column), Positions.of(Row.F, column)));
        pieces.put(new Queen(player), Arrays.asList(Positions.of(Row.D, column)));
        pieces.put(new King(player), Arrays.asList(Positions.of(Row.E, column)));

        List<Position> pawnPositions = Arrays.stream(Row.values())
                .map(row -> Positions.of(row, pawnColumn))
                .collect(Collectors.toList());
        for (Position position : pawnPositions) {
            pieces.put(new Pawn(player, position), Arrays.asList(position));
        }
    }

    public static Map<Piece, List<Position>> create() {
        return new HashMap<>(pieces);
    }
}
