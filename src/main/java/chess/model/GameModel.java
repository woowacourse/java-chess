package chess.model;

import chess.domain.board.piece.Color;
import chess.dto.response.RowDto;
import chess.dto.response.WebBoardViewDto;
import java.util.List;

public class GameModel {

    private final int id;
    private final Color currentTurn;
    private final List<RowDto> board;

    public GameModel(int id, Color currentTurn, WebBoardViewDto boardView) {
        this.id = id;
        this.currentTurn = currentTurn;
        this.board = boardView.toDisplay();
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
