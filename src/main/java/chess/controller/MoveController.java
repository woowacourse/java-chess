package chess.controller;

import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.dto.PieceResponse;
import chess.domain.exception.IllegalPieceMoveException;
import chess.domain.piece.Piece;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoveController implements Controller {
    private final OutputView outputView;

    public MoveController(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public Board execute(RequestInfo requestInfo, Board board) {
        try {
            validate(requestInfo, board);
            board.movePiece(makeStartingPosition(requestInfo), makeDestinationPosition(requestInfo));
            printBoard(board);
            return board;
        } catch (IllegalArgumentException | IllegalPieceMoveException e) {
            outputView.printError(e);
            return board;
        }
    }

    private void printBoard(Board board) {
        outputView.printBoard(makePieceResponse(board.getBoard()));
    }

    public List<List<PieceResponse>> makePieceResponse(Map<Position, Piece> data) {
        List<List<PieceResponse>> response = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            List<PieceResponse> pieceResponses = Arrays.stream(File.values())
                    .map(file -> Position.of(file, rank))
                    .map(data::get)
                    .map(PieceResponse::from)
                    .collect(Collectors.toList());
            response.add(pieceResponses);
        }
        return response;
    }

    private Position makeStartingPosition(RequestInfo requestInfo) {
        String inputData = requestInfo.getInput();
        List<String> data = Arrays.asList(inputData.split(" "));
        return Position.of(data.get(1).charAt(0), data.get(1).charAt(1));
    }

    private Position makeDestinationPosition(RequestInfo requestInfo) {
        String inputData = requestInfo.getInput();
        List<String> data = Arrays.asList(inputData.split(" "));
        return Position.of(data.get(2).charAt(0), data.get(2).charAt(1));
    }


    private void validate(RequestInfo requestInfo, Board board) {
        if (requestInfo.getGameCommand() != GameCommand.MOVE) {
            throw new IllegalArgumentException();
        }
        if (board == BoardGenerator.emtpyBoard()) {
            throw new IllegalArgumentException("게임이 시작하지 않았습니다");
        }
    }
}
