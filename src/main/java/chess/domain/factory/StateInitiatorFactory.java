package chess.domain.factory;

import chess.domain.PieceType;
import chess.domain.coordinate.ChessCoordinate;
import chess.domain.coordinate.ChessXCoordinate;
import chess.domain.piece.ChessPiece;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.PieceType.*;

public class StateInitiatorFactory implements AbstractStateInitiatorFactory {

    @Override
    public Map<ChessCoordinate, ChessPiece> create() {
        ChessPieceFactory factory = new ChessPieceFactory();
        Map<ChessCoordinate, ChessPiece> board = new HashMap<>();
        ChessCoordinate.forEachCoordinate(coord -> board.put(coord, factory.create(PieceType.NONE)));

        board.put(ChessCoordinate.valueOf("a1"), factory.create(ROOK_WHITE));
        board.put(ChessCoordinate.valueOf("b1"), factory.create(KNIGHT_WHITE));
        board.put(ChessCoordinate.valueOf("c1"), factory.create(BISHOP_WHITE));
        board.put(ChessCoordinate.valueOf("d1"), factory.create(QUEEN_WHITE));
        board.put(ChessCoordinate.valueOf("e1"), factory.create(KING_WHITE));
        board.put(ChessCoordinate.valueOf("f1"), factory.create(BISHOP_WHITE));
        board.put(ChessCoordinate.valueOf("g1"), factory.create(KNIGHT_WHITE));
        board.put(ChessCoordinate.valueOf("h1"), factory.create(ROOK_WHITE));
        ChessXCoordinate.allAscendingCoordinates()
                .forEach(x -> board.put(ChessCoordinate.valueOf(x.getSymbol() + "2"), factory.create(PAWN_WHITE)));

        board.put(ChessCoordinate.valueOf("a8"), factory.create(ROOK_BLACK));
        board.put(ChessCoordinate.valueOf("b8"), factory.create(KNIGHT_BLACK));
        board.put(ChessCoordinate.valueOf("c8"), factory.create(BISHOP_BLACK));
        board.put(ChessCoordinate.valueOf("d8"), factory.create(QUEEN_BLACK));
        board.put(ChessCoordinate.valueOf("e8"), factory.create(KING_BLACK));
        board.put(ChessCoordinate.valueOf("f8"), factory.create(BISHOP_BLACK));
        board.put(ChessCoordinate.valueOf("g8"), factory.create(KNIGHT_BLACK));
        board.put(ChessCoordinate.valueOf("h8"), factory.create(ROOK_BLACK));
        ChessXCoordinate.allAscendingCoordinates()
                .forEach(x -> board.put(ChessCoordinate.valueOf(x.getSymbol() + "7"), factory.create(PAWN_BLACK)));

        return board;
    }
}
