package chess.controller;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.dto.BoardDto;
import chess.domain.dto.PieceDto;
import chess.domain.exception.IllegalPieceMoveException;
import chess.domain.game.Game;
import chess.domain.game.GameSession;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoveController implements Controller {
    private final static MoveController INSTANCE = new MoveController();

    private MoveController() {
    }

    public static MoveController getInstance() {
        return INSTANCE;
    }

    @Override
    public Response execute(Request request) {
        try {
            validate(request);
            Game game = getGame();
            Game afterGame = game.move(makeStartingPosition(request), makeDestinationPosition(request));
            GameSession.replaceSession(afterGame);
            return selectResponseBy(afterGame);
        } catch (IllegalStateException | IllegalPieceMoveException e) {
            return new Response(ResponseType.FAIL, e.getMessage());
        }
    }

    private Response selectResponseBy(Game afterGame) {
        if (afterGame.isEnd()) {
            return new Response(ResponseType.END);
        }
        return new Response(ResponseType.MOVE, makeBoardDto(afterGame));
    }

    private void validate(Request request) {
        if (request.getGameCommand() != GameCommand.MOVE) {
            throw new IllegalArgumentException("잘못된 커맨드 요청입니다.");
        }
        if (!GameSession.existGame()) {
            throw new IllegalStateException("게임이 시작하지 않았습니다");
        }
    }

    private Game getGame() {
        if (!GameSession.existGame()) {
            throw new IllegalStateException("게임이 초기화 되지 않았습니다.");
        }
        return GameSession.getGame();
    }

    private Position makeStartingPosition(Request request) {
        String inputData = request.getInput();
        List<String> data = Arrays.asList(inputData.split(" "));
        return Position.of(data.get(1).charAt(0), data.get(1).charAt(1));
    }

    private Position makeDestinationPosition(Request request) {
        String inputData = request.getInput();
        List<String> data = Arrays.asList(inputData.split(" "));
        return Position.of(data.get(2).charAt(0), data.get(2).charAt(1));
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
