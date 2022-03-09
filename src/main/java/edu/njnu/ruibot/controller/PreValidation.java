package edu.njnu.ruibot.controller;

import edu.njnu.ruibot.enums.ResultCode;
import edu.njnu.ruibot.pojo.Result;
import edu.njnu.ruibot.service.PreValidationService;
import edu.njnu.ruibot.utils.IpUtil;
import edu.njnu.ruibot.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/exclude/user")
public class PreValidation {
	@Autowired
	private PreValidationService validationService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Result valid(HttpServletRequest request) {
		String ip = IpUtil.getIpAddr(request);
		long ID = validationService.validate(ip);
		if (ID == -1L) {
			return Result.fail(ResultCode.REQUEST_DENIED, null);
		} else {
			String token = TokenUtil.sign(ID);
			return Result.success(token);
		}
	}
}
