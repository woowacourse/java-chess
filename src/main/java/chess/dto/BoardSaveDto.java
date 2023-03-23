package chess.dto;

import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.pieces.Piece;
import java.util.Map;

public class BoardSaveDto {

    private final int boardId;
    private final Map<Position, Piece> board;
    private final boolean isLowerTeamTurn;

    private BoardSaveDto(final int boardId, final Map<Position, Piece> board, final boolean isLowerTeamTurn) {
        this.boardId = boardId;
        this.board = board;
        this.isLowerTeamTurn = isLowerTeamTurn;
    }

    public static BoardSaveDto toDto(final int boardId, final ChessGame chessGame) {
        return new BoardSaveDto(boardId, chessGame.getBoard(), chessGame.isLowerTeamTurn());
    }

    public int getBoardId() {
        return boardId;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public boolean isLowerTeamTurn() {
        return isLowerTeamTurn;
    }
}
