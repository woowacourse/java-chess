package chess.controller.game.move;

import chess.controller.Controller;
import chess.controller.exception.BoardNotFoundException;
import chess.controller.game.BoardOutput;
import chess.controller.main.Request;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.service.game.LoadChessGameService;
import chess.service.game.MoveChessGameService;
import chess.view.resposne.PieceResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MoveController implements Controller {

    private final MoveChessGameService moveChessGameService;
    private final LoadChessGameService loadChessGameService;
    private final BoardOutput boardOutput;

    public MoveController(MoveChessGameService moveChessGameService, LoadChessGameService loadChessGameService,
            BoardOutput boardOutput) {
        this.moveChessGameService = moveChessGameService;
        this.loadChessGameService = loadChessGameService;
        this.boardOutput = boardOutput;
    }

    @Override
    public void run(Request request) {
        MoveRequest moveRequest = MoveRequest.from(request);
        moveChessGameService.move(moveRequest.getBoardId(), moveRequest.getOrigin(), moveRequest.getDestination());
        Optional<ChessGame> chessGame = loadChessGameService.load(moveRequest.getBoardId());
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        printBoard(chessGame.get());
    }

    private void printBoard(ChessGame chessGame) {
        boardOutput.printBoard(makeBoardResponse(chessGame.getPieces()));
    }


    private List<List<PieceResponse>> makeBoardResponse(List<List<Piece>> boardResult) {
        return boardResult.stream()
                .map(this::makeRankResponse)
                .collect(Collectors.toList());
    }

    private List<PieceResponse> makeRankResponse(List<Piece> row) {
        return row.stream()
                .map(PieceResponse::from)
                .collect(Collectors.toList());
    }
}
