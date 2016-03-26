package controllers;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Model;

import models.AppGroup;
import models.AppUser;
import models.Shop;
import play.*;
import play.mvc.*;
import views.html.*;
import play.data.Form;

public class GroupController extends Controller {
    public Result index() {
        Form<AppGroup> form = Form.form(AppGroup.class);
        return ok(creategroup.render(form));
    }

    public Result savegroup() {
        //リクエストからFormを復元
        Form<AppGroup> form = Form.form(AppGroup.class).bindFromRequest();  
        //FormからGroupを取得
        AppGroup group1 = form.get();
        group1.save();
        flash("groupinfo", group1.groupname + "を登録しました。");
        //return ok(mygroupdetail.render(group1));
    	return redirect(routes.GroupController.index()); 	
        //return redirect(creategroup.render(group1));
    }
    
    public Result grouplist() {
		// Groupを全件検索する
		List<AppGroup> allgroup = AppGroup.getFind().all();
		// 検索結果を表示する
		return ok(grouplist.render(allgroup));  	
    }
 
    
    public Result showdetail() {
    	String groupid1 = request().body().asFormUrlEncoded().get("groupid")[0];
    	AppGroup groupinfo = AppGroup.getFind().byId(Long.parseLong(groupid1));
    	return ok(groupdetail.render(groupinfo));    	
    }
    
    
    public Result join() {
    	String groupid1 = request().body().asFormUrlEncoded().get("groupid")[0];
    	AppGroup g = AppGroup.getFind().byId(Long.parseLong(groupid1));
    	String username1 = request().body().asFormUrlEncoded().get("username")[0];
    	AppUser u = AppUser.findByName(username1);
    	g.users.add(u);
    	u.groups.add(g);
    	g.save();
    	flash("joined", g.groupname + "に参加しました。");
    	return ok(groupdetail.render(g));
    	//return ok(joingroup.render(g, u));
    }
    
	/*
    private AppGroup searchbyid(String groupid) {
		// Groupを全件検索する
		List<AppGroup> group1 = AppGroup.getFind().all();
				// 検索結果を表示する
				return ok(grouplist.render(allgroup)); 
	}
    */
}
