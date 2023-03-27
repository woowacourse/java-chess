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
        final List<String> piece_type = new ArrayList<>();
        final List<String> piece_file = new ArrayList<>();
        final List<String> piece_rank = new ArrayList<>();
        final List<String> piece_team = new ArrayList<>();
        final List<String> last_turn = new ArrayList<>();

        final Map<Position, Piece> board = chessGame.getBoard();
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            piece_type.add(entry.getValue().getPieceType().name());
            piece_file.add(entry.getKey().getFile().name());
            piece_rank.add(entry.getKey().getRank().name());
            piece_team.add(entry.getValue().getTeam().name());
            last_turn.add(chessGame.getTurn().name());
        }
        return new ChessGameSaveDto(piece_type, piece_file,
                piece_rank, piece_team, last_turn);
    }
}
