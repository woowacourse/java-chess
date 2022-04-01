package chess.model;

import chess.domain.board.piece.Color;
import chess.dto.BoardViewDto;
import chess.dto.RowDto;
import java.util.List;

public class GameModel {

    private final int id;
    private final Color currentTurn;
    private final List<RowDto> board;

    public GameModel(int id, Color currentTurn, BoardViewDto boardView) {
        this.id = id;
        this.currentTurn = currentTurn;
        this.board =  boardView.webDisplay();
    }

    public int getId() {
        return id;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public List<RowDto> getBoard() {
        return board;
    }
}
