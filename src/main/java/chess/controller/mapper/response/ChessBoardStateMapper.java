package chess.controller.mapper.response;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoardStateMapper {
    private static final String EMPTY_PIECE_SYMBOL = ".";

    public static List<List<String>> convertToConsoleViewBoard(Map<Position, Piece> piecesPosition) {
        List<List<String>> consoleViewBoard = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            List<String> rankView = getRankSymbols(piecesPosition, rank);
            consoleViewBoard.add(rankView);
        }

        return consoleViewBoard;
    }

    private static List<String> getRankSymbols(Map<Position, Piece> piecesPosition, Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .map(position -> getViewSymbol(piecesPosition, position))
                .collect(Collectors.toList());
    }

    private static String getViewSymbol(Map<Position, Piece> piecesPosition, Position position) {
        if (!piecesPosition.containsKey(position)) {
            return EMPTY_PIECE_SYMBOL;
        }

        Piece piece = piecesPosition.get(position);
        return getViewSymbolOfPiece(piece);
    }

    private static String getViewSymbolOfPiece(Piece piece) {
        if (piece.isBlack()) {
            return BlackPieceSymbolMapper.getViewSymbolBy(piece);
        }

        return WhitePieceSymbolMapper.getViewSymbolBy(piece);
    }
}
