package edu.njnu.ruibot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
	private static final long EXPIRE_DATE = 1000 * 60 * 60 * 24;

	private static final String PRIVATE_KEY = "U2FsdGVkX184C7cKA2uAGsDn9vrpRTpbvqyUvoZ1OJ4=";

	public static String sign(Long ID) {
		try {
			Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
			Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);

			Map<String, Object> header = new HashMap<String, Object>();
			header.put("typ", "JWT");
			header.put("alg", "HS256");
			return JWT.create()
					.withHeader(header)
					.withClaim("ID", ID)
					.sign(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean verify(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static long decode(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			return jwt.getClaim("ID").asLong();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}