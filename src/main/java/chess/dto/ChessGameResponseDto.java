package chess.dto;

import chess.domain.board.Board;

public class ChessGameResponseDto {

    private final BoardResultDto boardResultDto;
    private final boolean isLowerTeamTurn;

    public ChessGameResponseDto(final BoardResultDto boardResultDto, final boolean isLowerTeamTurn) {
        this.boardResultDto = boardResultDto;
        this.isLowerTeamTurn = isLowerTeamTurn;
    }

    public static ChessGameResponseDto toDto(final Board board, final boolean isLowerTeamTurn) {
        return new ChessGameResponseDto(BoardResultDto.toDto(board), isLowerTeamTurn);
    }

    public BoardResultDto getBoardResultDto() {
        return boardResultDto;
    }

    public boolean isLowerTeamTurn() {
        return isLowerTeamTurn;
    }
}
