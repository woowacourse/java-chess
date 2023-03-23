package chess.controller;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.board.BoardGenerator;
import chess.domain.dto.BoardDto;
import chess.domain.dto.PieceDto;
import chess.domain.game.Game;
import chess.domain.game.GameSession;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StartController implements Controller {
    private final static StartController INSTANCE = new StartController();

    private StartController() {
    }

    public static StartController getInstance() {
        return INSTANCE;
    }

    @Override
    public Response execute(Request request) {
        try {
            validate(request);
            Game newGame = Game.of(BoardGenerator.makeBoard());
            GameSession.makeSession(newGame);
            return new Response(ResponseType.START, makeBoardDto(newGame));
        } catch (IllegalStateException e) {
            return new Response(ResponseType.FAIL, e.getMessage());
        }
    }

    private void validate(Request request) {
        validateRequest(request);
        validateBoard();
    }

    private void validateRequest(Request request) {
        if (request.getGameCommand() != GameCommand.START) {
            throw new IllegalArgumentException();
        }
    }

    private void validateBoard() {
        if (GameSession.existGame()) {
            throw new IllegalStateException("이미 게임이 시작되었습니다.");
        }
    }

    public BoardDto makeBoardDto(Game game) {
        Map<Position, Piece> data = game.getBoard().getBoardData();
        List<List<PieceDto>> response = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            List<PieceDto> pieceRespons = Arrays.stream(File.values())
                    .map(file -> Position.of(file, rank))
                    .map(data::get)
                    .map(PieceDto::from)
                    .collect(Collectors.toList());
            response.add(pieceRespons);
        }
        return new BoardDto(response);
    }
}
