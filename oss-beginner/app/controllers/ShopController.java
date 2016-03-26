package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.Play;
import play.data.Form;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;
import models.*;

//@Security.Authenticated(Secured.class)
public class ShopController extends Controller {
	
	@Inject WSClient ws;

	//String keyword = request().getQueryString("q");
	
    public Result index() {
    	String keyword = request().getQueryString("q");
    	if (keyword == null || keyword.equals("")) {
    		// 初期表示
    		return ok(shop.render(keyword, new ArrayList<Shop>()));
    	}
    	// 検索結果表示
    	return ok(shop.render(keyword, search(keyword)));
    }
    
	private ArrayList<Shop> search(String keyword) {
		String keyid = Play.application().configuration().getString("gnavi.keyid");
		int timeout = Play.application().configuration().getInt("gnavi.timeout");
    	WSResponse res =  ws.url("http://api.gnavi.co.jp/RestSearchAPI/20150630/")
    			.setQueryParameter("keyid", keyid)
    			.setQueryParameter("name", keyword)
    			.setQueryParameter("format", "json")
    			.get()
    			.get(timeout);
    	String json = res.asJson().findPath("rest").toString();
    	try {
			return new ObjectMapper().readValue(json, new TypeReference<List<Shop>>() {});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }

    public Result saveshop() {
    	//String keyword = request().getQueryString("q");
    	String shopid = request().body().asFormUrlEncoded().get("shopid")[0];
    	Shop shopinfo = searchbyid(shopid);
    	shopinfo.save();
    	flash("info", shopinfo.name + "を登録しました。");
    	return redirect(routes.ShopController.index()); 	
    	//return redirect(routes.ShopController.search("keyword"));
    }
    
	private Shop searchbyid(String shopid) {
		String keyid = Play.application().configuration().getString("gnavi.keyid");
		int timeout = Play.application().configuration().getInt("gnavi.timeout");
    	WSResponse res =  ws.url("http://api.gnavi.co.jp/RestSearchAPI/20150630/")
    			.setQueryParameter("keyid", keyid)
    			.setQueryParameter("id", shopid)
    			.setQueryParameter("format", "json")
    			.get()
    			.get(timeout);
    	String json = res.asJson().findPath("rest").toString();
    	try {
			return new ObjectMapper().readValue(json, new TypeReference<Shop>() {});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }

}
