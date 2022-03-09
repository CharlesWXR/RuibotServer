package edu.njnu.ruibot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("td_user")
public class User extends Model<User> {
	@TableId(value = "id", type = IdType.AUTO)
	private long id;

	@TableField("ip")
	private String ip;

	@TableField("query_count")
	private int queryCount;

	@TableField("banned")
	private int banned;
}
