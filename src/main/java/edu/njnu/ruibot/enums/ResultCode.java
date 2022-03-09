package edu.njnu.ruibot.enums;

public enum ResultCode {
	SUCCESS(200, "Success"),
	BAD_REQUEST(400, "Bad Request"),
	UNAUTHORIZED(401, "Unauthorized access"),
	REQUEST_DENIED(403, "Request denied"),
	InternalServerError(500, "Internal server error"),
	USER_NOT_FOUND(1000, "User not exist"),
	USER_EXIST(1001, "User has registered"),
	FILE_UPLOADED_ERROR(2000, "Failed to upload the file"),
	FILE_DOWNLOAD_ERROR(2001, "Failed to download the file"),
	NO_TASK_EXIST(3000, "No task exists in the taskgroup");

	private int code;
	private String message;

	ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}