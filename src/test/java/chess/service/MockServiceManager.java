package chess.service;

import chess.dao.chess.MockChessGameDao;
import chess.dao.chess.MockPieceDao;
import chess.dao.user.MockUserDao;

public class MockServiceManager implements Service {
    @Override
    public UserService userService() {
        return new UserService(new MockUserDao());
    }

    @Override
    public ChessGameService chessGameService() {
        final ChessBoardService chessBoardService = new ChessBoardService(new MockPieceDao());
        return new ChessGameService(new MockChessGameDao(), chessBoardService);
    }
}
