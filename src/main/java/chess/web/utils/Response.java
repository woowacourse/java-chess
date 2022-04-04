package chess.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.game.state.position.Position;
import chess.domain.piece.property.Color;
import chess.web.dto.BoardDto;
import chess.web.dto.PieceDto;

public class Response {

    private static final int MAX_RANK = 8;
    private static final int MIN_RANK = 1;
    private static final char MIN_FILE = 'a';
    private static final char MAX_FILE = 'h';

    public static void putChessGame(Map<String, Object> model, BoardDto boardDto, ChessGame chessGame) {
        Map<String, PieceDto> pieces = new HashMap<>();

        for (Position position : boardDto.getBoard().keySet()) {
            String positionStr = position.getFile().name() + position.getRank().getValue();
            pieces.put(positionStr, PieceDto.of(position, boardDto.get(position)));
        }

        initializeRowPieces(model, pieces);
        putScoreBoard(model, chessGame, pieces);
    }

    private static void initializeRowPieces(Map<String, Object> model, Map<String, PieceDto> pieces) {
        for (int i = MAX_RANK; i >= MIN_RANK; i--) {
            List<PieceDto> pieceDtos = initializeColumnPieces(pieces, i);
            model.put("pieces" + i, pieceDtos);
        }
    }

    private static List<PieceDto> initializeColumnPieces(Map<String, PieceDto> pieces, int rank) {
        List<PieceDto> pieceDtos = new ArrayList<>();
        for (char currentFile = MIN_FILE; currentFile <= MAX_FILE; currentFile++) {
            String position = currentFile + String.valueOf(rank);
            if (pieces.containsKey(position)) {
                pieceDtos.add(pieces.get(position));
                continue;
            }
            pieceDtos.add(PieceDto.of(position, ""));
        }
        return pieceDtos;
    }

    private static void putScoreBoard(Map<String, Object> model, ChessGame chessGame, Map<String, PieceDto> pieces) {
        if (!pieces.isEmpty()) {
            Map<Color, Double> status = chessGame.status();
            model.put("blackScore", status.get(Color.Black));
            model.put("WhiteScore", status.get(Color.White));
        }
    }
}
