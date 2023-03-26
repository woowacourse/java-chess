package chess.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import chess.controller.dto.PieceResponse;
import chess.controller.mapper.FileMapper;
import chess.controller.mapper.PieceResponseMapper;
import chess.controller.mapper.RankMapper;
import chess.domain.exception.NotPlayableException;
import chess.domain.game.Game;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.MoveRequest;
import chess.view.dto.PositionRequest;
import chess.view.dto.Request;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameExceptionHandler exceptionHandler;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = new ChessGameExceptionHandler(outputView);
    }

    public void start() {
        outputView.printStartMessage();
        ready();
    }

    private void ready() {
        exceptionHandler.handleExceptionByRepeating(() -> {
            Request request = inputView.askCommand();
            Command command = request.getCommand();
            if (command == Command.START) {
                play(new Game());
            }
            if (command == Command.MOVE) {
                throw new NotPlayableException("아직 게임이 시작되지 않은 상태입니다.");
            }
            if (command == Command.END) {
                return;
            }
        });
    }

    private void play(Game game) {
        outputView.printPieces(createResponses(game.getPieces()));
        while (playOnce(game) != Command.END) {
            outputView.printPieces(createResponses(game.getPieces()));
        }
    }

    private Command playOnce(Game game) {
        return exceptionHandler.handleExceptionByRepeating(() -> {
            Request request = inputView.askCommand();
            Command command = request.getCommand();
            if (command == Command.START) {
                throw new NotPlayableException("게임이 진행중입니다.");
            }
            if (command == Command.MOVE) {
                MoveRequest moveRequest = request.getMoveRequest();
                Position source = createPosition(moveRequest.getSource());
                Position target = createPosition(moveRequest.getTarget());
                game.movePiece(source, target);
            }
            return command;
        });
    }

    private List<PieceResponse> createResponses(Map<Position, Piece> pieces) {
        List<PieceResponse> responses = new ArrayList<>();
        for (Entry<Position, Piece> positionToPiece : pieces.entrySet()) {
            responses.add(PieceResponseMapper.map(positionToPiece.getKey(), positionToPiece.getValue()));
        }
        return responses;
    }

    private Position createPosition(PositionRequest position) {
        File file = FileMapper.map(position.getFile());
        Rank rank = RankMapper.map(position.getRank());
        return new Position(file, rank);
    }
}
