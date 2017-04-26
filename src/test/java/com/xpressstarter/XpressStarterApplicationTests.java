package com.xpressstarter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.entity.Like;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.DonationRepository;
import com.xpressstarter.repository.LikeRepository;
import com.xpressstarter.repository.UserRepository;
import com.xpressstarter.util.CampaignCategory;
import com.xpressstarter.util.DonationStatus;
import com.xpressstarter.util.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class XpressStarterApplicationTests {
private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
	private CampaignRepository cRep;
	@Autowired
	private UserRepository uRep;
	@Autowired
	private LikeRepository lRep;
	@Autowired
	private DonationRepository dRep;
	@Autowired
	private EntityLinks links;
	
	
	private ObjectMapper om;	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    	this.om = new ObjectMapper();
    	this.om.registerModule(new JavaTimeModule());
    	this.om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//    	uRep.delete(uRep.findByEmail("test@test.com"));
	}

	@Test
	public void verifyIfAllRestControllersAreGenerated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/campaigns").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(200));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/likes").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is(200));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/donations").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is(200));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is(200));
		
	}
	@Test
	public void verifyUserPost() throws Exception {
		User testUser = new User("Test","User","testUserPost@test.com","ksdhfisd",false,LocalDateTime.now(),Role.ADMIN);
		System.out.println(om.writeValueAsString(testUser));
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
		        .content(om.writeValueAsString(testUser))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(201));
		uRep.delete(uRep.findByEmail("testUserPost@test.com"));
	}
	
	@Test
	public void verifyCampaignPost() throws Exception {
		User testUser = new User("Test","User","testCampaignPost@test.com","ksdhfisd",false,LocalDateTime.now(),Role.ADMIN);
		testUser=uRep.save(testUser);
		Campaign testCampaign = new Campaign("TestCampaign", "This is a test Campaign", testUser, 250.0, 125.5,
    			LocalDateTime.now(), LocalDateTime.now().plusDays(50), CampaignCategory.ARTS, true, testUser);
		
		
		testUser=uRep.findByEmail("test@test.com");
		testCampaign.setApprovedBy(testUser);
		testCampaign.setBeneficiary(testUser);
		testCampaign.setLikeCount(0);
		testCampaign.setIsApproved(true);
		String content=om.writeValueAsString(testCampaign);
		JSONObject jobj = new JSONObject(content);
		jobj.remove("beneficiary");
		jobj.remove("approvedBy");
		jobj.put("beneficiary", links.linkToSingleResource(User.class,testUser.getId()).getHref());
		jobj.put("approvedBy", links.linkToSingleResource(User.class,testUser.getId()).getHref());
		content=jobj.toString();
		System.out.println(content);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/campaigns").contentType(MediaType.APPLICATION_JSON)
		        .content(content)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(201));
		cRep.delete(cRep.findByName("TestCampaign"));
		uRep.delete(testUser);
	}


}
