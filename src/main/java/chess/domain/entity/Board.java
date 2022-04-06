package chess.domain.entity;

public class Board {

    private Long board_id;
    private Long game_id;
    private String board_position;
    private String board_piece;
    private String board_color;

    public Board(Long board_id, Long game_id, String board_position, String board_piece, String board_color) {
        this.board_id = board_id;
        this.game_id = game_id;
        this.board_position = board_position;
        this.board_piece = board_piece;
        this.board_color = board_color;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public Long getGame_id() {
        return game_id;
    }

    public String getBoard_position() {
        return board_position;
    }

    public String getBoard_piece() {
        return board_piece;
    }

    public String getBoard_color() {
        return board_color;
    }
}
