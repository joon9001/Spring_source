package pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class JsonController2 {
	@GetMapping("list2")
	@ResponseBody
	public Map<String, Object> getJsons(){
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "공기밥");
		data.put("age", "23");
		dataList.add(data);
		
		data = new HashMap<String, String>();
		data.put("name", "김밥");
		data.put("age", "25");
		dataList.add(data);
		
		data = new HashMap<String, String>();
		data.put("name", "주먹밥");
		data.put("age", "27");
		dataList.add(data);
		//return data; 3명의 데이터를 모두 넘겨야므로 Map을 이용해야 한다.
		System.out.println("data: " + data);
//@ResponseBody에 의해 {"datas":[{"name":"공기밥","age":"23"},{"name":"김밥","age":"25"},{"name":"주먹밥","age":"27"}]}
// 이렇게 리턴함
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("datas", dataList);
		System.out.println("dataList: " + dataList);
		
		return data2;

	}
}
