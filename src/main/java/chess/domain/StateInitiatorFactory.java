package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.PieceType.*;

public class StateInitiatorFactory implements AbstractStateInitiatorFactory {

    @Override
    public Map<ChessCoordinate, ChessPiece> create() {
        AbstractChessPieceFactory factory = new ChessPieceFactory();
        Map<ChessCoordinate, ChessPiece> board = new HashMap<>();
        ChessCoordinate.forEachCoordinate(coord -> board.put(coord, factory.create(PieceType.NONE)));

        board.put(ChessCoordinate.valueOf("a1").get(), factory.create(ROOK_WHITE));
        board.put(ChessCoordinate.valueOf("b1").get(), factory.create(KNIGHT_WHITE));
        board.put(ChessCoordinate.valueOf("c1").get(), factory.create(BISHOP_WHITE));
        board.put(ChessCoordinate.valueOf("d1").get(), factory.create(QUEEN_WHITE));
        board.put(ChessCoordinate.valueOf("e1").get(), factory.create(KING_WHITE));
        board.put(ChessCoordinate.valueOf("f1").get(), factory.create(BISHOP_WHITE));
        board.put(ChessCoordinate.valueOf("g1").get(), factory.create(KNIGHT_WHITE));
        board.put(ChessCoordinate.valueOf("h1").get(), factory.create(ROOK_WHITE));
        ChessXCoordinate.allAscendingCoordinates()
                .forEach(x -> board.put(ChessCoordinate.valueOf(x.getSymbol() + "2").get(), factory.create(PAWN_WHITE)));

        board.put(ChessCoordinate.valueOf("a8").get(), factory.create(ROOK_BLACK));
        board.put(ChessCoordinate.valueOf("b8").get(), factory.create(KNIGHT_BLACK));
        board.put(ChessCoordinate.valueOf("c8").get(), factory.create(BISHOP_BLACK));
        board.put(ChessCoordinate.valueOf("d8").get(), factory.create(QUEEN_BLACK));
        board.put(ChessCoordinate.valueOf("e8").get(), factory.create(KING_BLACK));
        board.put(ChessCoordinate.valueOf("f8").get(), factory.create(BISHOP_BLACK));
        board.put(ChessCoordinate.valueOf("g8").get(), factory.create(KNIGHT_BLACK));
        board.put(ChessCoordinate.valueOf("h8").get(), factory.create(ROOK_BLACK));
        ChessXCoordinate.allAscendingCoordinates()
                .forEach(x -> board.put(ChessCoordinate.valueOf(x.getSymbol() + "7").get(), factory.create(PAWN_BLACK)));

        return board;
    }


}
