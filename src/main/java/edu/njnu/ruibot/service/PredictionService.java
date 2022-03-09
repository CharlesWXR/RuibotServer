package edu.njnu.ruibot.service;

import edu.njnu.ruibot.pojo.QueryRecord;

import java.util.List;

public interface PredictionService {
	String prediction(String description, long userID);

	List<QueryRecord> getHistoryRecords(long userID);
}
