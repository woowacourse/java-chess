package chess.controller.room.join;

import chess.controller.Controller;
import chess.controller.exception.BoardNotFoundException;
import chess.controller.game.BoardOutput;
import chess.controller.main.Request;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.service.game.LoadChessGameService;
import chess.view.resposne.PieceResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JoinBoardController implements Controller {

    private final JoinBoard joinBoard;
    private final JoinBoardOutput joinBoardOutput;
    private final LoadChessGameService loadChessGameService;
    private final BoardOutput boardOutput;

    public JoinBoardController(JoinBoard joinBoard, JoinBoardOutput joinBoardOutput,
            LoadChessGameService loadChessGameService, BoardOutput boardOutput) {
        this.joinBoard = joinBoard;
        this.joinBoardOutput = joinBoardOutput;
        this.loadChessGameService = loadChessGameService;
        this.boardOutput = boardOutput;
    }

    @Override
    public void run(Request request) {
        JoinBoardRequest joinBoardRequest = JoinBoardRequest.from(request);
        joinBoard.join(joinBoardRequest.getRoomId());
        Optional<ChessGame> chessGame = loadChessGameService.load(joinBoardRequest.getRoomId());
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        joinBoardOutput.printJoinBoardSuccess(chessGame.get().getStatusType());
        boardOutput.printBoard(makeBoardResponse(chessGame.get().getPieces()));
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
