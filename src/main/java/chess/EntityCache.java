package chess;

import java.util.HashMap;
import java.util.Map;

import chess.repository.entity.Entity;

public class EntityCache {

	public static final String USER_GAME_KEY_NAME1 = "userGame1";
	public static final String USER_GAME_KEY_NAME2 = "userGame2";
	public static final String GAME_KEY_NAME = "game";

	private final Map<String, Entity> entityCache;

	public EntityCache() {
		this.entityCache = new HashMap<>();
	}

	public void put(final String key, final Entity value) {
		entityCache.put(key, value);
	}

	public Entity get(final String key) {
		return entityCache.get(key);
	}

	public boolean contains(final String key) {
		return entityCache.containsKey(key);
	}
}
