package com.ele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSON;

/**
 * 获取店铺对应的企业信息
 * @author chenyw
 *
 */
	public class GetElmLhfjInfoJsoup {
	// 获取饿了么量化分级信息
	public static void main(String[] args) throws Exception {
		String url = "https://www.ele.me/shop/E18426130947557485887";
		//String authentic_id = getauthentic_id(url);
		String authentic_id="6375305002744496";
		System.out.println(authentic_id);
		Map map = geElmLhfj(authentic_id);
	}

	// 获取饿了么的authentic_id
	public static String getauthentic_id(String url) throws Exception {

		String id = url.split("https://www.ele.me/shop/")[1];
		System.out.println(id + "===id");
		String urlx = "https://mainsite-restapi.ele.me/shopping/restaurant/"
				+ id + "";
		Document document = null;
		try {
			/*
			 * Random random = new Random(); int a =
			 * random.nextInt(TestUtils.getSleepTime()) + 1;
			 */
			new Thread().sleep(100);
			document = Jsoup.connect(urlx).userAgent("Mozilla")
					.ignoreContentType(true).timeout(200000).data().get();
			String authentic_id = document.html().split("authentic_id")[1]
					.split(",")[0].replace("\":", "");
			return authentic_id;
		} catch (Exception e2) {
			e2.printStackTrace();
			String str = e2.toString();
			if (str.indexOf("Status=430") > 0) {
				System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
						.format(new Date()));
				System.err.println("IP被封了休息一个小时在访问");
				try {
					new Thread().sleep(1000 * 3600);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			return null;
		} finally {
		}
	}

	// 获取饿了么的量化分级信息
	public static Map geElmLhfj(String authentic_id) throws Exception {
		Map<Object, Object> maps = new HashMap<Object, Object>();
		// url="https://www.ele.me/shop/397092";
		String url = "https://mainsite-restapi.ele.me/shopping/v1/restaurants/"
				+ authentic_id + "/business/qualification";
		// System.out.println(url);
		Document document = null;
		try {
			/*
			 * Random random = new Random(); int a =
			 * random.nextInt(TestUtils.getSleepTime()) + 1;
			 */
			new Thread().sleep(100);
			document = Jsoup.connect(url).userAgent("Mozilla")
					.ignoreContentType(true).timeout(200000).data().get();
			String str = document.text();
			System.out.println(document.text());
			maps = (Map) JSON.parse(str);
			// System.out.println(maps);
			// Object imgsmap= maps.get("images");
			// System.out.println(imgsmap.toString());
			String city_id = null;
			String corp_name = null;
			String images = null;
			String last_check_date = null;// 最后检查日期
			String last_check_result = null;// 检查结果
			String license_legal_person = null;// 法定代表人
			String license_location = null;// 地址
			String restaurant_service_licence_no = null;// 许可证号
			String service_license_business_scope = null;// 经营范围
			String service_license_expire_date = null;// 许可证有效期
			String type = null;
			String is_xkz = "0";
			String is_yyzz = "0";
			if (maps.get("city_id") != null) {
				city_id = maps.get("city_id").toString();
			}
			if (maps.get("corp_name") != null) {
				corp_name = maps.get("corp_name").toString();
			}
			if (maps.get("images") != null) {
				images = maps.get("images").toString();
				String strs[] = images.split(",");
				if (strs.length > 1) {
					is_xkz = "1";
					is_yyzz = "1";
				} else if (strs.length == 1) {
					is_xkz = "1";
					is_yyzz = "0";
				} else {
					is_xkz = "0";
					is_yyzz = "0";
				}
			}
			maps.put("is_xkz", is_xkz);
			maps.put("is_yyzz", is_yyzz);
			if (maps.get("last_check_date") != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"MM/dd/yyyy HH:mm:ss");
				System.err.println(maps.get("last_check_date").toString());
				Date dt = new Date(Long.parseLong(maps.get(
						"last_check_date").toString()) * 1000);
				String sDateTime = sdf.format(dt);
				System.err.println(sDateTime);
				last_check_date = maps.get("last_check_date").toString();
				// last_check_date
				maps.put("last_check_date", sDateTime);

			}
			if (maps.get("last_check_result") != null) {
				last_check_result = maps.get("last_check_result").toString();
			}
			if (maps.get("license_legal_person") != null) {
				license_legal_person = maps.get("license_legal_person")
						.toString();
			}

			if (maps.get("license_location") != null) {
				license_location = maps.get("license_location").toString();
			}
			if (maps.get("restaurant_service_licence_no") != null) {
				restaurant_service_licence_no = maps.get(
						"restaurant_service_licence_no").toString();
			}
			if (maps.get("service_license_business_scope") != null) {
				service_license_business_scope = maps.get(
						"service_license_business_scope").toString();
			}
			if (maps.get("service_license_expire_date") != null) {
				service_license_expire_date = maps.get(
						"service_license_expire_date").toString();
			}
			if (last_check_result != null) {
				System.err.println(last_check_result);
			}
			System.out
					.println("================================================");
			return maps;
		} catch (Exception e2) {
			e2.printStackTrace();
			String str = e2.toString();
			if (str.indexOf("Status=430") > 0) {
				System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
						.format(new Date()));
				System.err.println("IP被封了休息一个小时在访问");
				try {
					new Thread().sleep(1000 * 3600);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			return null;
		} finally {

		}
	}



}
