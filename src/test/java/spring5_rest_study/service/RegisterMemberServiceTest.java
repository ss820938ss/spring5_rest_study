package spring5_rest_study.service;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
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
public class RegisterMemberServiceTest {
	protected static final Log log = LogFactory.getLog(RegisterMemberServiceTest.class);

	@Autowired
	private RegisterMemberService service;
	
	@Autowired
	private RemoveMemberService remService;
	
	@Test
	public void testRegisterMember() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Member member = new Member("test100@test.co.kr", "1111", "test100");
		
		int res = service.registerMember(member);
		Assert.assertEquals(1, res);
		
		log.debug("res > " + res);
		
		remService.removeMember(member.getId());
	}

}
