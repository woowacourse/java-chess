package chess.controller;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.board.Board;
import chess.domain.board.BoardSession;
import chess.domain.dto.BoardDto;
import chess.domain.dto.PieceDto;
import chess.domain.exception.IllegalPieceMoveException;
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
            Board board = getBoard();
            board.movePiece(makeStartingPosition(request), makeDestinationPosition(request));
            return new Response(ResponseType.MOVE, makeBoardDto(BoardSession.getBoard()));
        } catch (IllegalStateException | IllegalPieceMoveException e) {
            return new Response(ResponseType.FAIL, e.getMessage());
        }
    }

    private void validate(Request request) {
        if (request.getGameCommand() != GameCommand.MOVE) {
            throw new IllegalArgumentException("잘못된 커맨드 요청입니다.");
        }
        if (!BoardSession.existBoard()) {
            throw new IllegalStateException("게임이 시작하지 않았습니다");
        }
    }

    private Board getBoard() {
        if (!BoardSession.existBoard()) {
            throw new IllegalStateException("보드가 초기화 되지 않았습니다.");
        }
        return BoardSession.getBoard();
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

    public BoardDto makeBoardDto(Board board) {
        Map<Position, Piece> data = board.getBoard();
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
