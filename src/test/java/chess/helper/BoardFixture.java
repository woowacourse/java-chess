package chess.helper;

import chess.domain.board.position.Position;
import chess.domain.piece.slider.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.jumper.King;
import chess.domain.piece.jumper.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.slider.Queen;
import chess.domain.piece.slider.Rook;

import java.util.HashMap;
import java.util.Map;

public class BoardFixture {

    /**
     * .KR.....  8
     * P.PB....  7
     * .P..Q...  6
     * ........  5
     * .....nq.  4
     * .....p.p  3
     * .....pp.  2
     * ....rk..  1
     *
     * 12345678
     */

    public static Map<Position, Piece> createBoard() {
        Map<Position, Piece> map = new HashMap<>();

        final Map<Position, Piece> map1 = Map.of(
                new Position(5, 1), new Rook(Color.WHITE),
                new Position(6, 1), new King(Color.WHITE),
                new Position(6, 2), new Pawn(Color.WHITE),
                new Position(7, 2), new Pawn(Color.WHITE),
                new Position(6, 3), new Pawn(Color.WHITE),
                new Position(8, 3), new Pawn(Color.WHITE),
                new Position(6, 4), new Knight(Color.WHITE),
                new Position(7, 4), new Queen(Color.WHITE),
                new Position(2, 6), new Pawn(Color.BLACK),
                new Position(5, 6), new Queen(Color.BLACK)
        );

        final Map<Position, Piece> map2 = Map.of(new Position(1, 7), new Pawn(Color.BLACK),
                                                 new Position(3, 7), new Pawn(Color.BLACK),
                                                 new Position(4, 7), new Bishop(Color.BLACK),
                                                 new Position(2, 8), new King(Color.BLACK),
                                                 new Position(3, 8), new Rook(Color.BLACK));


        map.putAll(map1);
        map.putAll(map2);

        return map;
    }

    /**
     * .KR.....  8
     * P.PB....  7
     * .P..Q...  6
     * ........  5
     * .....nq.  4
     * .....p.p  3
     * .....pp.  2
     * ....r...  1
     *
     * 12345678
     */
    public static Map<Position, Piece> createKingDeadBoard() {
        Map<Position, Piece> map = new HashMap<>();

        final Map<Position, Piece> map1 = Map.of(
                new Position(5, 1), new Rook(Color.WHITE),
                new Position(6, 2), new Pawn(Color.WHITE),
                new Position(7, 2), new Pawn(Color.WHITE),
                new Position(6, 3), new Pawn(Color.WHITE),
                new Position(8, 3), new Pawn(Color.WHITE),
                new Position(6, 4), new Knight(Color.WHITE),
                new Position(7, 4), new Queen(Color.WHITE),
                new Position(2, 6), new Pawn(Color.BLACK),
                new Position(5, 6), new Queen(Color.BLACK)
        );

        final Map<Position, Piece> map2 = Map.of(new Position(1, 7), new Pawn(Color.BLACK),
                                                 new Position(3, 7), new Pawn(Color.BLACK),
                                                 new Position(4, 7), new Bishop(Color.BLACK),
                                                 new Position(2, 8), new King(Color.BLACK),
                                                 new Position(3, 8), new Rook(Color.BLACK));


        map.putAll(map1);
        map.putAll(map2);

        return map;
    }
}
