package chess.dto;

import java.util.List;

public class BoardDTO {

    private final List<RankDTO> board;

    public BoardDTO(List<RankDTO> board) {
        this.board = board;
    }

    public List<RankDTO> getBoard() {
        return board;
    }
}
