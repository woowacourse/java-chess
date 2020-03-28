package chess.controller;

import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.domain.ChessGame;
import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;
import java.util.stream.Collectors;

public class ChessController {

    private InputView inputView;
    private OutputView outputView;
    private ChessGame chessGame = new ChessGame();

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printInitialMessage();
        while (!chessGame.isEnd()) {
            try {
                RequestDto requestDto = inputView.inputRequest();

                switch (requestDto.getCommand()) {
                    case START:
                        chessGame.start();
                        break;
                    case END:
                        chessGame.end();
                        break;
                    case MOVE:
                        chessGame.move(MoveParameter.of(requestDto.getParameter()));
                        break;
                    case STATUS:
                        Map<Team, Double> status = chessGame.getStatus();
                        outputView.printStatus(status);
                        break;
                    case UNKNOWN:
                        throw new IllegalArgumentException();
                }
                Board board = chessGame.getBoard();
                ResponseDto responseDto = ResponseDto.of(createBoardDto(board));
                outputView.printResponse(responseDto);
            } catch (IllegalArgumentException ie) {
                System.out.println(ie.getMessage());
            }
        }
    }

    private Map<Position, String> createBoardDto(Board board) {
        return board.getBoard()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getFigure()
                ));
    }
}
