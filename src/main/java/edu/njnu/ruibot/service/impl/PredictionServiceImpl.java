package edu.njnu.ruibot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.njnu.ruibot.config.PredictionConfig;
import edu.njnu.ruibot.mapper.QueryRecordMapper;
import edu.njnu.ruibot.mapper.ResponseContentMapper;
import edu.njnu.ruibot.pojo.QueryRecord;
import edu.njnu.ruibot.pojo.ResponseContent;
import edu.njnu.ruibot.service.PredictionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Service
public class PredictionServiceImpl implements PredictionService {
	@Autowired
	private ResponseContentMapper responseContentMapper;

	@Autowired
	private QueryRecordMapper queryRecordMapper;

	@Autowired
	private PredictionConfig predictionConfig;

	@Override
	public String prediction(String description, long userID) {
		try {
			StringBuilder sb = new StringBuilder();

			String[] argsPrediction = new String[] {
					predictionConfig.pythonPath,
					predictionConfig.predictionPath,
					description
			};
			Process proc = Runtime.getRuntime().exec(argsPrediction);

			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				sb.append(temp + "\n");
			}
			proc.waitFor();
			reader.close();

			String prediction = sb.toString().trim();
			sb.setLength(0);

			argsPrediction[1] = predictionConfig.lawCasePath;
			Process lawCaseProc = Runtime.getRuntime().exec(argsPrediction);

			BufferedReader r = new BufferedReader(new InputStreamReader(lawCaseProc.getInputStream(), "GBK"));
			while ((temp = r.readLine()) != null) {
				sb.append(temp + "\n");
			}
			lawCaseProc.waitFor();
			r.close();

			String res = prediction + "\n" + sb.toString().trim();

			if (res != null) {
				ResponseContent responseContent = new ResponseContent();
				responseContent.setContent(res);

				QueryWrapper<ResponseContent> query = new QueryWrapper<>();
				query.eq("content", res);

				List<ResponseContent> responses = responseContent.selectList(query);
				int responseID = 0;
				if (responses.size() == 0) {
					responseContent.insert();
					responseID = responseContent.getId();
				}
				else {
					responseID = responses.get(0).getId();
				}

				QueryRecord queryRecord = new QueryRecord();
				queryRecord.setQueryText(description);
				queryRecord.setUserId(userID);
				queryRecord.setResponseTextId(responseID);

				queryRecord.insert();
			}

			return res;
		} catch (IOException | InterruptedException e) {
			log.error("Service: failed to execute python codes: {}", e.getCause().toString());
			return null;
		}
	}

	@Override
	public List<QueryRecord> getHistoryRecords(long userID) {
		QueryWrapper<QueryRecord> query = new QueryWrapper<QueryRecord>();
		query.eq("user_id", userID);

		QueryRecord queryRecord = new QueryRecord();
		List<QueryRecord> queryRecords = queryRecord.selectList(query);

		ResponseContent responseContent = new ResponseContent();
		for (QueryRecord q : queryRecords) {
			responseContent.setId(q.getResponseTextId());
			q.setResponseText(responseContent.selectById().getContent());
		}

		return queryRecords;
	}
}
