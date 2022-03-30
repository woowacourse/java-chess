package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

import chess.Member;

class MemberDaoTest {
	@Test
	void connection() {
		final MemberDao memberDao = new MemberDao();
		final Connection connection = memberDao.getConnection();
		assertThat(connection).isNotNull();
	}

	@Test
	void save() {
		final MemberDao memberDao = new MemberDao();
		memberDao.save(new Member("Azpi", "신동석"));
	}

	@Test
	void findById() {
		final MemberDao memberDao = new MemberDao();
		final Member member = memberDao.findById("Azpi");
		System.out.println(member);
		assertThat(member.getName()).isEqualTo("신동석");
	}

	@Test
	void findWithRoleById() {
		final MemberDao memberDao = new MemberDao();
		final Member member = memberDao.findWithAllById("Azpi");
		System.out.println(member);
		assertThat(member.getName()).isEqualTo("신동석");
	}

	@Test
	void findAll() {
		final MemberDao memberDao = new MemberDao();
		final List<Member> members = memberDao.findAll();
		System.out.println(members);
		assertThat(members).isNotNull();
	}
}
