package service;

import db.Movement;
import db.MovementDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {
    public void save(final Movement movement) {
        final MovementDao movementDao = new MovementDao();
        movementDao.createMovement(movement);
    }

    public void save(final String source, final String target, final String type, final String color) {
        final MovementDao movementDao = new MovementDao();
        final Map<String, String> rankMapper = new HashMap<>();
        rankMapper.put("1", "ONE");
        rankMapper.put("2", "TWO");
        rankMapper.put("3", "THREE");
        rankMapper.put("4", "FOUR");
        rankMapper.put("5", "FIVE");
        rankMapper.put("6", "SIX");
        rankMapper.put("7", "SEVEN");
        rankMapper.put("8", "EIGHT");
        final String sourceFile = source.substring(0, 1).toUpperCase();
        final String sourceRank = rankMapper.get(source.substring(1, 2));
        final String targetFile = target.substring(0, 1).toUpperCase();
        final String targetRank = rankMapper.get(target.substring(1, 2));
        movementDao.createMovement(new Movement(sourceFile, sourceRank, targetFile, targetRank, type, color));
    }

    public List<Movement> findAll() {
        final MovementDao movementDao = new MovementDao();
        return movementDao.findAll();
    }

}
