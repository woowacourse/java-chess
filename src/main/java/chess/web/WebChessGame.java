package chess.web;

import chess.db.BoardDAO;
import chess.db.StateDAO;
import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.State;
import java.util.Map;

public class WebChessGame {

    private BoardDAO boardDAO;
    private StateDAO stateDAO;

    public State getState() {
        Color color = stateDAO.findColor();
        Map<Position, Piece> pieces = boardDAO.findAllPieces();
        return State.create(pieces, color);
    }

    public void searchSavedGame(String roomId, BoardDTO boardDTO) {
        stateDAO = new StateDAO(roomId);
        boardDAO = new BoardDAO(roomId);
        if (stateDAO.isSaved()) {
            State now = getState();
            boardDTO.update(now.getBoard());
        }
    }

    public boolean isSaved() {
        return stateDAO.isSaved();
    }

    public Color getColor() {
        return getState().getColor();
    }

    public void initializeGame(BoardDTO boardDTO, State state) {
        boardDTO.update(state.getBoard());
        if (state.isRunning()) {
            stateDAO.initializeID();
            stateDAO.initializeColor();
            boardDAO.initializePieces(state);
        }
    }

    public State executeOneTurn(Command command, BoardDTO boardDTO) {
        State next = getState().proceed(command);
        executeWhenFinished(boardDTO, next);
        executeWhenShowScore(command, boardDTO, next);
        executeWhenMoveEnd(command, boardDTO, next);
        return next;
    }

    private void executeWhenMoveEnd(Command command, BoardDTO boardDTO, State next) {
        if (command.isMove()) {
            endTurn(next, command, boardDTO);
        }
    }

    private void executeWhenShowScore(Command command, BoardDTO boardDTO, State next) {
        if (command.isStatus()) {
            boardDTO.updateWithScore(next.getBoard(), next.generateScore());
        }
    }

    private void executeWhenFinished(BoardDTO boardDTO, State next) {
        if (next.isFinished()) {
            boardDTO.update(next.getBoard());
            stateDAO.terminateDB();
        }
    }

    private void endTurn(State next, Command command, BoardDTO boardDTO) {
        boardDTO.update(next.getBoard());
        movePieceIntoDB(next, command);
        if (!next.isWhite()) {
            stateDAO.convertColor(Color.BLACK);
            return;
        }
        stateDAO.convertColor(Color.WHITE);
    }

    private void movePieceIntoDB(State next, Command command) {
        Position from = command.getFromPosition();
        Position to = command.getToPosition();
        boardDAO.delete(from);
        boardDAO.delete(to);
        boardDAO.insert(to, next.getBoard().findPiece(to));
    }
}
