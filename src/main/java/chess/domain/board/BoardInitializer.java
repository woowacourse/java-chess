package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.*;

import java.util.*;

public class BoardInitializer {
    private static final int BOARD_LENGTH = 8;

    public static Board initiateBoard() {
        Map<Position, Piece> map = new LinkedHashMap<>();

        putLineInMap(map, Vertical.H, getPiecesOfFirstLine(Owner.BLACK));
        putLineInMap(map, Vertical.G, getPiecesOfSecondLine(Owner.BLACK));
        putLineInMap(map, Vertical.F, getEmptyLine());
        putLineInMap(map, Vertical.E, getEmptyLine());
        putLineInMap(map, Vertical.D, getEmptyLine());
        putLineInMap(map, Vertical.C, getEmptyLine());
        putLineInMap(map, Vertical.B, getPiecesOfSecondLine(Owner.WHITE));
        putLineInMap(map, Vertical.A, getPiecesOfFirstLine(Owner.WHITE));

        return new Board(map);
    }

    private static void putLineInMap(Map<Position, Piece> map, Vertical vertical, Piece[] pieces){
        Horizontal[] horizontals = Horizontal.values();
        for(int i =0; i<BOARD_LENGTH; i++){
            map.put(new Position(vertical, horizontals[i]), pieces[i]);
        }
    }

    private static Piece[] getPiecesOfFirstLine(Owner owner){
        return new Piece[]{
                Rook.getInstanceOf(owner),
                Knight.getInstanceOf(owner),
                Bishop.getInstanceOf(owner),
                Queen.getInstanceOf(owner),
                Knight.getInstanceOf(owner),
                Bishop.getInstanceOf(owner),
                Knight.getInstanceOf(owner),
                Rook.getInstanceOf(owner)
        };
    }

    private static Piece[] getPiecesOfSecondLine(Owner owner){
        Piece[] pieces = new Pawn[BOARD_LENGTH];
        Arrays.fill(pieces, Pawn.getInstanceOf(owner));
        return pieces;
    }

    private static Piece[] getEmptyLine(){
        Piece[] pieces = new Empty[BOARD_LENGTH];
        Arrays.fill(pieces, Empty.getInstance());
        return pieces;
    }
}
