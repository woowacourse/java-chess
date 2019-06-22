package chess.domain;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.PieceType.*;

public class BoardStateFactory implements AbstractBoardStateFactory {

    @Override
    public GameBoardState create() {
        AbstractChessPieceFactory factory = new ChessPieceFactory();
        Map<CoordinatePair, ChessPiece> board = new HashMap<>();
        CoordinatePair.forEachCoordinate(coord -> board.put(coord, factory.create(PieceType.NONE)));

        board.put(CoordinatePair.from("a1").get(), factory.create(ROOK_WHITE));
        board.put(CoordinatePair.from("b1").get(), factory.create(KNIGHT_WHITE));
        board.put(CoordinatePair.from("c1").get(), factory.create(BISHOP_WHITE));
        board.put(CoordinatePair.from("d1").get(), factory.create(QUEEN_WHITE));
        board.put(CoordinatePair.from("e1").get(), factory.create(KING_WHITE));
        board.put(CoordinatePair.from("f1").get(), factory.create(BISHOP_WHITE));
        board.put(CoordinatePair.from("g1").get(), factory.create(KNIGHT_WHITE));
        board.put(CoordinatePair.from("h1").get(), factory.create(ROOK_WHITE));
        CoordinateX.allAscendingCoordinates()
            .forEach(x -> board.put(CoordinatePair.from(x.getSymbol() + "2").get(), factory.create(PAWN_WHITE)));

        board.put(CoordinatePair.from("a8").get(), factory.create(ROOK_BLACK));
        board.put(CoordinatePair.from("b8").get(), factory.create(KNIGHT_BLACK));
        board.put(CoordinatePair.from("c8").get(), factory.create(BISHOP_BLACK));
        board.put(CoordinatePair.from("d8").get(), factory.create(QUEEN_BLACK));
        board.put(CoordinatePair.from("e8").get(), factory.create(KING_BLACK));
        board.put(CoordinatePair.from("f8").get(), factory.create(BISHOP_BLACK));
        board.put(CoordinatePair.from("g8").get(), factory.create(KNIGHT_BLACK));
        board.put(CoordinatePair.from("h8").get(), factory.create(ROOK_BLACK));
        CoordinateX.allAscendingCoordinates()
            .forEach(x -> board.put(CoordinatePair.from(x.getSymbol() + "7").get(), factory.create(PAWN_BLACK)));

        return GameBoardState.of(board);
    }


}
