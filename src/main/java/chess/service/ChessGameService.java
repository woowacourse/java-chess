package chess.service;

import chess.dao.ChessDao;
import chess.domain.game.ChessGame;
import chess.domain.game.Position;
import chess.domain.piece.Piece;
import chess.dto.game.ChessGameLoadDto;
import chess.dto.game.ChessGameSaveDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ChessGameService {

    private final ChessDao dao;

    public ChessGameService(final ChessDao dao) {
        this.dao = dao;
    }

    public void save(final ChessGame chessGame) {
        dao.delete();
        dao.save(parseDto(chessGame));
    }

    public ChessGameLoadDto loadGame() {
        return dao.loadGame();
    }

    public void delete() {
        dao.delete();
    }

    public boolean hasHistory() {
        return dao.hasHistory();
    }

    private ChessGameSaveDto parseDto(final ChessGame chessGame) {
        final List<String> pieceType = new ArrayList<>();
        final List<String> pieceFile = new ArrayList<>();
        final List<String> pieceRank = new ArrayList<>();
        final List<String> pieceTeam = new ArrayList<>();
        final List<String> lastTurn = new ArrayList<>();

        final Map<Position, Piece> board = chessGame.getBoard();
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            pieceType.add(entry.getValue().getPieceType().name());
            pieceFile.add(entry.getKey().getFile().name());
            pieceRank.add(entry.getKey().getRank().name());
            pieceTeam.add(entry.getValue().getTeam().name());
            lastTurn.add(chessGame.getTurn().name());
        }
        return new ChessGameSaveDto(pieceType, pieceFile, pieceRank, pieceTeam, lastTurn);
    }
}
