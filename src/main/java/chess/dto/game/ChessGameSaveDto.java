package chess.dto.game;

import chess.domain.game.ChessGame;
import chess.domain.game.Position;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessGameSaveDto {

    private final int size;
    private final List<String> piece_type = new ArrayList<>();
    private final List<String> piece_file = new ArrayList<>();
    private final List<String> piece_rank = new ArrayList<>();
    private final List<String> piece_team = new ArrayList<>();
    private final List<String> last_turn = new ArrayList<>();

    public ChessGameSaveDto(final ChessGame chessGame) {
        final Map<Position, Piece> board = chessGame.getBoard();

        this.size = board.size();
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            piece_type.add(entry.getValue().getPieceType().name());
            piece_file.add(entry.getKey().getFile().name());
            piece_rank.add(entry.getKey().getRank().name());
            piece_team.add(entry.getValue().getTeam().name());
            last_turn.add(chessGame.getTurn().name());
        }
    }

    public int getSize() {
        return size;
    }

    public List<String> getPiece_type() {
        return piece_type;
    }

    public List<String> getPiece_file() {
        return piece_file;
    }

    public List<String> getPiece_rank() {
        return piece_rank;
    }

    public List<String> getPiece_team() {
        return piece_team;
    }

    public List<String> getLast_turn() {
        return last_turn;
    }
}
