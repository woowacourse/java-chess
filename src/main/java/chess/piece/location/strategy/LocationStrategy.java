package chess.piece.location.strategy;

import java.util.List;

import chess.board.Location;

public interface LocationStrategy {
	// 백팀의 로케이션 정보를 가져온다.
	List<Location> getWhiteTeamLocations();

	// 흑팀의 로케이션 정보를 가져온다.
	List<Location> getBlackTeamLocations();
}
