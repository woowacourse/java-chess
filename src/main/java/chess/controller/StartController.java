package chess.controller;

import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.dto.PieceResponse;
import chess.domain.piece.Piece;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StartController implements Controller {
    private final OutputView outputView;

    public StartController(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public Board execute(RequestInfo requestInfo, Board board) {
        try {
            validate(requestInfo, board);
            Board newBoard = BoardGenerator.makeBoard();
            printBoard(newBoard);
            return newBoard;
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return board;
        }
    }

    private void validate(RequestInfo requestInfo, Board board) {
        validateRequest(requestInfo);
        validateBoard(board);
    }

    private void validateRequest(RequestInfo requestInfo) {
        if (requestInfo.getGameCommand() != GameCommand.START) {
            throw new IllegalArgumentException();
        }
    }

    private void validateBoard(Board board) {
        if (board != BoardGenerator.emtpyBoard()) {
            throw new IllegalArgumentException("이미 게임이 시작되었습니다.");
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
}
