package chess.domain;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.PieceType.*;

public class StateInitiatorFactory implements AbstractStateInitiatorFactory {

    @Override
    public Map<CoordinatePair, ChessPiece> create() {
        AbstractChessPieceFactory factory = new ChessPieceFactory();
        Map<CoordinatePair, ChessPiece> board = new HashMap<>();
        CoordinatePair.forEachCoordinate(coord -> board.put(coord, factory.create(PieceType.NONE)));

        board.put(CoordinatePair.valueOf("a1").get(), factory.create(ROOK_WHITE));
        board.put(CoordinatePair.valueOf("b1").get(), factory.create(KNIGHT_WHITE));
        board.put(CoordinatePair.valueOf("c1").get(), factory.create(BISHOP_WHITE));
        board.put(CoordinatePair.valueOf("d1").get(), factory.create(QUEEN_WHITE));
        board.put(CoordinatePair.valueOf("e1").get(), factory.create(KING_WHITE));
        board.put(CoordinatePair.valueOf("f1").get(), factory.create(BISHOP_WHITE));
        board.put(CoordinatePair.valueOf("g1").get(), factory.create(KNIGHT_WHITE));
        board.put(CoordinatePair.valueOf("h1").get(), factory.create(ROOK_WHITE));
        CoordinateX.allAscendingCoordinates()
            .forEach(x -> board.put(CoordinatePair.valueOf(x.getSymbol() + "2").get(), factory.create(PAWN_WHITE)));

        board.put(CoordinatePair.valueOf("a8").get(), factory.create(ROOK_BLACK));
        board.put(CoordinatePair.valueOf("b8").get(), factory.create(KNIGHT_BLACK));
        board.put(CoordinatePair.valueOf("c8").get(), factory.create(BISHOP_BLACK));
        board.put(CoordinatePair.valueOf("d8").get(), factory.create(QUEEN_BLACK));
        board.put(CoordinatePair.valueOf("e8").get(), factory.create(KING_BLACK));
        board.put(CoordinatePair.valueOf("f8").get(), factory.create(BISHOP_BLACK));
        board.put(CoordinatePair.valueOf("g8").get(), factory.create(KNIGHT_BLACK));
        board.put(CoordinatePair.valueOf("h8").get(), factory.create(ROOK_BLACK));
        CoordinateX.allAscendingCoordinates()
            .forEach(x -> board.put(CoordinatePair.valueOf(x.getSymbol() + "7").get(), factory.create(PAWN_BLACK)));

        return board;
    }


}
