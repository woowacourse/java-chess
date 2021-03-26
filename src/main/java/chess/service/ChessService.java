package chess.service;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.web.dto.CheckFinishedResponseDto;
import chess.web.dto.MoveRequestDto;
import chess.web.dto.response.Response;
import chess.web.dto.response.ResponseCode;

public class ChessService {

    private final Grid grid;

    public ChessService() {
        grid = new Grid(new NormalGridStrategy());
    }

    public Response move(MoveRequestDto requestDto) {
        try {
            grid.move(requestDto.getSourcePosition(), requestDto.getTargetPosition());
            return new Response(ResponseCode.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new Response(ResponseCode.WRONG_ARGUMENTS.getCode(), e.getMessage());
        }
    }

    public Response start() {
        try {
            grid.start();
            return new Response(ResponseCode.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new Response(ResponseCode.WRONG_ARGUMENTS.getCode(), e.getMessage());
        }
    }

    public Response checkFinished() {
        return new Response(ResponseCode.OK, new CheckFinishedResponseDto(grid.isFinished()));
    }
}
