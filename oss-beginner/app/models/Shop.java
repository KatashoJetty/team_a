package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import models.Shop;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Find;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Shop extends Model {
	
	@Id
	@JsonIgnoreProperties 
	public Long no;

	@JsonProperty("id")
	public String shopid;
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("url")
	public String url;
		
	@Override
	public String toString() {
		return "name=" + name + ",url=" + url;
	}
/*	
	public String getName() {
		return name;
	}
	
	public String setName(String name) {
		this.name = name;
	}	
	
	public String getUrl() {
		return url;
	}
	
	public String setUrl(String url) {
		this.url = url;
	}	
*/


	/**
	 * 検索用ヘルパー
	 */
//	private static final Find<String, Shop> find = new Find<String, Shop>(){};	
	
	/**
	 * 全件取得
	 * @return
	 */
//	public static List<Shop> findAll() {
//		return find.orderBy("name").findList();
//	}
}
