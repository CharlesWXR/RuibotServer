package edu.njnu.ruibot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.njnu.ruibot.mapper.UserMapper;
import edu.njnu.ruibot.pojo.User;
import edu.njnu.ruibot.service.PreValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreValidationServiceImpl implements PreValidationService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public long validate(String ip) {
		User user = new User();
		user.setIp(ip);

		QueryWrapper<User> query = new QueryWrapper<>();
		query.eq("ip", ip);

		List<User> users = user.selectList(query);
		if (users.size() > 0) {
			User u = users.get(0);
			if (u.getBanned() == 1)
				return -1L;
			else {
				u.setQueryCount(u.getQueryCount() + 1);
				u.updateById();
				return u.getId();
			}
		}
		else {
			user.insert();
			return user.getId();
		}
	}
}
