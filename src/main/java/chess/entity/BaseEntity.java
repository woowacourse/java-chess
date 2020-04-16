package chess.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class BaseEntity implements Comparable<BaseEntity> {

	protected final LocalDateTime createdTime;

	protected BaseEntity(final LocalDateTime createdTime) {
		Objects.requireNonNull(createdTime, "생성 시간이 null입니다.");
		this.createdTime = createdTime;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	@Override
	public int compareTo(final BaseEntity that) {
		return this.createdTime.compareTo(that.createdTime);
	}

}
