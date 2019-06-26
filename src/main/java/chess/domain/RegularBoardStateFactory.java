package chess.domain;

import chess.domain.boardcell.ChessPiece;
import chess.domain.boardcell.ChessPieceFactory;
import chess.domain.boardcell.PieceType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static chess.domain.boardcell.PieceType.*;

/**
 * 정식 규칙의 말 배치를 생성하는 팩토리
 */
public class RegularBoardStateFactory implements BoardStateFactory {

    @Override
    public LivingPieceGroup create() {
        Map<CoordinatePair, ChessPiece> board = new HashMap<>();
        board.putAll(initWhite());
        board.putAll(initBlack());
        return LivingPieceGroup.of(board);
    }

    private Map<CoordinatePair, ChessPiece> initBlack() {
        List<PieceType> pieceTypes = Arrays.asList(ROOK_BLACK, KNIGHT_BLACK, BISHOP_BLACK, QUEEN_BLACK, KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK);
        Map<CoordinatePair, ChessPiece> board = initRow(CoordinateY.RANK_8, pieceTypes);
        CoordinateX.allAscendingCoordinates()
            .forEach(x -> board.put(CoordinatePair.of(x, CoordinateY.RANK_7), ChessPieceFactory.create(PAWN_BLACK)));
        return board;
    }

    private Map<CoordinatePair, ChessPiece> initWhite() {
        List<PieceType> pieceTypes = Arrays.asList(ROOK_WHITE, KNIGHT_WHITE, BISHOP_WHITE, QUEEN_WHITE, KING_WHITE, BISHOP_WHITE, KNIGHT_WHITE, ROOK_WHITE);
        Map<CoordinatePair, ChessPiece> board = initRow(CoordinateY.RANK_1, pieceTypes);
        CoordinateX.allAscendingCoordinates()
            .forEach(x -> board.put(CoordinatePair.of(x, CoordinateY.RANK_2), ChessPieceFactory.create(PAWN_WHITE)));
        return board;
    }

    private Map<CoordinatePair, ChessPiece> initRow(CoordinateY y, List<PieceType> pieceTypes) {
        List<CoordinateX> xCoords = CoordinateX.allAscendingCoordinates();
        Map<CoordinatePair, ChessPiece> map = new HashMap<>();
        IntStream.range(0, xCoords.size())
            .forEach(i -> map.put(CoordinatePair.of(xCoords.get(i), y), ChessPieceFactory.create(pieceTypes.get(i))));
        return map;
    }


}
