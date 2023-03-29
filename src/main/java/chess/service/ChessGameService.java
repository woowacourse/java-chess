package chess.service;

import chess.dao.MoveDao;
import chess.dto.MoveDto;
import java.util.List;

public class ChessGameService {

    private final MoveDao moveDao;

    public ChessGameService() {
        this.moveDao = new MoveDao();
    }

    public List<MoveDto> load() {
        final List<MoveDto> moves = moveDao.restart();
        return moves;
    }

    public void move(final String source, final String target) {
        MoveDto moveDto = new MoveDto(source, target);
        moveDao.save(moveDto);
    }

    public void clear() {
        moveDao.delete();
    }
}
