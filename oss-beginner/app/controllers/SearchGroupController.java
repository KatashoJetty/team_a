package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.AppGroup;
import models.Shop;
import play.*;
import play.libs.ws.WSResponse;
import play.mvc.*;
import views.html.*;
import play.data.Form;

public class SearchGroupController extends Controller {
    public Result index() {
    	String keyword1 = request().getQueryString("gname");
    	if (keyword1 == null || keyword1.equals("")) {
    		// 初期表示
    		return ok(searchgroup.render(keyword1, new ArrayList<AppGroup>()));
    	}
    	// 検索結果表示
    	//return ok(searchgroup.render(keyword1, searchgroup(keyword1)));
		return ok(searchgroup.render(keyword1, searchgroup()));
    }
    


	private List<AppGroup> searchgroup() {
		// Groupを全件検索する
		List<AppGroup> grouplist = AppGroup.getFind().all();
		// 検索結果を表示する
		return grouplist;
		
		
		//検索クラス
		//Finder<Long, AppGroup> finder = new Finder<Long, AppGroup>(Long.class, AppGroup.class);
		//List<AppGroup> grouplist = finder.all();
		//Groupidを取得
		//Long groupid1 = request().body().asFormUrlEncoded().get("groupid")[0];
		//GroupidからGroup情報の取得
        //Parent parentgroup =finder.byId(groupid);
        //取得したGroup情報を表示
        //return ok(groupdetail.render(grouplist));        
    }

}
