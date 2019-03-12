package nextdms.controller.crs.salesass;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import appconfig.BaseJunitTest;
import common.Constant;
import common.JSONUtil;
import common.WebLogs;
import nextdms.domain.crs.salesass.Potential;
import nextdms.domain.crs.salesass.PotentialDefeat;
import nextdms.domain.crs.salesass.PotentialRecommended;
import nextdms.domain.crs.salesass.PotentialRecords;
import nextdms.domain.crs.salesass.PotentialReview;
import nextdms.domain.crs.system.Group;
import nextdms.domain.crs.system.User;

/**
 * @author duyujie
 * @creation 2018年11月14日
 */
public class PotentialControllerTests extends BaseJunitTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	
	private Potential potential;
	private PotentialRecords potentialRecords;
	private PotentialReview potentialReview;
	@Before
	public void setupMockMvc() throws Exception {
		potential = new Potential();
		potential.setSaname("张三");
		potential.setSalxdh("17319878898");
		potential.setIsat06(11L);
		potential.setSayxjb("H");
		potential.setXtdwdm("zt");
		 // 跟进方式
		potentialRecords = new PotentialRecords();
		potentialRecords.setSagjfs("短信");
		potentialRecords.setXtdwdm("zt");
		// 点评
		potentialReview = new PotentialReview();
		potentialReview.setSadpnr("222");
		potentialReview.setXtdwdm("zt");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testQuery() throws Exception {
		Potential potential = new Potential();
		potential.setLimit(2);
		potential.setIsat05(22L);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/query?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potential)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	@Test
	public void testfindViewPotential() throws Exception {
		Potential potential = new Potential();
		potential.setIsat05(22L);
		potential.setIsat06(22L);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/viewquery?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potential)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testFindPotentialRecord() throws Exception {
		Potential potential = new Potential();
		potential.setIsat05(22L);
		potential.setIsat06(22L);
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/recordquery?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potential)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testCreateQk() throws Exception {
		// 先新增
		Potential potential = new Potential();
		potential.setIsat05(22L);
		potential.setSaname("222");
		potential.setSalxdh("13222222222");
		User user =new User();
		user.setXtdwdm("88");
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/createqk?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potential)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testUpdateQk() throws Exception {
		// 先新增
		Potential potential = new Potential();
		potential.setIsat05(22L);
		potential.setIsat06(22L);
		potential.setSayxjb("A");
		potential.setSaxinb("M");
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/updateqk?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potential)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testFindPotentialMc() throws Exception {
		// 先新增
		Potential potential = new Potential();
		potential.setIsat05(22L);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/findmc?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potential)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testCreateRecord() throws Exception {
		// 先新增
		PotentialRecords potentialRecords = new PotentialRecords();
		potentialRecords.setIsat07(22L);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/createrecord?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potentialRecords)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testUpdateRecord() throws Exception {
		// 先新增
		PotentialRecords potentialRecords = new PotentialRecords();
		potentialRecords.setIsat07(22L);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/updaterecord?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potentialRecords)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	
	
	@Test
	public void testdoCreateReviews() throws Exception {
		// 先新增
		PotentialReview potentialReview = new PotentialReview();
		potentialReview.setIsat08(22L);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/createreviews?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potentialReview)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testFindReview() throws Exception {
		// 先新增
		PotentialReview potentialReview = new PotentialReview();
		potentialReview.setIsat08(22L);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/queryreviews?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potentialReview)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testdoDefeat() throws Exception {
		// 先新增
		 // 战败申请
		PotentialDefeat potentialDefeat = new PotentialDefeat();
	    potentialDefeat.setIsat10(22L);
	    potentialDefeat.setIsat05(22L);
	    potentialDefeat.setSazbyy("其他原因");
	    potentialDefeat.setXtdwdm("zt");
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/dodefeat?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potentialDefeat)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testdoRecommend() throws Exception {
		PotentialRecommended potentialRecommended = new PotentialRecommended();
		potentialRecommended.setIsat09(22L);
		potentialRecommended.setXtdwdm("zt");
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/dorecommend?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potentialRecommended)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testFindPinpai() throws Exception {
		// 先新增
		Group group = new Group();
		group.setIxtm02(22L);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/findpinpai?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(group)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	@Test
	public void testFindjs() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/findjs?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(null)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}
	@Test
	public void testGetHtBack() throws Exception {
		Potential potential = new Potential();
		potential.setIsat05(102929L);
		potential.setIsat06(123444L);
		potential.setSaname("爽歪歪");
		potential.setSalxdh("13222222222");
		potential.setSayxcx("B SUV");
		potential.setXtdwdm("88");
		User user = new User();
		user.setXtczdm("LUOSHENHUA");
		user.setXtdwdm("88");	
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/crs-app/salesass/potential/gethtback?" + Constant.TEST_KEY)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJson(potential)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		WebLogs.info("输出 :" + mvcResult.getResponse().getContentAsString());
	}

}
