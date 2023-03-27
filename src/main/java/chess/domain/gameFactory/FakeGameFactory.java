package chess.domain.gameFactory;

import chess.domain.game.ChessGame;
import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import chess.domain.game.Turn;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

import java.util.HashMap;
import java.util.Map;

public final class FakeGameFactory implements ChessGameFactory {

    @Override
    public ChessGame generate() {
        final Map<Position, Piece> board = new HashMap<>();
        initializeBoard(board);
        board.put(Position.of(File.of(1), Rank.of(1)), Rook.instance(Team.WHITE));
        board.put(Position.of(File.of(1), Rank.of(2)), Bishop.instance(Team.WHITE));
        return ChessGame.of(board, Turn.of(Team.WHITE));
    }

    private void initializeBoard(final Map<Position, Piece> board) {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(Position.of(file, rank), Empty.instance());
            }
        }
    }
}
