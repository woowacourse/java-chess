package chess.controller;

import chess.domain.chessgame.ChessGame;
import chess.view.OutputView;

import java.util.List;

public interface Command {
    ChessGame execute(final ChessGame chessGame, final List<String> input, final OutputView outputView);
}
