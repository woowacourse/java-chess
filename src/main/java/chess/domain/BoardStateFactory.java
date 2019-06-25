package chess.domain;

import chess.domain.boardcell.CellFactory;
import chess.domain.boardcell.ChessPiece;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.boardcell.PieceType.*;

public class BoardStateFactory implements AbstractBoardStateFactory {

    @Override
    public LivingPieceGroup create() {
        Map<CoordinatePair, ChessPiece> board = new HashMap<>();

        board.putAll(initWhite());

        board.putAll(initBlack());

        return LivingPieceGroup.of(board);
    }

    private Map<CoordinatePair, ChessPiece> initBlack() {
        Map<CoordinatePair, ChessPiece> board = new HashMap<>();
        board.put(CoordinatePair.of("a8").get(), CellFactory.create(ROOK_BLACK));
        board.put(CoordinatePair.of("b8").get(), CellFactory.create(KNIGHT_BLACK));
        board.put(CoordinatePair.of("c8").get(), CellFactory.create(BISHOP_BLACK));
        board.put(CoordinatePair.of("d8").get(), CellFactory.create(QUEEN_BLACK));
        board.put(CoordinatePair.of("e8").get(), CellFactory.create(KING_BLACK));
        board.put(CoordinatePair.of("f8").get(), CellFactory.create(BISHOP_BLACK));
        board.put(CoordinatePair.of("g8").get(), CellFactory.create(KNIGHT_BLACK));
        board.put(CoordinatePair.of("h8").get(), CellFactory.create(ROOK_BLACK));
        CoordinateX.allAscendingCoordinates()
            .forEach(x -> board.put(CoordinatePair.of(x.getSymbol() + "7").get(), CellFactory.create(PAWN_BLACK)));
        return board;
    }

    private Map<CoordinatePair, ChessPiece> initWhite() {
        Map<CoordinatePair, ChessPiece> board = new HashMap<>();
        board.put(CoordinatePair.of("a1").get(), CellFactory.create(ROOK_WHITE));
        board.put(CoordinatePair.of("b1").get(), CellFactory.create(KNIGHT_WHITE));
        board.put(CoordinatePair.of("c1").get(), CellFactory.create(BISHOP_WHITE));
        board.put(CoordinatePair.of("d1").get(), CellFactory.create(QUEEN_WHITE));
        board.put(CoordinatePair.of("e1").get(), CellFactory.create(KING_WHITE));
        board.put(CoordinatePair.of("f1").get(), CellFactory.create(BISHOP_WHITE));
        board.put(CoordinatePair.of("g1").get(), CellFactory.create(KNIGHT_WHITE));
        board.put(CoordinatePair.of("h1").get(), CellFactory.create(ROOK_WHITE));
        CoordinateX.allAscendingCoordinates()
            .forEach(x -> board.put(CoordinatePair.of(x.getSymbol() + "2").get(), CellFactory.create(PAWN_WHITE)));
        return board;
    }


}
