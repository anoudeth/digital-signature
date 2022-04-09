package com.noh.ds;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.noh.ds.security.SecurityManager;

@SpringBootTest
class DigitalSignatureApplicationTests {

	@Autowired
	SecurityManager securityManager;

	@Test
	void contextLoads() {
	}

	@Test
	void verifySignature() {
		Gson gson = new Gson();
		String signature = "C9EFDEB97C2858C7FCACAE423625F89C21999ED65D51A328368FCD5F3B59AD36882F9ABB0EC449B9FF6A9FE5AD21524E3D9C402928FC1F2E12D027259257507DFEC863D4CD610540170D890DAF2BF37D4185708920801FFC0916723CB64A13E1EEDF79A3CBF62F1F4ABA1FE41A91589A58EE309D95C21D61D292230849C6D97EA1157CD9FA5F1CB7E8689BB3073179342D0DAD5EAF80D2B733D1CC4BEF6225DCD9CF75D03350BAC9951C81466114B537901C238094A4A810E8D220C93C88BF4B35B46723612DFDC0C79F181F1327E2DCA26C4AB61C2430367CB059174D89B29D1708BC374ADC5AC20267236C1DEA548B5EE975F2FDEC4664283A623412B3F535";

		JsonObject requestBody = new JsonObject();

		requestBody.addProperty("frommember", "0302000410000000");
		requestBody.addProperty("fromuser", "nohder");
		requestBody.addProperty("fromaccount", "bouder");
		requestBody.addProperty("tomember", "IB");
		requestBody.addProperty("toaccount", "0100000000000");
		requestBody.addProperty("totype", "type");
		requestBody.addProperty("reference", "1234567890");
		requestBody.addProperty("time", "2020-10-0905:34:11");
		String requestString = gson.toJson(requestBody);

		boolean verifyOk = this.securityManager.verifySignatureWithPublicKey(requestString, signature, "public-merchant.pem");

		System.out.println("verify: " + verifyOk);
	}

}
