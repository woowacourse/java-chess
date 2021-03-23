package chess.controller;

import chess.domain.board.Board;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.running.Ready;
import chess.domain.location.Location;
import chess.domain.team.Team;
import chess.utils.BoardUtil;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void play() {
        OutputView.printGameStartMessage();
        Team currentTurn = Team.WHITE;

        Board board = BoardUtil.generateInitialBoard();
        State state = new Ready(board);

    }
}
