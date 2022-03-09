package edu.njnu.ruibot.pojo;

import edu.njnu.ruibot.enums.ResultCode;
import lombok.Data;

@Data
public class Result {
	private int code;
	private String message;
	private Object result;

	public Result(ResultCode resultCode, Object result) {
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
		this.result = result;
	}

	public static Result success(Object result) {
		return new Result(ResultCode.SUCCESS, result);
	}

	public static Result fail(ResultCode code, Object result) {
		return new Result(code, result);
	}
}
