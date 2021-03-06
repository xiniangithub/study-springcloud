package com.wez.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.wez.springcloud.entities.Dept;

@RestController
public class DeptController_Consumer {

//	private static final String REST_URL_PREFIX = "http://localhost:8001";
	// 使用微服务名称访问微服务
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "consumer/dept/add")
	public boolean add(Dept dept) {
		String url = REST_URL_PREFIX + "/dept/add";
		return restTemplate.postForObject(url, dept, Boolean.class);
	}

	@RequestMapping(value = "consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		String url = REST_URL_PREFIX + "/dept/get/" + id;
		return restTemplate.getForObject(url, Dept.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "consumer/dept/list")
	public List<Dept> list() {
		String url = REST_URL_PREFIX + "/dept/list";
		return restTemplate.getForObject(url, List.class);
	}

	// 测试@EnableDiscoveryClient,消费端可以调用服务发现
	@RequestMapping(value = "/consumer/dept/discovery")
	public Object discovery() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
	}

}
