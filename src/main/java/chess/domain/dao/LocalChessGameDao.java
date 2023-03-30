package chess.domain.dao;

import chess.domain.chessgame.ChessGame;
import chess.domain.piecesfactory.StartingPiecesFactory;

public class LocalChessGameDao implements ChessGameDao {

    private ChessGame chessGame = ChessGame.from(new StartingPiecesFactory().generate());

    @Override
    public void save(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public ChessGame select() {
        return chessGame;
    }

    @Override
    public void update(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }
}
