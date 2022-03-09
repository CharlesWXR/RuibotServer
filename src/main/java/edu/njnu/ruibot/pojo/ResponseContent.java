package edu.njnu.ruibot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("td_response_content")
public class ResponseContent extends Model<ResponseContent> {
	@TableId(value = "id", type = IdType.AUTO)
	private int id;

	@TableField(value = "content")
	private String content;
}
