package edu.njnu.ruibot.controller;

import edu.njnu.ruibot.enums.ResultCode;
import edu.njnu.ruibot.pojo.QueryRecord;
import edu.njnu.ruibot.pojo.Result;
import edu.njnu.ruibot.service.PredictionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/prediction")
public class PredictionController {
	@Autowired
	PredictionService predictionService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Result getPrediction(@RequestBody Map<String, Object> params) {
		if (!params.containsKey("description") || !params.containsKey("user_id"))
			return Result.fail(ResultCode.BAD_REQUEST, null);

		String desc = (String)params.get("description");
		long userID = Long.valueOf(Integer.toString((int)params.get("user_id")));
		String prediction = predictionService.prediction(desc, userID);
		log.debug(prediction);
		return Result.success(prediction);
	}

	@RequestMapping(value = "/{userID}", method = RequestMethod.GET)
	public Result getHistoryPredictions(@PathVariable("userID")long userID) {
		List<QueryRecord> historyRecords = predictionService.getHistoryRecords(userID);
		return Result.success(historyRecords);
	}
}