package controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author rapidAutoCreate
 * @createTime <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ${application?cap_first}Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ${className}ControllerTest{

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).alwaysExpect(status().isOk()).build();
	}

	@Test
	public void pageListTest() throws Exception {
		String url = "/${className?uncap_first}/pageList";
		mockMvc.perform(get(url)
		.characterEncoding("UTF-8")
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void saveTest() throws Exception {
		String url = "/${className?uncap_first}/save";
		JSONObject jsonObject = new JSONObject();
		<#list clazz.fields as field>
			jsonObject.put("${field.fieldName}", "${field.fieldName}");
		</#list>
		mockMvc.perform(post(url)
			.characterEncoding("UTF-8")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonObject.toJSONString()))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void updateTest() throws Exception {
		String url = "/${className?uncap_first}/update";
		JSONObject jsonObject = new JSONObject();
		<#list clazz.fields as field>
				jsonObject.put("${field.fieldName}", "${field.fieldName}");
		</#list>
		mockMvc.perform(post(url)
			.characterEncoding("UTF-8")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonObject.toJSONString()))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void deletesTest() throws Exception {
		String url = "/${className?uncap_first}/deletes";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ids", "111111");
		mockMvc.perform(post(url)
			.characterEncoding("UTF-8")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonObject.toJSONString()))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void checkFormTest() throws Exception {
		String url = "/${className?uncap_first}/checkForm/name";
		mockMvc.perform(get(url)
			.characterEncoding("UTF-8")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.param("param", "11111"))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
}
