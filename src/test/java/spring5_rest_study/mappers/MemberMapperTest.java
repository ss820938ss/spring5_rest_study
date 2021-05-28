package spring5_rest_study.mappers;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import spring5_rest_study.config.ContextRoot;
import spring5_rest_study.dto.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
public class MemberMapperTest {
	protected static final Log log = LogFactory.getLog(MemberMapperTest.class);

	@Autowired
	private MemberMapper mapper;
		
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	

	@Test
	public void test1SelectMemberAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Member> list = mapper.selectMemberByAll();
		Assert.assertNotNull(list);
		
		list.forEach(s->log.debug(s.toString()));
	}

	@Test
	public void test2InsertMember() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Member newMember = new Member("test300@test.co.kr", "1111", "test300");
		
		int res = mapper.insertMember(newMember);
		Assert.assertEquals(1, res);
		log.debug("res id >>" + res);
		
		mapper.deleteMember(newMember.getId());
	}

	@Test
	public void test3SelectMemberById() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Member member = mapper.selectMemberById(2);
		Assert.assertNotNull(member);
		
		log.debug(member.toString());
	}
	
	@Test
	public void test4UpdateMember() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Member newMember = new Member("test300@test.co.kr", "1111", "test300");
		mapper.insertMember(newMember);
		
		newMember.setPassword("2222");
		int res = mapper.updateMember(newMember);
		Assert.assertEquals(1, res);
		
		mapper.deleteMember(newMember.getId());
	}

	@Test
	public void test5DeleteMember() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		
		Member newMember = new Member("test90@test.co.kr", "2222", "테스트90");
		mapper.insertMember(newMember);
		
		int res = mapper.deleteMember(newMember.getId());
		Assert.assertEquals(1, res);
	}



}
