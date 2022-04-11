package chess.web.view;

import chess.dto.PieceDto;
import chess.dto.PiecesDto;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardView {

    private final Map<String, PieceDto> boardView;

    public BoardView(Map<String, PieceDto> boardView) {
        this.boardView = Collections.unmodifiableMap(boardView);
    }

    public static BoardView of(PiecesDto piecesDto) {
        Map<String, PieceDto> boardView = new HashMap<>();

        for (PieceDto pieceDto : piecesDto.getPieces()) {
            boardView.put(pieceDto.getPosition(), pieceDto);
        }
        return new BoardView(boardView);
    }

    public Map<String, PieceDto> getBoardView() {
        return boardView;
    }
}
