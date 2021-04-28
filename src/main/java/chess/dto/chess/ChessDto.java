package chess.dto.chess;

import chess.dto.piece.PieceDto;
import chess.dto.user.UserResponseDto;
import java.util.List;

public class ChessDto {

    private final List<PieceDto> pieceDtos;
    private final UserResponseDto whiteUserDto;
    private final UserResponseDto blackUserDto;
    private final String turn;
    private final boolean isFinished;

    public ChessDto(final List<PieceDto> pieceDtos, final UserResponseDto whiteUserDto,
        final UserResponseDto blackUserDto, final String turn, final boolean isFinished) {
        this.pieceDtos = pieceDtos;
        this.whiteUserDto = whiteUserDto;
        this.blackUserDto = blackUserDto;
        this.turn = turn;
        this.isFinished = isFinished;
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }

    public UserResponseDto getWhiteUserDto() {
        return whiteUserDto;
    }

    public UserResponseDto getBlackUserDto() {
        return blackUserDto;
    }

    public String getTurn() {
        return turn;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
