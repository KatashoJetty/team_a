package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.data.validation.Constraints;

import com.avaje.ebean.Model;

@Entity
public class AppGroup extends Model {
	
	/**
	 * GroupID
	 */
	@Id
	public long groupid;
	
	/**
	 * Group名
	 */
	//@Column(nullable = false, length = 100, unique = true)
	//@Constraints.Required
	//@Constraints.MaxLength(100)
	public String groupname;
	
	/**
	 * Group Comment
	 */
	public String comment;
	
	/**
	 * User Name
	 */
	@ManyToMany(mappedBy="groups", cascade=CascadeType.ALL)
	public List<AppUser> users;
	
	/**
	 * find
	 */
	private static Find<Long, AppGroup> find = new Find<Long, AppGroup>() {
	};
	
	
	/**
	 * find取得
	 * @return find
	*/
	public static Find<Long, AppGroup> getFind() {
		return find;
	}
	
	/**
	 * find設定
	 * @param find
	 *            find
	*/ 
	public static void setFind(Find<Long, AppGroup> find) {
		AppGroup.find = find;
	}
    
}
