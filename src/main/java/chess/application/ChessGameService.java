package chess.application;

import chess.domain.board.ChessBoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameRepository;
import chess.domain.piece.position.PiecePosition;

public class ChessGameService {

    private final ChessGameRepository chessGameRepository;

    public ChessGameService(final ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public Long create(final ChessBoardFactory chessBoardFactory) {
        final ChessGame chessGame = new ChessGame(chessBoardFactory.create());
        return chessGameRepository.save(chessGame).id();
    }

    public void movePiece(final Long id, final PiecePosition source, final PiecePosition destination) {
        final ChessGame chessGame = findById(id);
        chessGame.movePiece(source, destination);
        chessGameRepository.update(chessGame);
    }

    public ChessGame findById(final Long id) {
        return chessGameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾으시는 게임이 없습니다."));
    }
}
