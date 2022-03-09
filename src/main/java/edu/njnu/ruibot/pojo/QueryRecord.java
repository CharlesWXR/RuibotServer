package edu.njnu.ruibot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
@TableName("td_query_record")
public class QueryRecord extends Model<QueryRecord> {
	@TableId(value = "id", type = IdType.AUTO)
	private long id;

	@TableField("user_id")
	private long userId;

	@TableField("response_text_id")
	private int responseTextId;

	@TableField("approve")
	private int approve;

	@TableField("query_time")
	private Date queryTime;

	@TableField("query_text")
	private String queryText;

	@TableField(exist = false)
	private String responseText;
}
